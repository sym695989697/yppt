package com.ctfo.yppt.portal.action.rebate;

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
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.portal.service.datastat.IUserStatService;
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
public class RebateController extends BaseControler{
	
	@Resource(name="rebateService" ,description= "油卡返利管理service")
	IRebateService rebateService;
	@Resource(name="userStatService" ,description= "用户数据统计service")
    IUserStatService userStatService;
	private static Log logger = LogFactory.getLog(RebateController.class);

    /**
     * 查询油卡返利记录
     * 
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryOilCardRebateList() {
        if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            result = rebateService.queryOilCardRebateList(requestParam, index.getUserId());
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (AAException e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询油卡返利记录异常：======", e);
        }
        return result;
    }
	
	/**
	 * 查询油卡返利进出账总额
	 * @return
	 */
	@ResponseBody
	public PaginationResult<?> getOilCardRebateIO(){
	    if (requestParam == null) {
            requestParam = new DynamicSqlParameter();
        }
        if (requestParam.getEqual() == null) {
            requestParam.setEqual(new HashMap<String, String>());
        }
        try {
            result = rebateService.queryOilCardRebateIO(requestParam, index);
            result.setDataObject(Constants.OPER_SUCCESS);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询油卡返利进出账总额异常：======", e);
        }
        return result;
	}
	
   
}