package com.ctfo.yppt.portal.action.oilcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;
import com.ctfo.yppt.portal.service.datastat.IUserStatService;
import com.ctfo.yppt.portal.service.oilcard.IOilCardsService;
import com.ctfo.yppt.portal.service.rebate.IRebateService;


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
public class OilCardController extends BaseControler{
	
	@Resource(name="oilCardService" ,description= "油卡管理service")
	IOilCardsService oilCardService;
	@Resource(name="userStatService" ,description= "用户数据统计service")
    IUserStatService userStatService;
	@Resource(name="rebateService" ,description= "油卡管理service")
	IRebateService rebateService;
	private static Log logger = LogFactory.getLog(OilCardController.class);

	/**
	 * 查询我的油卡列表
	 * @return
	 */
    @ResponseBody
    public PaginationResult<?> queryCardList() {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            result = oilCardService.queryCardListPage(requestParam, index.getUserId());
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            logger.error("======controller层======查询我的油卡列表数据异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
    }
    /**
	 * 查询我的油卡申请列表
	 * @return
	 */
    @ResponseBody
    public PaginationResult<?> queryCardListApply() {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            result = oilCardService.queryCardListPageApply(requestParam, index.getUserId());
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            logger.error("======controller层======查询我的油卡列表数据异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
    }
    /**
     * 查询办理中的油卡详情
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryOilCardDetail(@RequestParam("cardApplyDetailId") String cardApplyDetailId) {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            result = oilCardService.queryOilCardDetail(cardApplyDetailId);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            logger.error("======controller层======查询我的油卡列表数据异常：======", e);
            result.setMessage(GlobalMessage.FAIL);
            result.setDataObject(Constants.OPER_ERROR);
        }
        return result;
    }
	
	/**
     * IC卡开卡申请
     * @param iCCradApply
     * @return
     */
    @ResponseBody
    public PaginationResult<?> openCardApply(ICCardApplyMetaBean icCardApplyMetaBean){
        try {
            if(oilCardService.openCardApply(icCardApplyMetaBean, index) != null){
                result.setDataObject(Constants.OPER_SUCCESS);
                result.setMessage(GlobalMessage.SUCCESS);
            }else{
                result.setDataObject(Constants.OPER_FAIL);
                result.setMessage(GlobalMessage.FAIL);
            }
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======IC卡开卡申请异常：======", e);
        }
        return result;
    }
	
    /**
     * 初始化油品首页 我的油卡数量 、累计加油量、上月返利数据
     * 
     * @return
     * @author fuxiaohui
     */
    @ResponseBody
    public PaginationResult<?> getOilHomePageData() {
        Map<String, Object> map = new HashMap<String, Object>();
        List list = new ArrayList<HashMap<String, Object>>();
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        //统计我的油卡数量
        Integer countCard = 0;
        //统计累计加油量
        Integer countOil = 0;
        //上月累计返利
        String countCredit = "0";
        try {
            //统计我的油卡数量
            countCard = oilCardService.countOilCardByUserId(index.getUserId());
            //统计累计加油量
            DynamicSqlParameter dsp = new DynamicSqlParameter();
            dsp.setPage(1);
            dsp.setPagesize(10);
            dsp.setRows(10);
            PaginationResult<UserStat> oilResult =  userStatService.queryUserStat(index.getUserId());
            if(oilResult != null && oilResult.getDataObject() != null) {
            	UserStat u = (UserStat) oilResult.getDataObject();
                if(u.getOilNum() != null) {
                    countOil = u.getOilNum().intValue();
                }
            }
            //上月累计返利
            countCredit =  rebateService.queryOilCardRebatePreMonth(requestParam, index).toString();
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======初始化油品首页 我的油卡数量 、累计加油量、上月返利数据异常：======", e);
        } finally {
            map.put("countOil", countOil);
            map.put("countCard", countCard);
            map.put("countCredit", countCredit);
            list.add(map);
            result.setData(list);
        }
        return result;
    }
	
    /**
     * 查询当前用户收件人相关信息
     * @return
     */
    @ResponseBody
    public PaginationResult<?> getReceiverInfo() {
        try {
            String receiver = String.format("（收件人：%s %s）", index.getUserName(), index.getMobile());
            result.setDataObject(receiver);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询当前用户收件人相关信息异常：======", e);
        }
        return result;
    }
    /**
     * 更新卡信息
     * @return
     */
    @ResponseBody
    public PaginationResult<?> updateCard(ICCard iccard){
    	  try {
    		  if(oilCardService.updateCard(iccard,index)){
    			  result.setDataObject(Constants.OPER_SUCCESS);
                  result.setMessage(GlobalMessage.SUCCESS);
    		  }else{
    			  result.setDataObject(Constants.OPER_ERROR);
                  result.setMessage(GlobalMessage.FAIL);
    		  }
          } catch (Exception e) {
        	   result.setDataObject(Constants.OPER_ERROR);
               result.setMessage(GlobalMessage.FAIL);
              logger.error("======controller层======更新卡信息异常：======", e);
          }
    	return result;
    }
   
}