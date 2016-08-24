package com.ctfo.base.service.intf;


/**
 * 数据统计接口Manager
 * @author fuxiaohui
 *
 */
public interface IStatDataManager{
    /**
     * 根据用户ID查询 积分账户余额
     * @param userId 用户id(统一认证服务uuids)
     * @return 旺金币余额
     * @throws ICCardException
     */
    Long queryCreditBalanceByUserId(String userId) throws Exception;
    /**
     * 根据用户ID查询油卡余额
     * @param userId 用户id(统一认证服务uuids)
     * @return 卡余额
     * @throws ICCardException
     */
    Long queryAllUserCardsBalanceByUserId(String userId) throws Exception;
}
