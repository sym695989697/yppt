package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.AccountRechargeReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.AccountRechargeRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.MoneyError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayeeRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayerError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.sinoiov.mobile.webapi.util.FileUploadImage;
import com.ctfo.sinoiov.mobile.webapi.util.PayConstants;
import com.ctfo.upp.http.HttpUtils;
import com.ctfo.util.DateUtil;
import com.ctfo.util.EnvironmentUtil;

/**
 * 
 * 帐号充值申请服务
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
@Service("accountRechargeManagerImpl")
public class AccountRechargeManagerImpl implements IJsonService {
	//日志信息
	protected static final Log logger = LogFactory.getLog(AccountRechargeManagerImpl.class);

	private static int MONEY_VALUE = 100;
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		
		AccountRechargeRsp accountRechargeRsp = new AccountRechargeRsp();
		AccountRechargeReq accountRechargeReq = (AccountRechargeReq) request.getBody();
		UppResult uppResult=null;
		try {
            logger.info(String.format("帐号充值申请服务",JSONObject.fromObject(accountRechargeReq)));
            logger.info(String.format("上传凭证图片操作"));
            
            Map<String,Object> params=new HashMap<String,Object>();
            
            String picUrl = uploadImage(params , (File[]) obj[0],(String[])obj[1]);
            
            //清空参数重新赋值
            //凭证图片地址
            params.put("voucherFileName", picUrl);
          //申请人标识(统一认证UUID)
            params.put("applyId", accountRechargeReq.getUserId());
            //付款人标识(统一认证UUID)
            accountRechargeReq.setPayer(accountRechargeReq.getUserId());
            params.put("remitterId", accountRechargeReq.getPayer());
          //付款人姓名
            params.put("remitterName", accountRechargeReq.getPayerName());
         // 付款人银行卡号或账号
            params.put("remitterBankcardNo", accountRechargeReq.getPayAccount());
          //交易金额（小写）
            params.put("tradeAmount", accountRechargeReq.getRemittance());
        	// 交易类型,1:充值，2：扣款
            params.put("tradeType", "1");
            //收款人帐号
            params.put("payeeNo", accountRechargeReq.getPayee());
            //收款人名称
            params.put("payeeName", accountRechargeReq.getPayeeName());
            //申请时间
            params.put("applyTime" , accountRechargeReq.getPayTime());
            //资金确认时间
            params.put("amountArriveTime" , DateUtil.getCurrentUtcMsTime().toString());
            params.put("insideAccountNo" , accountRechargeReq.getInsideAccountNo());
            params.put("approvalPersonName" , accountRechargeReq.getPayerName());
            
            uppResult = invokeUPP(params, "UPP_CREATE_ACCOUNT_RECHARGE_APPLY");
            if (uppResult.getResult().equals(UppResult.FAILURE)) {
                throw new ClientException(PayError.E240001, uppResult.getError());
            }
            accountRechargeRsp.setAttachId(picUrl);
            accountRechargeRsp.setResult("0");
		}  catch (ClientException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
			// TODO Auto-generated catch block
			accountRechargeRsp.setResult("1");
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("账户充值失败:"+e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//			logger.error("open card error:" + accountRechargeReq);
			throw new  ClientException("资金账户充值失败,userId="+accountRechargeReq.getUserId(),e);
		}
		
	return accountRechargeRsp;
	}

	
    public String uploadImage(Map<String, Object> params, File[] files,
            String[] fileNames) throws Exception {
        try {
            logger.info("开始上传文件");
            File[] newFiles = new File[files.length];
            String webSite = PayConstants.uppURL;
            String methodUrlValue = EnvironmentUtil.getInstance()
                    .getPropertyValue("UPP_UPLOAD_PIC");
            Map map = new HashMap<String, String>();
            map.putAll(params);

            for (int i = 0; i < files.length; i++) {
                int suxInx = fileNames[i].lastIndexOf(".");
                String suxName = "jpg";
                if (suxInx != -1) {
                    suxName = fileNames[i].substring(suxInx);
                }
                int filePathInx = files[i].getPath().lastIndexOf(
                        File.separatorChar);
                String filePath = files[i].getPath().substring(0, filePathInx);
                String newFileStr = filePath + File.separatorChar
                        + UUID.randomUUID().toString() + suxName;
                newFiles[i] = FileUploadImage.FileCopy(files[i].getPath(),
                        newFileStr);
            }

            String picUrl = FileUploadImage.upload(webSite + methodUrlValue,
                    map, newFiles);
            // String
            // picUrl=FileUploadImage.upload("http://127.0.0.1:8080/ICCardManagerApp/viceCard/icCardImport.action",
            // map, file);
            logger.info(String.format("上传文件结束，返回结果：%s", picUrl));
            return picUrl;
        } catch (Exception e) {
            logger.info(String.format("上传文件异常"), e);
            throw new Exception("上传文件异常", e);
        }
    }
	
    public UppResult invokeUPP(Object param, String methodUrlKey) throws Exception{
        UppResult uppResult = null ;
        try {
            String methodUrlValue = EnvironmentUtil.getInstance()
                    .getPropertyValue(methodUrlKey);
            String requestJson = JSONObject.fromObject(param).toString();
            // 发送POST消息(明文的数据在下面的方法中做了加密，实际POST发送给支付平台是加密后的数据)
            String returnJson = HttpUtils.sendRequest(requestJson,
                    PayConstants.uppURL + methodUrlValue,
                    PayConstants.myPrivateKey, PayConstants.uppPublicKey,
                    PayConstants.myMerchantCode);
            uppResult = (UppResult) JSONObject.toBean(
                    JSONObject.fromObject(returnJson), UppResult.class);
            
            
        } catch (Exception e) {
            logger.error("调用支付中接口出错：", e);
            throw e;
        }
        return uppResult;
    }
	
	
	@Override
	public void validate(Parameter request) throws ClientException {
	    
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		AccountRechargeReq req = (AccountRechargeReq) request.getBody();
		
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
        /*if(StringUtils.isBlank(req.getPayer())){
            throw new ClientException("E170002",PayerError.E170002);
        }*/
        if(StringUtils.isBlank(req.getPayerName())){
            throw new ClientException("E170001",PayerError.E170001);
        }
        
		if(StringUtils.isBlank(req.getPayAccount())){
			throw new ClientException("E160002",MoneyError.E160002);
		}
		if(StringUtils.isBlank(req.getPayee()) || 
		   StringUtils.isBlank(req.getPayeeName())){
			throw new ClientException("E190001",PayeeRrror.E190001);
		}

		if((req.getRemittance()=="" && req.getRemittance().isEmpty())){
			throw new ClientException("E160002",MoneyError.E160002);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", AccountRechargeReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}