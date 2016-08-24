package com.ctfo.yppt.portal.service.billing;

import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.crm.boss.beans.UserInvoiceInfo;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApply;
import com.ctfo.yppt.portal.view.billing.BillingApplyVo;
import com.ctfo.yppt.portal.view.billing.BillingInfoVo;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-portal-app <br>
 * 功能：发票业务服务service层接口 <br>
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
@AnnotationName(name = "发票业务服务接口")
public interface IBillingService extends IService {
    /**
     * 新增发票信息
     * @param billingInfoVo
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="新增发票信息")
    String addBillingInfo(BillingInfoVo billingInfoVo, Index index)throws AAException;

    /**
     * 新增开票申请
     * @param billingInfoVo
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="新增开票申请")
    String billingApply(BillingApplyVo billingApplyVo, Index index) throws AAException;
    /**
     * 查询开票信息列表
     * @param requestParam
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="查询开票信息列表")
    PaginationResult<UserInvoiceInfo> queryBillingInfoList(DynamicSqlParameter requestParam, Index index) throws AAException;
    /**
     * 查询开票记录列表
     * @param requestParam
     * @param index
     * @return
     * @throws AAException
     */
    @AnnotationName(name="查询开票记录列表")
    PaginationResult<ICCardInvoiceApply> queryBillingApplyList(DynamicSqlParameter requestParam, Index index);

    /**
     * 
     * <p>方法描述: 根据参数查询累计开票金额</p>
     *
     * <p>方法备注: 根据参数查询累计开票金额</p>
     *
     * @param id
     * 
     * @return
     *
     * <p>创建人：李飞</p>
     *
     * <p>创建时间：2015年1月31日 下午6:35:35</p>
     *
     */
	PaginationResult<?> getTotalOpenMoney(String id);

	
	/**
	 * 
	 * <p>方法描述: 根据参数ID查询开票信息</p>
	 *
	 * <p>方法备注: 根据参数ID查询开票信息</p>
	 *
	 * @param model 发票信息实体
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月31日 下午7:21:02</p>
	 *
	 */
	PaginationResult<?> getBillingApplyById(String id);

	/**
	 * 
	 * <p>方法描述: 删除发票信息</p>
	 *
	 * <p>方法备注: 删除发票信息</p>
	 *
	 * @param id
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年2月3日 下午6:48:09</p>
	 *
	 */
	PaginationResult<?> deleteBillingInfoById(String id);
}
