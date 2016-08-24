package com.ctfo.yppt.portal.service.datastat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.baseservice.system.beans.UserStat;
import com.ctfo.yppt.baseservice.system.intf.IUserStatManager;
/**
 * 
 * 用户数据统计相关业务服务类
 * 
 * @author fuxiaohui
 *
 * @datetime 2014-12-16 下午12:01:34
 *
 */
@Service("userStatService")
@AnnotationName(name = "用户数据统计业务服务")
public class UserStatServiceImpl implements IUserStatService {
    private static Log logger = LogFactory.getLog(UserStatServiceImpl.class);

    private static IUserStatManager userStatManager;
    
    // 构造方法，将后台对象实例化
    private  UserStatServiceImpl() {
    }
    static {
        logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
        userStatManager = (IUserStatManager) ServiceFactory.getFactory().getService(IUserStatManager.class);
        logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
    }
    /**
     * 查询用户数据统计
     * @param requestParam
     * @return
     * @throws AAException
     */
    @AnnotationName(name = "查询用户数据统计")
    @Override
    public PaginationResult<UserStat> queryUserStat(String userId) throws AAException {
        PaginationResult<UserStat> result = new PaginationResult<UserStat>();
        try {
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
            if (StringUtils.isNotBlank(userId)) {
                result.setMessage(GlobalMessage.SUCCESS);
                result.setDataObject(userStatManager.getUserstatByUserId(userId));
            } else {
                result.setMessage(GlobalMessage.FAIL);
                result.setDataObject(Constants.OPER_FAIL);
                logger.debug(">>>>>>>>账户所属用户ID为空【requestParam.equal.userId=" + userId + "】");
            }
            logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return result;
    }
}
