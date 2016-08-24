package com.ctfo.yppt.baseservice.recharge.intf;

import java.math.BigDecimal;
import java.util.Map;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardRechangeProcessLog;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyExampleExtended;
import com.ctfo.yppt.bean.CreateAccountBean;
import com.ctfo.yppt.bean.ICCardRechageApplyExtend;
import com.ctfo.yppt.bean.ICCardRechageApplyMetaBean;
import com.ctfo.yppt.bean.ICardRechageMetaBean;
/** 
 * @author rao yongbing 
 * @Description 增加调用：add  
 *              删除调用：remove  
 *              修改调用：update 
 *              查询单个对象调用：getById 
 *              分页查询调用：paginate 
 *              查询所有记录调用 ：getList
 * 2015年1月20日
 */
public interface IICRechargeApplyManager extends IBaseManager<ICRechargeApply, ICRechargeApplyExampleExtended>  {
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 处理无效订单
	 * @param exptime有效期
	 * @return
	 * @throws BusinessException
	 */
	
	public Long changeUselessRechargeApplyTask(String exptime) throws BusinessException;
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 操作员点击处理后锁定该充值申请
	 * @param applyId 充值申请Id
	 * @param pid 操作人Id
	 * @param pname操作人姓名
	 * @return
	 * @throws BusinessException
	 */
	public int auditRechargeLock(String applyId,String pid,String pname)throws BusinessException;
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 充值审核
	 * @param rechageMetaBean 
	 * @param processLog 操作日志
	 * @return
	 * @throws BusinessException
	 */
	public int  auditRecharge(ICardRechageMetaBean rechageMetaBean,ICCardRechangeProcessLog processLog) throws BusinessException ;
	
	
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 根据充值申请id查询rechageMetaBean;
	 * @param id 充值申请id
	 * @return
	 * @throws BusinessException
	 */
	public ICardRechageMetaBean getICardRechageMetaBeanByapplyId(String id)throws BusinessException;

//	/**
//	 * 
//	 * @author rao yongbing 
//	 * @Description 分页取得充值记录
//	 * @param params
//	 * @return
//	 * @throws BusinessException
//	 */
//	public PaginationResult<?> paginateRechargeApply(Map<String,Object> params)throws BusinessException;

	/**
     * 
     * @author rao yongbing 
     * @Description		取得交易记录（充值、加油当月与历史记录）
     * @param params
     * @return
     * @throws BusinessException
     */
	public PaginationResult<?> paginateRechargeAndTradeInfoForAPP(Map<String,Object> params)throws BusinessException;
    /**
     * 
     * @author rao yongbing 
     * @Description 分页查询充值信息
     * @param params page    第几页
			         pageSize  每页行数
			         userId   用户id
			         threeSwitch  卡号/车牌号/电话号
			         startTime   开始时间
			         endTime     结束时间
			         status  充值状态
     * @return
     * @throws BusinessException
     */
	public PaginationResult<?> paginateRecharge(Map<String, Object> params) throws BusinessException;
     /**
      * 
      * @author rao yongbing 
      * @Description 统计充值总额
      * @param params
      * @return
      * @throws BusinessException
      */
	public  BigDecimal countRecharge(Map<String, Object> params) throws BusinessException;

	 /**
     * 
     * @author rao yongbing 
     * @Description 统计充值总额
     * @param params
     * @return
     * @throws BusinessException
     */
	public  BigDecimal sumRecharge(Map<String, Object> params) throws BusinessException;

	 /**
     * 
     * @author rao yongbing 
     * @Description 统计单张卡充值总额
     * @param params cardId 卡id
     *               startTime 开始时间
     *               endTime 结束时间
     * @return
     * @throws BusinessException
     */
	public  BigDecimal countSingleRecharge(Map<String, Object> params) throws BusinessException;
	
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 后台充值申请
	 * @param obj
	 * @return applyId
	 * @throws BusinessException
	 */
	public String applyRechargeForManagerApp(ICCardRechageApplyMetaBean model)throws BusinessException;
	
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 充值
	 * @param obj
	 * @return
	 * @throws BusinessException
	 */
	public ICCardRechageApplyExtend applyRecharge(ICCardRechageApplyMetaBean model)throws BusinessException;
	
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 充值(移动app)
	 * @param obj
	 * @return
	 * @throws BusinessException
	 */
	public ICCardRechageApplyExtend applyRechargeForApp(ICCardRechageApplyMetaBean model)throws BusinessException;
	
	
	
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 调用网关，构造请求内容
	 * @param obj
	 * @return
	 * @throws BusinessException
	 */
	public String buildRequestContent(String applyId,String userId,String userLoginName,String orderNo,String mark,BigDecimal totalMoney)throws BusinessException;

	/**
	 * 
	 * @author rao yongbing 
	 * @Description 网关回调，修改订单状态
	 * @param orderNO 业务订单号
	 * @param rslt 结果
	 * @param money 金额
	 * @param payOrderNo 支付订单号
	 * @return  支付订单号
	 * @throws BusinessException
	 */
	public String callBackPay(String orderNo,String rslt,BigDecimal money,String payOrderNo,String payWay) throws BusinessException;
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 网关回调，修改订单状态(移动app)
	 * @param orderNO 业务订单号
	 * @param rslt 结果
	 * @param money 金额
	 * @param payOrderNo 支付订单号
	 * @return  支付订单号
	 * @throws BusinessException
	 */
	public String callBackPayForApp(String orderNo,String rslt,BigDecimal money,String payOrderNo,String payWay) throws BusinessException;
	
}
