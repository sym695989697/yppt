package com.ctfo.yppt.baseservice.card.intf;

import java.util.List;
import java.util.Map;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.card.beans.ICCardApply;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLog;
import com.ctfo.yppt.bean.ICCardApplyDetailExtend;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;

/**
 * 
 * 
 * IC卡申请管理.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
public interface IICCardApplyManager extends
        IBaseManager<ICCardApply, ICCardApplyExampleExtended> {

    /**
     * 
     * 
     * 新增IC卡申请
     * 
     * @param icCardApplyMetaBean 包含卡申请对象和卡申请详情对象; 申请卡的信息在卡申请详情对象中
     * @return applyId 卡申请表的id; 操作成功时,返回id;失败时,抛出异常!
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    String addIcCardApply(ICCardApplyMetaBean icCardApplyMetaBean)
            throws BusinessException;

    /**
     * 
     * 
     * IC卡申请审核功能
     * 
     * @param icCardApplyMetaBean 包含卡申请对象和卡申请详情对象; 申请卡的信息在卡申请详情对象中
     * @param auditLog 审核日志
     * @return 0: 审核成功; 1: 参数为空!
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    int auditingIcCardApply(ICCardApplyMetaBean icCardApplyMetaBean,
            ICCardApplyProcessLog auditLog) throws BusinessException;

    /**
     * 
     * 
     * 修改IC卡申请
     * 
     * @param icCardApplyMetaBean
     * @return
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    int updateIcCardApply(ICCardApplyMetaBean icCardApplyMetaBean)
            throws BusinessException;

    /**
     * 
     * 
     * 根据ID查询申请记录(返回复杂实体集合，单实体集合调用基类方法).
     * 
     * @param id
     * @return
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    ICCardApplyMetaBean getIcCardApplyMetaBean(String id)
            throws BusinessException;

    /**
     * 
     * 
     * 查询IC卡申请操作日志记录.
     * 
     * @param applyId
     * @return
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    List<ICCardApplyProcessLog> queryICCardApplyProcessLogByApplyID(
            String applyId) throws BusinessException;

    /**
     * 
     * 
     * 根据ID查询IC卡申请操作日志记录.
     * 
     * @param id
     * @return
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    ICCardApplyProcessLog getICCardApplyProcessLog(String id)
            throws BusinessException;

    /**
     * 
     * 
     * 批量开卡服务.
     * 
     * @param iCCardApplyMetaBean
     * @param auditLog
     * @return
     * @throws BusinessException
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月21日    dxs    新建
     * </pre>
     */
    Map<String, String> addCardImportBatch(
            ICCardApplyMetaBean iCCardApplyMetaBean,
            ICCardApplyProcessLog auditLog) throws BusinessException;

    /**
     * 
     * 
     * 根据申请单明细ID查询申请单明细记录(返回复杂实体集合，单实体集合调用基类方法).
     * 
     * @param applyDetailId 申请单明细ID
     * @return
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月20日    dxs    新建
     * </pre>
     */
    ICCardApplyMetaBean getIcCardApplyDetail(String applyDetailId)
            throws BusinessException;
    
    
    /**
     * 
     * 
     * 根据用户UAAID查询处理中卡片信息.
     * 
     * @param param 目前参数为（用户UAAID=userId，卡号/车牌号手机号=condition
     *            ,开始数=start,每页条数=limit）
     * @return 返回
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月26日    dxs    新建
     * </pre>
     */
    PaginationResult<ICCardApplyDetailExtend> queryProcessCard(
            Map<String, String> param) throws BusinessException;

    /**
     * 
     * 
     * 根据用户参数查询所有申请信息.
     * 
     * @param param 目前参数为（用户UAAID=userId , 开始数=start,每页条数=limit
     * @return 返回申请信息列表（成功的卡片，含有卡余额信息）
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月26日    dxs    新建
     * </pre>
     */
    PaginationResult<ICCardApplyDetailExtend> queryUserAllAplly(
            Map<String, String> param) throws BusinessException;
    
    /**
     * 
     * <pre>
     *   根据用户参数查询申请信息.
     *   目前参数为（
     *     用户UAAID=userId , 开始数=start , 每页条数=limit ,
     *     要过滤的状态=cardStateNotIn ,需要的状态=cardStateIn , 
     *     卡号/车牌号手机号=condition 
     *   )
     * </pre>
     * 
     * @param param 
     * @return 返回申请信息列表（成功的卡片，含有卡余额信息）
     * 
     *         <pre>
     * 修改日期        修改人    修改原因
     * 2015年1月26日    dxs    新建
     * </pre>
     */    
    public PaginationResult<ICCardApplyDetailExtend> queryUserAplly(
            Map<String, Object> param) throws BusinessException;
    
    /**
     * 
     *
     * 统计用户申请中卡数量(不包括状态为（不通过，成功)
     *
     * @param param（用户UAAID=userId)
     * @return
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月2日    dxs    新建
     * </pre>
     */
    public Integer countProcessCard(Map<String, String> param)
            throws BusinessException;
    /**
     * 
     *
     * 统计用户申请失败的卡数量（不通过)
     *
     * @param param（用户UAAID=userId)
     * @return
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月2日    dxs    新建
     * </pre>
     */
    public Integer countFailCard(Map<String, String> param) throws BusinessException;

}
