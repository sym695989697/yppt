package com.ctfo.base.mamager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.beans.SimpleCode;
import com.ctfo.base.service.beans.SimpleCodeExampleExtended;
import com.ctfo.base.service.intf.ISimpleCodeManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.common.models.PaginationResult;

@Service("SimpleCodeManagerImpl")
public class SimpleCodeManagerImpl extends GenericManagerImpl<SimpleCode, SimpleCodeExampleExtended> implements ISimpleCodeManager {
	private static Log logger = LogFactory.getLog(SimpleCodeManagerImpl.class);

	/**
	 * 检查码表对象在根节点和同一类型下唯一
	 * 
	 * @param type
	 * @param code
	 * @param op
	 */
	@Override
	public boolean checkLegal(String type, String code) throws BusinessException {
		try{
			SimpleCodeExampleExtended scEE = new SimpleCodeExampleExtended();
			scEE.createCriteria().andTypeCodeEqualTo(type).andCodeEqualTo(code);
			int tem1 = this.count(scEE);

			scEE = new SimpleCodeExampleExtended();
			scEE.createCriteria().andTypeCodeEqualTo("root").andCodeEqualTo(code);
			int tem2 = this.count(scEE);

			if(tem1>0 || tem2>0)
				return false;
			else
				return true;

		} catch (Exception e) {
			logger.error("检查码表对象唯一异常", e);
			throw new BusinessException("检查码表对象唯一异常", e);
		}

	}

	@Override
	public String add(SimpleCode model) throws BusinessException {
		String uuid = null;
		try {

			super.notNull(model, model.getCode(), model.getTypeCode());

			this.checkLegal("root", model.getCode());// 根节点下唯一

			this.checkLegal(model.getTypeCode(), model.getCode());// 同一类型下唯一
			
			if(!(this.checkLegal("root", model.getCode()) && this.checkLegal(model.getTypeCode(), model.getCode())))
				throw new BusinessException("根节点下或同类型下不唯一");

			// 默认新增码表信息为启用状态
//			model.setStatus(GenericEnum.DISENABLE.getValue().equals(model.getStatus()) ? GenericEnum.DISENABLE.getValue() : GenericEnum.ENABLE.getValue());

			uuid = super.add(model);

		} catch (Exception e) {
			logger.error("增加码表信息异常", e);
			throw new BusinessException("增加码表信息异常", e);
		}
		return uuid;
	}

	@Override
	public int update(SimpleCode model) throws BusinessException {
		try {
			super.notNull(model, model.getId(), model.getCode(), model.getTypeCode());
			SimpleCode tem = (SimpleCode) super.getById(model);
			if (!tem.getTypeCode().equals(model.getTypeCode())) {
				if(!(this.checkLegal("root", model.getCode())))
					throw new BusinessException("根节点下不唯一");
				//this.checkLegal("root", model.getCode());
			}

			if (!tem.getCode().equals(model.getCode())) {
				if(!this.checkLegal(model.getTypeCode(), model.getCode()))
					throw new BusinessException("同类型下不唯一");			
				//this.checkLegal(model.getTypeCode(), model.getCode());
			}
			// code发生变化则对应的typecode均要发生变更
			SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
			exampleExtended.createCriteria().andTypeCodeEqualTo(model.getCode());
			List<SimpleCode> sub = super.getList(exampleExtended);
			if (sub != null && sub.size() > 0) {
				for (SimpleCode usc : sub) {
					super.updatePart(usc);
				}
			}
			// model.setStatus(null);//不修改状态
			return	super.updatePart(model);

		} catch (Exception e) {
			logger.error("修改码表信息异常", e);
			throw new BusinessException("修改码表信息异常", e);
		}

	}

	@Override
	public int remove(SimpleCode model) throws BusinessException {

		try {
			super.notNull(model, model.getId());

			//			if (GenericEnum.ENABLE.getValue().equalsIgnoreCase(model.getStatus())) {
			//				logger.warn("SimpleCode[" + model.getId() + "] is using , not allowed to remove!");
			//				throw new BusinessException("SimpleCode is using, not allowed to remove!");
			//			}

			return super.remove(model);

		} catch (Exception e) {
			logger.error("删除码表信息异常", e);
			throw new BusinessException("删除码表信息异常", e);
		}

	}

	@Override
	public SimpleCode getById(SimpleCode model) throws BusinessException {

		SimpleCode bean = null;

		try {
			super.notNull(model, model.getId());

			bean = (SimpleCode) super.getById(model);

		} catch (Exception e) {
			logger.error("根据ID查询码表异常", e);
			throw new BusinessException("根据ID查询码表异常", e);
		}
		return bean;
	}

	@Override
	public List<SimpleCode> getList(SimpleCodeExampleExtended exampleExtended) throws BusinessException {

		List<SimpleCode> list = null;

		try {
			super.notNull(exampleExtended);

			list = super.getList(exampleExtended);

		} catch (Exception e) {
			logger.error("查询码表集合异常", e);
			throw new BusinessException("查询码表集合异常", e);
		}

		return list;
	}

	@Override
	public PaginationResult<SimpleCode> paginate(SimpleCodeExampleExtended exampleExtended) throws BusinessException {
		PaginationResult<SimpleCode> pagination = null;
		try {

			super.notNull(exampleExtended);

			pagination = super.paginate(exampleExtended);

		} catch (Exception e) {
			logger.error("查询码表页面对象异常", e);
			throw new BusinessException("查询码表页面对象异常", e);
		}
		return pagination;
	}

	@Override
	public int count(SimpleCodeExampleExtended exampleExtended) throws BusinessException {
		int count = 0;
		try {
			super.notNull(exampleExtended);
			count = super.count(exampleExtended);
		} catch (Exception e) {
			logger.error("统计码表信息异常", e);
			throw new BusinessException("统计码表信息异常", e);
		}
		return count;
	}

	@Override
	public SimpleCode getSimpleCodeByTypeAndCode(String typeCode, String code) throws BusinessException {
		try {

			SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
			exampleExtended.createCriteria().andTypeCodeEqualTo(typeCode).andCodeEqualTo(code);
			List<SimpleCode> list = super.getList(exampleExtended);
			if (list != null && list.size() == 1)
				return list.get(0);
			else
				logger.warn("找不到类型[" + typeCode + "],代码[" + code + "]对应的信息！");

		} catch (Exception e) {
			logger.error("根据类型编码和编码获取单个码表对象异常", e);
			throw new BusinessException("根据类型编码和编码获取单个码表对象异常", e);
		}
		return null;
	}

	@Override
	public List<SimpleCode> getSimpleCodeByType(String typeCode) throws BusinessException {
		try {
			SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
			exampleExtended.createCriteria().andTypeCodeEqualTo(typeCode);
			return super.getList(exampleExtended);
		} catch (Exception e) {
			logger.error("根据类型编码码表对象集合异常", e);
			throw new BusinessException("根据类型编码码表对象集合异常", e);
		}
	}

	@Override
	public SimpleCode getSimpleCodeByCode(String code) throws BusinessException {
		try {
			SimpleCodeExampleExtended exampleExtended = new SimpleCodeExampleExtended();
			exampleExtended.createCriteria().andCodeEqualTo(code);
			List<SimpleCode> list = super.getList(exampleExtended);
			if (list != null && list.size() == 1)
				return list.get(0);
			else
				logger.warn("找不到代码[" + code + "]对应的信息！");
		} catch (Exception e) {
			logger.error("根据编码获取单个码表对象异常", e);
			throw new BusinessException("根据编码获取单个码表对象异常", e);
		}
		return null;
	}

	@Override
	public int modifySimpleCodeStatus(SimpleCode simpleCode) throws BusinessException {
		int status = -1;
		try {
			status = super.updatePart(simpleCode);
		} catch (Exception e) {
			logger.error("状态修改失败",e);
			throw new BusinessException("状态修改失败",e);
		}
		return status;
	}
	
	@Override
	public void querySimpleCodeDemo() throws BusinessException {
		Map<String, Object> parmaObjMap = new HashMap<String, Object>();
		parmaObjMap.put("codeTypes", "TRRT");
		try {
			//SimpleCode
			List list = queryListBySQL("SIMPLE_CODE_EXTEND.selectSimpleCodeComplex", parmaObjMap );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
