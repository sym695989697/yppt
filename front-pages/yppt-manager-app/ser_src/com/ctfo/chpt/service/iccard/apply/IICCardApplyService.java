package com.ctfo.chpt.service.iccard.apply;


import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.yppt.baseservice.card.beans.ICCardApply;

public interface IICCardApplyService extends IService {
	/**
	 * 
	 * @Description 卡申请记录
	 * @param requestParam
	 * @return
	 * @throws Exception
	 */
	public PaginationResult<?> getListPage(DynamicSqlParameter requestParam) throws Exception;
	/**
	 * 
	 * @Description 审核
	 * @param model
	 * @return
	 * @throws AAException
	 */
	public PaginationResult check(ICCardApply model,Index index,String cards) throws Exception;
	/**
	 * 当审核时所查询的信息
	 * @param model
	 * @param index 通过登录用户的id对本次审核进行绑定
	 * @return
	 * @throws Exception
	 */
	public PaginationResult<?> queryApplyMeta(ICCardApply model, Index index) throws Exception;
	/**
	 * 判断卡是否存在 
	 * @param cardNo
	 * @return
	 */
	public PaginationResult<?> checkICCardNum(String cardNo,String openCardOrgCode);
	/**
	 * 查看详情
	 * @param apply
	 * @return
	 */
	public PaginationResult<?> getApplyInfo(ICCardApply apply)throws Exception;
	/**
	 * 获取图片的大小
	 * @param imageName
	 * @param size
	 * @return
	 */
	public String getPicPath(String imageName,String size);
}
