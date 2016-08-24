package com.ctfo.base.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;

public class ServiceImpl extends AbstractService implements IService {
	private static Log logger = LogFactory.getLog(ServiceImpl.class);

	protected IBaseManager baseManager;

	protected IBaseManager getBaseManager(Object model) throws Exception {
		if (baseManager != null) {
			return baseManager;
		}
		String clsName = bulidInterFaceClassName(model);
		Class cls = Class.forName(clsName);
		baseManager = getRemoManager(cls);
		return baseManager;
	}

	public static String bulidInterFaceClassName(Object model) {
		String modelClassName = model.getClass().getSimpleName();
		String modelPackage = model.getClass().getPackage().getName();
		String intfacePackageName = modelPackage.substring(0, modelPackage.lastIndexOf(".")) + ".intf";
		return intfacePackageName + "." + "I" + modelClassName + "Manager";
	}

	public PaginationResult<?> add(Object model) throws BusinessException {
		PaginationResult<Object> result = null;
		try {
			System.out.println("add");
			result = new PaginationResult<Object>();
			getBaseManager(model).add(model);
			result.setMessage("成功!");
		} catch (Exception e) {
			throw new BusinessException("UC_E0002:调用通用add方法异常", e);// UC_0001
																	// 调用通用add方法异常
		}

		return result;
	}

	public PaginationResult<?> update(Object model) throws BusinessException {
		PaginationResult<Object> result = null;
		try {
			System.out.println("update");
			result = new PaginationResult<Object>();
			getBaseManager(model).update(model);
			result.setMessage("成功！");
		} catch (Exception e) {
			throw new BusinessException("UC_E0003:调用通用update方法异常", e);// UC_0002
																		// 调用通用update方法异常
		}
		return result;
	}

	public PaginationResult<?> delete(Object model) throws BusinessException {
		// TODO Auto-generated method stub
		PaginationResult<Object> result = null;
		try {
			System.out.println("delete");
			result = new PaginationResult<Object>();
			getBaseManager(model).remove(model);
			result.setMessage("成功！");
		} catch (Exception e) {
			throw new BusinessException("UC_E0004:调用通用delete方法异常", e);// UC_0002
																		// 调用通用delete方法异常
		}
		return result;
	}

	public PaginationResult<?> queryObjectById(Object model) throws BusinessException {
		// TODO Auto-generated method stub
		PaginationResult<Object> result = null;
		try {

			System.out.println("queryObject");

			result = new PaginationResult<Object>();
			Object dataObject = getBaseManager(model).getById(model);
			result.setDataObject(dataObject);

			result.setMessage("成功！");

		} catch (Exception e) {
			throw new BusinessException("UC_E0005:调用通用queryObject方法异常", e);// UC_0004
																			// 调用通用delete方法异常
		}
		return result;
	}

	public List queryList(DynamicSqlParameter requestParam) throws BusinessException {
		try {

			String modelName = requestParam.getEqual() == null || requestParam.getEqual().get("modelName") == null ? "" : requestParam.getEqual().remove("modelName");
			Object model = Class.forName(modelName).newInstance();// 生成一个扩展对象

			Object modelEE = Class.forName(modelName + "ExampleExtended").newInstance();// 生成一个扩展对象

			Converter.paramToExampleExtended(requestParam, modelEE);// 将参数对象转换成扩展对象
			return getBaseManager(model).getList(modelEE);

		} catch (Exception e) {
			logger.error("app service query pages object fail", e);
		}

		return new ArrayList();
	}

	public PaginationResult<?> queryListPage(DynamicSqlParameter requestParam) throws BusinessException {
		// TODO Auto-generated method stub
		PaginationResult<Object> result = null;
		try {
			String modelName = requestParam.getEqual() == null || requestParam.getEqual().get("modelName") == null ? "" : requestParam.getEqual().remove("modelName");
			Object model = Class.forName(modelName).newInstance();// 生成一个扩展对象

			Object modelEE = Class.forName(modelName + "ExampleExtended").newInstance();// 生成一个扩展对象

			Converter.paramToExampleExtended(requestParam, modelEE);// 将参数对象转换成扩展对象
			return getBaseManager(model).paginate(modelEE);
		} catch (Exception e) {
			throw new BusinessException("UC_E0001:调用通用queryListPage方法异常", e);// UC_0001
																				// 调用通用queryListPage方法异常
		}
	}

	@Override
	public boolean checkExist(Object model, String fieldName) {
		return false;
	}

	/**
	 * 验证参数是否为NULL 或空
	 * 
	 * @param objects
	 * @return
	 */
	protected boolean vailidateParams(Object... objects) {
		for (Object ob : objects) {
			if (ob == null || "".equals(String.valueOf(objects))) {
				return false;
			}
		}
		return true;
	}
}
