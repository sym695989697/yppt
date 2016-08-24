package com.ctfo.chpt.service.iccard.cardmanager;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.interfaces.external.ICatchService;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.dao.beans.UAAUser;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExampleExtended;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExampleExtended.Criteria;
import com.ctfo.yppt.baseservice.card.intf.IICCardMainManager;
import com.ctfo.yppt.bean.ICCardMainMetaBean;

/**
 * 
 * 项目名称：yppt-manager-app<br>
 * *********************************<br>
 * <P>
 * 类名称：MainCardServiceImpl
 * </P>
 * *********************************<br>
 * <P>
 * 类描述：主卡管理功能App-Service接口实现
 * </P>
 * 创建人：李飞<br>
 * 创建时间：2015年1月21日 下午1:27:25<br>
 * 修改人：李飞<br>
 * 修改时间：2015年1月21日 下午1:27:25<br>
 * 修改备注：<br>
 * 
 * @version 1.0<br>
 */
@Service(value = "iCCardMainService")
public class ICCardMainServiceImpl extends ServiceImpl implements IICCardMainService {

	private static Log logger = LogFactory.getLog(ICCardServiceImpl.class);

	private IICCardMainManager icCardMainManager;

	private IUserService userService = null;

	/**
	 * 数据抓取接口
	 */
	private ICatchService iCatchService;

	public ICCardMainServiceImpl() {
		icCardMainManager = (IICCardMainManager) ServiceFactory.getFactory().getService(IICCardMainManager.class);
	}

	@Override
	public PaginationResult<?> queryListPage(DynamicSqlParameter requestParam) {

		logger.info("查询主卡信息调用开始：Method:queryListPage;params:" + requestParam.toString());

		PaginationResult<ICCardMainMetaBean> result = null;
		try {
			String cardNumber = null;// IC卡号
			String opencardofficecode = null;// 开卡代码
			String ownOrg = null;// 所属组织
			String cardType = null;// 发卡类型
			ICCardMainExampleExtended extended = new ICCardMainExampleExtended();
			extended.setOrderByClause(" created_time desc ");
			Map<String, String> params = requestParam.getEqual();
			if (params != null && !params.isEmpty()) {
				cardNumber = params.get("cardNumber");
				opencardofficecode = params.get("opencardofficecode");
				ownOrg = params.get("ownOrg");
				cardType = params.get("cardType");
				Criteria criteria = extended.createCriteria();
				if (!StringUtils.isBlank(cardNumber))
					criteria.andCardNumberLike("%" + cardNumber + "%");
				if (!StringUtils.isBlank(opencardofficecode))
					criteria.andOpencardofficecodeEqualTo(opencardofficecode);
				if (!StringUtils.isBlank(ownOrg))
					criteria.andOwnOrgEqualTo(ownOrg);
				if (!StringUtils.isBlank(cardType))
					criteria.andCardTypeEqualTo(cardType);
			}
			int offset = (requestParam.getPage() - 1) * requestParam.getRows();
			int limit = requestParam.getRows();
			logger.info("查询主卡信息调用开始：Method:queryListPage;cardNumber:" + cardNumber + ";opencardofficecode:" + opencardofficecode + ";ownOrg:" + ownOrg + ";offset:" + offset + ";limit" + limit);
			extended.setSkipNum(offset);
			extended.setLimitNum(limit);

			logger.info("查询主卡信息远程调用(ypptBaseService)开始：Method:queryListPage;cardNumber:" + cardNumber + ";opencardofficecode:" + opencardofficecode + ";ownOrg:" + ownOrg);
			result = icCardMainManager.queryIICCardMainAndChildCardNum(extended);
			logger.info("查询主卡信息远程调用(ypptBaseService)结束：Method:queryListPage;cardNumber:" + cardNumber + ";opencardofficecode:" + opencardofficecode + ";ownOrg:" + ownOrg);
			if (result != null) {
				for (ICCardMainMetaBean bean : result.getData()) {
					UAAUser user = getUserService().queryUaaUserById(bean.getCtrdituser(), new Operator());
					bean.setCtrdituser(user == null ? null : user.getUserName());
					UAAUser modifyUser = getUserService().queryUaaUserById(bean.getModifinguser(), new Operator());
					bean.setModifinguser(modifyUser == null ? null : modifyUser.getUserName());
				}
			}

		} catch (Exception e) {
			logger.error("分页查询ic卡主卡记录异常", e);
		}
		return result;
	}

	@Override
	public PaginationResult<?> add(Object model) throws BusinessException {

		logger.info("添加主卡信息调用开始：add");
		if (model == null) {
			logger.info("添加主卡信息调用开始：add,参数为：" + model);
			return null;
		}

		PaginationResult<ICCardMain> result = new PaginationResult<ICCardMain>();

		try {

			if (model instanceof ICCardMain) {

				logger.info("添加主卡信息调用开始(ypptBaseService)开始：Method:add");
				((ICCardMain) model).setCreatedTime(System.currentTimeMillis());
				((ICCardMain) model).setCtrdituser(Index.class.getName());
				icCardMainManager.add((ICCardMain) model);
				result.setMessage("添加成功!");
				logger.info("添加主卡信息调用开始(ypptBaseService)结束：Method:add");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			return result;
		}
		return result;
	}

	@Override
	public PaginationResult<?> update(Object model) throws BusinessException {
		logger.info("修改主卡信息调用开始：update");
		if (model == null) {
			logger.info("修改主卡信息调用开始：update,参数为：" + model);
			return null;
		}

		PaginationResult<ICCardMain> result = new PaginationResult<ICCardMain>();
		try {
			if (model instanceof ICCardMain) {

				String id = ((ICCardMain) model).getId();
				logger.info("修改主卡信息调用开始：update,id：" + id);

				if (StringUtils.isBlank(id)) {
					logger.info("修改主卡信息调用开始：update,id is null：" + id);
					return null;
				}
				logger.info("修改主卡信息调用开始(ypptBaseService)开始：Method:update");
				icCardMainManager.update((ICCardMain) model);
				result.setMessage("修改主卡信息操作成功");
				logger.info("修改主卡信息调用开始(ypptBaseService)结束：Method:update");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			return result;
		}
		return result;
	}

	@Override
	public PaginationResult<?> delete(Object model) throws BusinessException {
		return super.delete(model);
	}

	@Override
	public PaginationResult<?> queryObjectById(Object model) throws BusinessException {

		logger.info("根据主键查询主卡信息调用开始：queryObjectById");
		if (model == null) {
			logger.info("根据主键查询主卡信息调用开始：queryObjectById,参数为：" + model);
			return null;
		}
		PaginationResult<ICCardMain> result = new PaginationResult<ICCardMain>();

		try {
			if (model instanceof ICCardMain) {
				String id = ((ICCardMain) model).getId();
				logger.info("根据主键查询主卡信息调用开始：queryObjectById,id：" + id);

				if (StringUtils.isBlank(id)) {
					logger.info("根据主键查询主卡信息调用开始：queryObjectById,id is null：" + id);
					return null;
				}
				logger.info("查询主卡信息远程调用(ypptBaseService)开始：Method:getById");
				ICCardMain cardMain = icCardMainManager.getById((ICCardMain) model);
				result.setDataObject(cardMain);
				logger.info("查询主卡信息远程调用(ypptBaseService)结束：Method:getById");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ctfo.chpt.service.iccard.cardmanager.ICCardMainService#removeBatch
	 * (java.util.List)
	 */
	@Override
	public int deleteBatch(List<ICCardMain> itmes) {
		int result = 0;
		try {
			result = this.icCardMainManager.removeBatch(itmes);
			logger.info("**********批量删除结果：" + result);
		} catch (Exception e) {
			logger.error("批量删除失败！删除结果：" + result);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int refreshBalance(Object model) {
		int flag = 0;
		logger.info("刷新主卡余额信息调用开始：refreshBalance");
		if (model == null) {
			logger.info("刷新主卡余额信息：refreshBalance,参数为：" + model);
			return 0;
		}
		try {
			if (model instanceof ICCardMain) {
				String id = ((ICCardMain) model).getId();
				logger.info("刷新主卡余额信息调用开始：refreshBalance,id：" + id);
				if (StringUtils.isBlank(id)) {
					logger.info("刷新主卡余额信息调用开始：refreshBalance,id is null：" + id);
					return 0;
				}
				logger.info("查询主卡余额远程调用(ypptBaseService)开始：Method:getById");
				ICCardMain card = (ICCardMain) model;
				if (card != null && card.getId() != null && !"".equals(card.getId())) {
					ICCardMain mcard = icCardMainManager.getById(card);
					JResult result = getICatchService().getOilCardBalance(mcard.getCardType(), mcard.getCardNumber(),"");
					if (result.isSuccess()) {
						flag = 1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = 0;
			logger.error("更新主卡余额异常,异常信息:" + e.getMessage());
		}
		return flag;
	}

	public IUserService getUserService() {
		if (userService == null)
			userService = (IUserService) ServiceFactory.getFactory().getService(IUserService.class);
		return userService;
	}

	public ICatchService getICatchService() {
		if (iCatchService == null)
			iCatchService = (ICatchService) ServiceFactory.getFactory().getService(ICatchService.class);
		return iCatchService;
	}

}
