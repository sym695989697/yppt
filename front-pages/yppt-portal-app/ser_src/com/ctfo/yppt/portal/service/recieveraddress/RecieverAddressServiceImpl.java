package com.ctfo.yppt.portal.service.recieveraddress;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.crm.boss.beans.UserRecieverAddress;
import com.ctfo.crm.boss.beans.UserRecieverAddressExampleExtended;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.gatewayservice.interfaces.service.IUserRecieverAddressService;

/**
 * 油卡相关业务服务类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:42:26
 *
 */
@AnnotationName(name = "油卡业务服务")
@Service("recieverAddressService")
public class RecieverAddressServiceImpl extends ServiceImpl implements RecieverAddressService {

	private static Log logger = LogFactory.getLog(RecieverAddressServiceImpl.class);

    private static IUserRecieverAddressService iUserRecieverAddressService;
   
	// 构造方法，将后台对象实例化
	private  RecieverAddressServiceImpl() {
	}
	static {
		logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
		iUserRecieverAddressService = (IUserRecieverAddressService) ServiceFactory.getFactory().getService(IUserRecieverAddressService.class);
		logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
	}
	/**
	 * 查询用户地址列表
	 */
	@Override
	public List queryAddressList(DynamicSqlParameter requestParam,Index index) throws AAException {
		
		List<UserRecieverAddress> result = new ArrayList<UserRecieverAddress>();
		try {
			UserRecieverAddressExampleExtended iicExample = new UserRecieverAddressExampleExtended();
			iicExample.createCriteria().andUserIdEqualTo(index.getUserId());
			try {
				result =  iUserRecieverAddressService.queryRecieverAddressInfo(iicExample);
			} catch (Exception e) {
                logger.error("调用iUserRecieverAddressService.queryRecieverAddressInfo后台接口查询用户地址列表失败"+e);                
				throw new Exception("调用iUserRecieverAddressService.queryRecieverAddressInfo后台接口查询用户地址列表失败",e);
			}
		} catch (Exception e) {
			    logger.error("查询用户地址失败",e);
                throw new AAException("查询用户地址失败",e);
		}
		
		return result;
	}
	/**
	 * 新增地址
	 */
	@Override
	public boolean addAddress(UserRecieverAddress address,
			Index index) throws AAException {
        
		
		boolean flag = false;
			try {
				UserRecieverAddress info = new UserRecieverAddress();
				if(StringUtils.isNotBlank(address.getRecieverName())){
					info.setRecieverName(URLDecoder.decode(address.getRecieverName(),"UTF-8"));
				}
				if(StringUtils.isNotBlank(address.getPhoneNum())){
					info.setPhoneNum(address.getPhoneNum());
				}
				if(StringUtils.isNotBlank(address.getProvince())){
					info.setProvince(address.getProvince()+"0000");
				}
				if(StringUtils.isNotBlank(address.getCity())){
					info.setCity(address.getCity()+"00");
				}
				if(StringUtils.isNotBlank(address.getDistrict())){
					info.setDistrict(address.getDistrict());
				}
				if(StringUtils.isNotBlank(address.getAddress())){
					info.setAddress(URLDecoder.decode(address.getAddress(),"UTF-8"));
				}
				if(StringUtils.isNotBlank(address.getZipCode())){
					info.setZipCode(address.getZipCode());
				}
				if(StringUtils.isNotBlank(index.getUserId())){
					info.setUserId(index.getUserId());
				}
				
				if(StringUtils.isNotBlank(address.getId())){
					info.setId(address.getId());
					//修改地址
					try {
						info.setModifyTime((new Date()).getTime());
						info.setModifier(index.getUserId());
						iUserRecieverAddressService.modifyRecieverAddressInfo(info);
						//iUserRecieverAddressService.modifiedRecieverAddressInfoOften(info.getId());
						flag = true;
					} catch (Exception e) {
						flag = false;
						logger.error("调用iUserRecieverAddressService.modifyRecieverAddressInfo修改地址失败"+e);
						throw new AAException("调用iUserRecieverAddressService.modifyRecieverAddressInfo修改地址失败",e);
					}
				}else{
					try {
						info.setStatus("0");
						info.setCreateTime((new Date()).getTime());
						info.setCreator(index.getUserId());
						String uuid = iUserRecieverAddressService.addRecieverAddressInfo(info);
						//iUserRecieverAddressService.modifiedRecieverAddressInfoOften(uuid);
						flag = true;
					} catch (Exception e) {
						flag = false;
						logger.error("调用iUserRecieverAddressService.addRecieverAddressInfo新增地址失败"+e);
						throw new AAException("调用iUserRecieverAddressService.addRecieverAddressInfo新增地址失败",e);
					}
				}
			} catch (Exception e) {
	             logger.error("新增或者修改地址失败",e);
	             throw new AAException("新增或者修改地址失败",e);
			}
		return flag;
	}
	
	@Override
	public boolean modifiedRecieverAddressInfoOften(String id, Index index) {
		
		logger.info("修改使用发票常用地址接口调用开始：参数:" +id+ " ; 操作人:" + index.getUserName());
		
		boolean flag = false;
		
		try {
			
			if(StringUtils.isBlank(id)) return false;
			
			logger.info("修改使用发票常用地址接口调用开始：参数:" +id+ " ; 操作人:" + index.getUserName() + "; 接口名:iUserRecieverAddressService;方法:modifiedRecieverAddressInfoOften");
			
			iUserRecieverAddressService.modifiedRecieverAddressInfoOften(id);
			
			flag = true;
			
			logger.info("修改使用发票常用地址接口调用结束：参数:" +id+ " ; 操作人:" + index.getUserName()+"; 接口名:iUserRecieverAddressService;方法:modifiedRecieverAddressInfoOften");
			
		} catch (Exception e) {
             logger.error("常用地址使用失败",e);
             throw new AAException("常用地址使用失败",e);
		}
		return flag;
	}
	
	@Override
	public List queryStockAddress(DynamicSqlParameter requestParam, Index index) {
		List<UserRecieverAddress> result = new ArrayList<UserRecieverAddress>();
		try {
			UserRecieverAddressExampleExtended iicExample = new UserRecieverAddressExampleExtended();
			iicExample.createCriteria().andUserIdEqualTo(index.getUserId()).andIsOftenEqualTo("0");
			try {
				result =  iUserRecieverAddressService.queryRecieverAddressInfo(iicExample);
			} catch (Exception e) {
                logger.error("调用iUserRecieverAddressService.queryRecieverAddressInfo后台接口查询用户地址列表失败"+e);                
				throw new Exception("调用iUserRecieverAddressService.queryRecieverAddressInfo后台接口查询用户地址列表失败",e);
			}
		} catch (Exception e) {
			    logger.error("查询用户地址失败",e);
                throw new AAException("查询用户地址失败",e);
		}
		
		return result;
	}
	/**
	 * 删除地区
	 */
	@Override
	public boolean deleteAddress(String id, Index index) throws AAException {
		boolean flag = false;
		try {
			iUserRecieverAddressService.deleteRecieverAddressInfo(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			 logger.error("删除用户地址失败",e);
             throw new AAException("删除用户地址失败",e);
		}
		return flag;
	}
   
}
