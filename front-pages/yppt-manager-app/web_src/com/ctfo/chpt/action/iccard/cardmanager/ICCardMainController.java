package com.ctfo.chpt.action.iccard.cardmanager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.service.iccard.cardmanager.ICCardMainServiceImpl;
import com.ctfo.chpt.service.iccard.cardmanager.IICCardMainService;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;

/**
 * 
 * 项目名称：yppt-manager-app<br>
 * *********************************<br>
 * <P>类名称：MainCardController</P>
 * *********************************<br>
 * <P>类描述：主卡功能逻辑Controller：MainCardController</P>
 * 创建人：李飞<br>
 * 创建时间：2015年1月21日 下午1:12:15<br>
 * 修改人：李飞<br>
 * 修改时间：2015年1月21日 下午1:12:15<br>
 * 修改备注：<br>
 * @version 1.0<br>
 */

@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/mainCard")
public class ICCardMainController extends BaseControler{
	
	private static Log logger = LogFactory.getLog(ICCardMainController.class);
	
	public ICCardMainController(){
		model=new ICCardMain();
		service=new ICCardMainServiceImpl();
	}
	
	
	/**
	 * 
	 * <p>方法描述: 分页查询主卡信息</p>
	 *
	 * <p>方法备注: 分页查询主卡信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午1:25:06</p>
	 *
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getMainCardListPage" ,produces = "application/json")
	@ResponseBody
	public PaginationResult getMainCardListPage() {
		try {
			result = (PaginationResult<?>)((IICCardMainService)service).queryListPage(requestParam);
		} catch (Exception e) {
			forwardError(((e.getMessage() == null || e.getMessage().isEmpty() || e.getMessage().equals(EMPTY_STRING)) ? "查询信息集合异常!" : e.getMessage()));
			logger.error("查询信息集合异常!", e);
		}
		return result;
	}
	
	/**
	 * 
	 * <p>方法描述: 添加主卡信息</p>
	 *
	 * <p>方法备注: 添加主卡信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午3:04:46</p>
	 *
	 */
	@RequestMapping(value="/addMaincard" ,produces = "application/json")
	@ResponseBody
	public PaginationResult<?> addMaincard(){
		
		try {
			
			((ICCardMain)model).setModifinguser(index.getUserId());
			((ICCardMain)model).setCtrdituser(index.getUserId());
			result = ((IICCardMainService)service).add(model);
			
		} catch (BusinessException e) {
			logger.error("添加主卡异常!", e);
			result.setMessage(e.getMessage());
		}
		 return result;
	}
	
	/**
	 * 
	 * <p>方法描述: 修改主卡信息</p>
	 *
	 * <p>方法备注: 修改主卡信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午3:04:46</p>
	 *
	 */
	@RequestMapping(value="/updateMaincard" ,produces = "application/json")
	@ResponseBody
	public PaginationResult<?> updateMaincard(){
		 try {
			 
			 ((ICCardMain)model).setModifinguser(index.getUserId());
			 result = ((IICCardMainService)service).update(model);
			
		} catch (BusinessException e) {
			logger.error("修改主卡异常!", e);
			result.setMessage(e.getMessage());
		}
		 return result;
	}
	
	/**
	 * 
	 * <p>方法描述: 删除主卡信息</p>
	 *
	 * <p>方法备注: 删除主卡信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午3:04:46</p>
	 *
	 */
	@RequestMapping(value="/deleteMaincard" ,produces = "application/json")
	@ResponseBody
	public String deleteMaincard(String ids){
		List<ICCardMain> itmes = null;
		StringBuilder result = new StringBuilder("");
		 try {
			 if(StringUtils.isNotEmpty(ids.trim())){
				 String[] ary = ids.split(";");
				 if(ary.length>0){
					 itmes = new ArrayList<ICCardMain>();
					 for(int i=0;i<ary.length;i++){
						 ICCardMain carMain = new ICCardMain();
						 carMain.setId(ary[i]);
						 itmes.add(carMain);
					 }
				 }
				int flag = ((IICCardMainService)service).deleteBatch(itmes);
				if(flag > 0){//删除成功
					result.append("success");
				}
			 }else{//删除成功
				logger.warn("主卡批量删除传入卡ID集合为空！！");
				result.append("success");
			 }
		} catch (Exception e) {
			logger.error("删除主卡异常！", e);
		}
		 return result.toString();
	}
	
	/**
	 * 
	 * <p>方法描述: 根据主卡主键查询主卡信息</p>
	 *
	 * <p>方法备注: 根据主卡主键查询主卡信息</p>
	 *
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月21日 下午3:04:46</p>
	 *
	 */
	@RequestMapping(value="/getMaincardById" ,produces = "application/json")
	@ResponseBody
	public PaginationResult<?> getMaincardById(){
		
		try {
			 
			 result = ((IICCardMainService)service).queryObjectById(model);
			
		} catch (BusinessException e) {
			logger.error("根据主卡ID查询主卡信息异常!", e);
		}
		
		return result;
	}
	
	
	@RequestMapping(value="/refreshBalance" ,produces = "application/json")
	@ResponseBody
	public PaginationResult<?> refreshBalance(){
		
		try {
			 
			 int flg = ((IICCardMainService)service).refreshBalance(model);
			 
			 result.setMessage(flg+"");
			
		} catch (Exception e) {
			
			logger.error("刷新主卡余额异常!", e);
		}
		 return result;
	}
	
	
	
	
	
}
