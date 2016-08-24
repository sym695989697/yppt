package com.ctfo.yppt.portal.service.recharge;

import java.math.BigDecimal;

import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
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
@AnnotationName(name = "油卡业务服务接口")
public interface IRechargeService extends IService {
    /**
     * 提交充值方式
     * @param cardpay
     * @return
     * @throws AAException
     */
    public OilCardChargeResultVo rechargeApply(OilCardPayVo cardpay,OilCardChargeVos cardList,Index index)throws AAException;
    /**
     * 查询油卡充值记录
     * @param requestParam
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="查询油卡充值记录")
    PaginationResult<?> queryOilCardRechargeList(DynamicSqlParameter requestParam, Index index);
    /**
     * 统计累计油卡充值
     * @param requestParam
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="统计累计油卡充值")
    BigDecimal countOilCardRecharge(DynamicSqlParameter requestParam, Index index);
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 根据id查询充值详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
    public ICRechargeApply getApplyRechargeById(String id) throws Exception;
}
