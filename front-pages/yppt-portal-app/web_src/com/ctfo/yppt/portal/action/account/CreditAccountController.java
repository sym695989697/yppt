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
import com.ctfo.common.utils.NumberUtil;
import com.ctfo.yppt.portal.service.account.ICreditAccountService;

/**
 * 积分账户相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:29:09
 *
 */
@Controller
@Scope("prototype")
public class CreditAccountController extends BaseControler{
	
	@Resource(name="creditAccountService" ,description= "旺金币service")
	ICreditAccountService creditAccountService;
	private static Log logger = LogFactory.getLog(CreditAccountController.class);

    /**
     * 获取旺金币可用积分
     * 
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryCreditAccountBalanceavailable() {

        result.setDataObject(0);
        try {
            Long creditAccountBalance = creditAccountService.queryCreditAccountBalanceavailable(index.getUserId());
            result.setDataObject(creditAccountBalance);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (Exception e) {
            result.setDataObject(0);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======获取旺金币可用积分异常：======", e);
        }
        return result;
    }
    
    /**
     * 获取旺金币可用余额(格式化为金额:0.00元)
     * 
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryCreditAccountBalanceavailableFormat() {

        result.setDataObject(0);
        try {
            Long creditAccountBalance = creditAccountService.queryCreditAccountBalanceavailable(index.getUserId());
            result.setDataObject(NumberUtil.converMoneyDefaultFormat(creditAccountBalance));
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (Exception e) {
            result.setDataObject(0);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======获取旺金币可用余额(格式化为金额:0.00元)异常：======", e);
        }
        return result;
    }
}