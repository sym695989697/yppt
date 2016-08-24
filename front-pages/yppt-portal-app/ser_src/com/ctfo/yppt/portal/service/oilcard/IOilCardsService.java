package com.ctfo.yppt.portal.service.oilcard;

import java.math.BigDecimal;

import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.bean.ICCardApplyDetailExtend;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;
import com.ctfo.yppt.portal.view.oilcard.ICCardVo;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-portal-app <br>
 * 功能：油卡业务服务service层接口 <br>
 * 描述：此类继承IService <br>
 * 授权 : (C) Copyright (c) 2015 <br>
 * 公司 : 北京中交兴路车联网科技有限公司 <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史 <br>
 * <table width="432" border="1">
 * <tr>
 * <td>版本</td>
 * <td>时间</td>
 * <td>作者</td>
 * <td>改变</td>
 * </tr>
 * <tr>
 * <td>1.0</td>
 * <td>2015-1-20</td>
 * <td>fuxiaohui</td>
 * <td>创建</td>
 * </tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font> <br>
 * 
 * @version 1.0
 * 
 * @author fuxiaohui
 * 
 * @since JDK1.6
 */
@AnnotationName(name = "油卡业务服务接口")
public interface IOilCardsService extends IService {
	/**
	 * 开卡申请
	 * @param icCrad
	 * @param userId
	 * @return
	 * @throws AAException
	 */
    @AnnotationName(name = "开卡申请")
	public String openCardApply(ICCardApplyMetaBean icCardApplyMetaBean,Index index)throws AAException;
	/**
	 * 查询油卡列表
	 * @param requestParam
	 * @param userId
	 * @return
	 * @throws AAException
	 */
	@AnnotationName(name = "查询油卡列表")
    PaginationResult<ICCardVo> queryCardListPage(DynamicSqlParameter requestParam, String userId) throws AAException;
	/**
	 * 查询油卡列表
	 * @param requestParam
	 * @param userId
	 * @return
	 * @throws AAException
	 */
	@AnnotationName(name = "查询油卡申请列表")
    PaginationResult<ICCardApplyDetailExtend> queryCardListPageApply(DynamicSqlParameter requestParam, String userId) throws AAException;
	/**
	 * 根据用户ID统计用户油卡数量
	 * @param requestParam
	 * @param index
	 * @return
	 * @throws AAException
	 */
	@AnnotationName(name="根据用户ID统计用户油卡数据")
    Integer countOilCardByUserId(String userId)throws AAException;
	/**
	 * 查询油卡消费（加油）记录（实时）
	 * @param requestParam
	 * @param index
	 * @return
	 * @throws AAException
	 */
	@AnnotationName(name="查询油卡消费（加油）记录（实时）")
    PaginationResult<ICCardTradeInfo> queryOilCardTradeInfoList(DynamicSqlParameter requestParam, Index index) throws AAException;
	/**
     * 查询油卡消费（加油）记录（历史）
     * @param requestParam
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="查询油卡消费（加油）记录（历史）")
    PaginationResult<ICCardTradeInfoHistory> queryOilCardTradeInfoHistoryList(DynamicSqlParameter requestParam, Index index) throws AAException;
    /**
     * 统计油卡消费（加油）记录（实时）
     * @param requestParam
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="统计油卡消费（加油）记录（实时）")
    BigDecimal countOilCardTradeInfo(DynamicSqlParameter requestParam, Index index) throws AAException;
    /**
     * 统计油卡消费（加油）记录（历史）
     * @param requestParam
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="统计油卡消费（加油）记录（历史）")
    BigDecimal countOilCardTradeInfoHistory(DynamicSqlParameter requestParam, Index index) throws AAException;
    /**
     * 查询办理中的油卡详情
     * 
     * @param requestParam
     * @param userId
     * @return
     */
    @AnnotationName(name="查询办理中的油卡详情")
    public PaginationResult<?> queryOilCardDetail(String cardApplyDetailId);
    
    /**
     * 更新卡信息
     * @param iccard
     * @param index
     * @return
     * @throws AAException
     */
    public boolean updateCard(ICCard iccard,Index index)throws AAException;
}
