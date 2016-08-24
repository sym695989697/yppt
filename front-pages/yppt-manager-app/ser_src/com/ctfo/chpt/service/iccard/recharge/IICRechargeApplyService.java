package com.ctfo.chpt.service.iccard.recharge;

import org.springframework.web.multipart.MultipartFile;

import com.ctfo.base.service.IService;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.bean.ICCardRechageApplyMetaBean;
import com.ctfo.yppt.bean.ICardRechageMetaBean;

public interface IICRechargeApplyService extends IService {
	/**
	 * 
	 * @Description 充值申请记录分页
	 * @param requestParam
	 * @return
	 * @throws Exception
	 */
	public PaginationResult<?> getListPage(DynamicSqlParameter requestParam) throws Exception;
	
	/**
	 * 通过充值申请id 获取大bean的信息(用于处理)
	 * @param applyId
	 * @return DataObject中的值为0表示为有审核人;
	 */
	public PaginationResult getICCardRechageApplyMetaByApplyId(Index index,String applyId)throws Exception;
	/**
	 * 充值申请
	 * @param model
	 * @return 返回订单号
	 * @throws Exception
	 */
	public String applyManagerRecharge(ICCardRechageApplyMetaBean model,Index index)throws Exception;
	/**
	 * 处理充值申请 
	 * @param model
	 * @return 返回错误代码
	 * @throws Exception
	 */
	public String checkRechargeApply(MultipartFile file,String applyId,Index index)throws Exception;
	/**
	 * 查看
	 * @param applyId
	 * @return 将大bean放到了DataObject中
	 */
	public PaginationResult queryICCardRechageApplyInfoByApplyId(String applyId) throws Exception;
	/**
	 * 获取图片的全路径
	 * @param imageName 图片名称
	 * @param size 大图片或小图片
	 * @return 图片的全路径
	 */
	public String getPicPath(String imageName,String size);
	
}
