package com.ctfo.yppt.portal.service.rebate;

import java.math.BigDecimal;

import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.gatewayservice.interfaces.bean.credit.CreditIO;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.bean.ICCardApplyDetailExtend;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;
import com.ctfo.yppt.portal.view.oilcard.OilCardChargeResultVo;
import com.ctfo.yppt.portal.view.oilcard.OilCardChargeVos;
import com.ctfo.yppt.portal.view.oilcard.OilCardPayVo;

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
@AnnotationName(name = "油卡返利服务接口")
public interface IRebateService extends IService {
	/**
	 * 查询油卡返利记录
	 * @param requestParam
	 * @param userId
	 * @return
	 * @throws AAException
	 */
    @AnnotationName(name="查询油卡返利记录")
	public PaginationResult<CreditIO> queryOilCardRebateList(DynamicSqlParameter requestParam, String userId)throws AAException;
	/**
	 * 查询油卡返利进出账总额
	 * @param requestParam
	 * @param index
	 * @return
	 * @throws AAException
	 */
    @AnnotationName(name="查询油卡返利进出账总额")
	public PaginationResult<CreditIO> queryOilCardRebateIO(DynamicSqlParameter requestParam, Index index)throws AAException;
	
	/**
     * 上月累计油卡返利
     * @param requestParam
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="上月累计油卡返利")
	public BigDecimal queryOilCardRebatePreMonth(DynamicSqlParameter requestParam, Index index)throws AAException;
}
