package com.ctfo.yppt.portal.action.datastat;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.portal.service.datastat.IUserStatService;
/**
 * 
 * 用户数据统计相关业务控制类
 * 
 * @author fuxiaohui
 *
 * @datetime 2014-12-16 上午11:53:17
 *
 */
@Controller
@Scope("prototype")
public class UserStatController extends BaseControler {
    private static Log logger = LogFactory.getLog(UserStatController.class);
    @Resource(name="userStatService" ,description= "用户数据统计service")
    IUserStatService userStatService;
    /**
     * 查询当前用户数据统计信息
     * @return
     */
    @ResponseBody
    public PaginationResult<?> queryUserStat() {
        try {
            result = userStatService.queryUserStat(index.getUserId());
        } catch (AAException e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error("======controller层======查询当前用户数据统计信息异常：======", e);
        }
        return result;
    }
}
