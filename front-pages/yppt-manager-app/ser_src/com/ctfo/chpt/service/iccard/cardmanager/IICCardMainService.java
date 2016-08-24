package com.ctfo.chpt.service.iccard.cardmanager;

import java.util.List;

import com.ctfo.base.service.IService;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;

/**
 * 
 * 项目名称：yppt-manager-app<br>
 * *********************************<br>
 * <P>类名称：MainCardService</P>
 * *********************************<br>
 * <P>类描述：主卡管理功能App-Service接口</P>
 * 创建人：李飞<br>
 * 创建时间：2015年1月21日 下午3:08:49<br>
 * 修改人：李飞<br>
 * 修改时间：2015年1月21日 下午3:08:49<br>
 * 修改备注：<br>
 * @version 1.0<br>
 */
public interface IICCardMainService extends IService {

	/**
	 * 
	 * <p>方法描述: 批量删除主卡信息</p>
	 *
	 * <p>方法备注: 批量删除主卡信息</p>
	 *
	 * @param itmes  主卡信息ID的集合
	 * 
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月26日 下午6:19:20</p>
	 *
	 */
	public int deleteBatch(List<ICCardMain> itmes);
	
	/**
	 * 
	 * <p>方法描述: 刷新主卡的卡余额信息</p>
	 *
	 * <p>方法备注: 刷新主卡的卡余额信息</p>
	 *
	 * @param model  为当前主卡实例对象：其中id不能为空
	 * 
	 * @return int 1:刷新成功  0:刷新失败
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月26日 下午6:20:06</p>
	 *
	 */
	public int refreshBalance(Object model);

}
