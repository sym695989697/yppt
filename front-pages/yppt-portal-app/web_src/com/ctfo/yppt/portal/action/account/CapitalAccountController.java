package com.ctfo.yppt.portal.action.account;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.yppt.portal.service.account.ICapitalAccountService;
/**
 * 资金账户管理控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-23 下午03:31:00
 *
 */
@Controller
@Scope("prototype")
public class CapitalAccountController extends BaseControler{
    private static Log logger = LogFactory.getLog(CapitalAccountController.class);
	@Resource(name="capitalAccountService" ,description= "资金账户管理service")
	ICapitalAccountService capitalAccountService;
	
    /**
     * 获取资金账户可用余额(格式化为金额:0.00元)
     * 
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryCapitalAccountBalanceavailableFormat() {

        result.setDataObject(0);
        try {
            String creditAccountBalance = capitalAccountService.queryCapitalAccountBalanceavailable(index.getUserId());
            result.setDataObject(creditAccountBalance);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (Exception e) {
            result.setDataObject(0);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======获取资金账户可用余额(格式化为金额:0.00元)异常：======", e);
        }
        return result;
    }
	
    
}