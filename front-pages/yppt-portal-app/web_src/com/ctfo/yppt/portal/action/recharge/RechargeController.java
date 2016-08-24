package com.ctfo.yppt.portal.action.recharge;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.Converter;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.common.utils.NumberUtil;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.portal.service.datastat.IUserStatService;
import com.ctfo.yppt.portal.service.recharge.IRechargeService;
import com.ctfo.yppt.portal.view.oilcard.OilCardChargeResultVo;
import com.ctfo.yppt.portal.view.oilcard.OilCardChargeVos;
import com.ctfo.yppt.portal.view.oilcard.OilCardPayVo;


/**
 * 
 * 油卡相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:26:22
 *
 */
@Controller
@Scope("prototype")
public class RechargeController extends BaseControler{
	
	@Resource(name="rechargeService" ,description= "油卡充值管理service")
	IRechargeService rechargeService;
	@Resource(name="userStatService" ,description= "用户数据统计service")
    IUserStatService userStatService;
	private static Log logger = LogFactory.getLog(RechargeController.class);

	
	  @ResponseBody
	    public PaginationResult<?> getRechargeById(String id) {
	
		  try {
			 result.setDataObject(rechargeService.getApplyRechargeById(id));
	        } catch (Exception e) {
	            logger.error("======controller层======油卡充值记录异常：======", e);
	            result.setMessage(GlobalMessage.FAIL);
	        }
	        return result;
	  }
	     
	
    /**
     * 油卡充值数据传递
     * @return
     */
    @ResponseBody
    public PaginationResult<?> oilCardRechargeDataTransmit(OilCardChargeVos oilCardChargeVos) {
        try {
            session.setAttribute(Converter.SESSION_OIL_CARD_CHARGE_DATA, oilCardChargeVos);
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (Exception e) {
            logger.error("======controller层======油卡充值数据传递异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
    }
	
	
	/**
	 * IC卡充值申请
	 * @param oilCardPayVo
	 * @return
	 */
	@ResponseBody
	public  PaginationResult<?> rechargeApply(OilCardPayVo oilCardPayVo){
		try {
			OilCardChargeVos oilCardChargeVos = (OilCardChargeVos) session.getAttribute(Converter.SESSION_OIL_CARD_CHARGE_DATA);
			OilCardChargeResultVo oilCardChargeResultVo = rechargeService.rechargeApply(oilCardPayVo, oilCardChargeVos, index);
			result.setDataObject(oilCardChargeResultVo);
		} catch (Exception e) {
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======IC卡充值申请异常：======", e);
		}
		return result;
	}
	/**
	 * 查询油卡充值记录
	 * @return
	 */
    @ResponseBody
    public PaginationResult<?> queryOilCardRechargeList() {
        if(requestParam == null){
            requestParam = new DynamicSqlParameter();
        }
        if(requestParam.getEqual() == null){
            requestParam.setEqual( new HashMap<String, String>());
        }
        try {
        	//交易状态为处理中的情况,不用查询数据库,直接返回无数据.
			if("2".equals(requestParam.getEqual().get("paystatus"))){
				requestParam.getEqual().remove("paystatus");
//				requestParam.getEqual().put("paystatus", "\'02\',\'04\',\'06\'");
				requestParam.getEqual().put("processStatus", "true");
			//处理失败
			}else if("3".equals(requestParam.getEqual().get("paystatus")) ){
				requestParam.getEqual().remove("paystatus");
				requestParam.getEqual().put("paystatus", "03");
			}else if("1".equals(requestParam.getEqual().get("paystatus"))){//成功
				requestParam.getEqual().remove("paystatus");
				requestParam.getEqual().put("paystatus", "05");
			}
            result = rechargeService.queryOilCardRechargeList(requestParam, index);
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询油卡充值记录：======", e);
        }
        return result;
    }
	
    /**
     * 充值总量统计
     * @return
     */
    @ResponseBody
    public PaginationResult<?> getRechargeTotal(){
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            BigDecimal countOilCardRecharge = rechargeService.countOilCardRecharge(requestParam, index);
            result.setDataObject(NumberUtil.converMoneyDefaultFormat(countOilCardRecharge));
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            result.setDataObject(0);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询充值总量统计异常：======", e);
        }
        return result;
    }
}