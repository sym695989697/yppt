package com.ctfo.chpt.service.iccard.sync;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ctfo.chpt.action.iccard.sync.utils.ParameterConver;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Converter;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.Operator;
import com.ctfo.gatewayservice.interfaces.bean.user.CRMUserBean;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.gatewayservice.interfaces.service.IVehicleService;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.vims.external.bean.ImageExternalBean;
import com.vims.external.bean.VehicleExternalBean;

/**
 * 同步信息实现类
 * 
 * @author jichao
 */
@Service
public class SyncServiceImpl implements ISyncService {

	private static Log logger = LogFactory.getLog(SyncServiceImpl.class);
	
	private IICCardManager iicCardManager;
	//网关接口--车辆
	private IVehicleService iVehicleService;
	//网关接口--用户
	private IUserService iUserService;
	
	
	public SyncServiceImpl() {
		iicCardManager = (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
		iUserService= (IUserService) ServiceFactory.getFactory().getService(IUserService.class);
		iVehicleService= (IVehicleService) ServiceFactory.getFactory().getService(IVehicleService.class);
	}
	
	@Override
	public PaginationResult<?> getListPage(DynamicSqlParameter requestParam) throws Exception{
		PaginationResult<?> result = new PaginationResult<ICCard>();
		try {
			ICCardExampleExtended example = (ICCardExampleExtended) Converter.paramToExampleExtendedNoException(requestParam, new ICCardExampleExtended());
			result = iicCardManager.paginate(example);
			logger.info("查询结果数量："+result.getTotal());
		} catch (Exception e) {
			logger.error("查询副卡信息记录失败！", e);
			throw new Exception("查询IC卡信息记录失败！", e);
		}
		return result;
	}

	@Override
	public void updateUserById(Index index ,String id) throws Exception {
		
		//查询副卡记录
		ICCard card = new ICCard();
		card.setId(id);
		ICCard iCCard = iicCardManager.getById(card);
		if(iCCard==null){
			logger.error("获取副卡信息为空.");
			throw new Exception("获取副卡信息为空.");
		}
		
		//获取网关用户信息
		String userId = iCCard.getUserId(); 
		if(StringUtils.isEmpty(userId)){
			logger.error("按ID更新卡用户信息异常:用户ID为空.");
			throw new Exception("按ID更新卡用户信息异常:用户ID为空."); 
		}else{
			Operator operator = new Operator();
			operator.setSystemsign("YPPT");
			operator.setUserId(index.getUserId());
			//统一认证USERID==CRMUserBean.userId
			CRMUserBean user = iUserService.queryCRMUserByUserId(userId,operator);
			if(user==null){
				logger.error("按ID查询用户时为空.");
				throw new Exception("按ID查询用户时为空.");
			}
			//更新用户信息
			iCCard.setId(id);
			iCCard.setUserId(user.getId());
			iCCard.setUserName(user.getCrmName());
			iCCard.setUserType(user.getCrmCustomerType());//1：企业 2：个人
			iCCard.setUserRegPhone(user.getCrmMobile());
			iicCardManager.update(iCCard);
		}
	}

	@Override
	public void updateVehicleById(String id) throws Exception {
		//查询副卡记录
		ICCard card = new ICCard();
		card.setId(id);
		ICCard iCCard = iicCardManager.getById(card);
		if(iCCard==null){
			logger.error("获取副卡信息为空.");
			throw new Exception("获取副卡信息为空.");
		}
		
		//获取网关用户信息
		String vid = iCCard.getVehicleId();
		if(StringUtils.isEmpty(vid)){
			logger.error("按ID更新卡用户信息异常:车辆ID为空.");
			throw new Exception("按ID更新卡用户信息异常:车辆ID为空."); 
		}else{
			//更新车辆信息
			VehicleExternalBean vehicle=  iVehicleService.getVehicleById(vid);
			if(vehicle==null){
				logger.error("按ID查询车辆为空.");
				throw new Exception("按ID查询车辆为空.");
			}
			iCCard.setVehicleId(vehicle.getId());
			iCCard.setVehicleNo(vehicle.getVehiclePlateNo());
			iCCard.setVehicleColor(vehicle.getVehiclePlateColor());
			List<ImageExternalBean> imgs = vehicle.getImageUrls();
			String imgUrl = "";//车辆行驶证图片地址
			//2002车辆行驶证，2000车辆合格证，2005道路运输许可证，2003车身，2001车辆登记证	
			for(int i=0;i<imgs.size();i++){
				ImageExternalBean img = imgs.get(i);
				if(img.getTypeCode().equals("2002")){
					imgUrl = img.getUrl();
				}
			}
			//如果系统中图片已经存在，则不做同步
			if(iCCard.getVehicleLicense() == null || "".equals(iCCard.getVehicleLicense())){
				iCCard.setVehicleLicense(imgUrl);
			}
			iicCardManager.update(iCCard);
		}
	}

	@Override
	public void updateAllUser(Index index) throws Exception {
		//查询副卡记录
		DynamicSqlParameter requestParam = new DynamicSqlParameter();
		ICCardExampleExtended example = new ICCardExampleExtended();
		List<ICCard> list = new ArrayList<ICCard>();
		ICCardExampleExtended.Criteria iCCardCriteria = (ICCardExampleExtended.Criteria) ParameterConver.paramToExampleExtendedCriteria(requestParam, example);
		example.createCriteria().andUserIdIsNotNull();
		int countNum =  iicCardManager.count(example);
		int pageNum = countNum /1000 + 1;
		
		for(int page=1;page<=pageNum;page++){
		      int pageSize = 1000;
		      example.setEndNum(page * pageSize);
		      example.setLimitNum(pageSize);
		      example.setSkipNum((page - 1) * pageSize);
		      
		      list = iicCardManager.getList(example);
		      for (int i=0;i<list.size();i++){
		    	  ICCard iCCard = list.get(i);
		    	  String userId = iCCard.getUserId();//统一认证ID
		    	  if(userId==null){
		    		  logger.error("ICCard.userId为空,忽略跳过-(userId:"+userId+").");
		    		  continue;
		    	  }
		    	  Operator operator = new Operator();
		    	  operator.setSystemsign("YPPT");
		    	  operator.setUserId(index.getUserId());
		    	  //统一认证USERID==CRMUserBean.userId
		    	  CRMUserBean user = iUserService.queryCRMUserByUserId(userId,operator);
		    	  if(user==null){
		    		  logger.error("(" + i + ") 按ID查询用户信息时为空,忽略跳过-(userId:"+userId+").");
		    		  continue;
		    	  }
		    	  iCCard.setUserId(user.getId());
		    	  iCCard.setUserName(user.getCrmName());
		    	  iCCard.setUserType(user.getCrmCustomerType());//1：企业 2：个人
		    	  iCCard.setUserRegPhone(user.getCrmMobile());
		    	  iicCardManager.update(iCCard);
		    	  logger.info("------->("+i+")" + "--更新iccard信息.");
		      }
		      logger.info(">>>>>>>>>>>>刷新完成："+list.size()+"条记录>>>>>>>>>>>>>>>>>>>>>>");
		      list.clear();
	      }
	}

	@Override
	public void updateAllVehicle() throws Exception {
		DynamicSqlParameter requestParam = new DynamicSqlParameter();
		ICCardExampleExtended example = new ICCardExampleExtended();
		List<ICCard> list = new ArrayList<ICCard>();
		ICCardExampleExtended.Criteria iCCardCriteria = (ICCardExampleExtended.Criteria) ParameterConver.paramToExampleExtendedCriteria(requestParam, example);
		example.createCriteria().andVehicleIdIsNotNull();
		int countNum =  iicCardManager.count(example);
		int pageNum = countNum /1000 + 1;
		
		for(int page=1;page<=pageNum;page++){
		      int pageSize = 1000;
		      example.setEndNum(page * pageSize);
		      example.setLimitNum(pageSize);
		      example.setSkipNum((page - 1) * pageSize);
		      
		      list = iicCardManager.getList(example);
		      for (int i=0;i<list.size();i++){
		    	  ICCard iCCard = list.get(i);
		    	  String vid = iCCard.getVehicleId();
		    	  if(vid==null){
		    		  logger.error("ICCard.vid为空,忽略跳过-(vid:"+vid+").");
		    		  continue;
		    	  }
		    	  
		    	  //更新车辆信息
		    	  VehicleExternalBean vehicle=  iVehicleService.getVehicleById(vid);
		    	  if(vehicle==null){
		    		  logger.error("(" + i + ") 按ID查询用户信息时为空,忽略跳过-(vid:"+vid+").");
		    		  continue;
		    	  }
		    	  iCCard.setVehicleId(vehicle.getId());
					iCCard.setVehicleNo(vehicle.getVehiclePlateNo());
					iCCard.setVehicleColor(vehicle.getVehiclePlateColor());
					List<ImageExternalBean> imgs = vehicle.getImageUrls();
					String imgUrl = "";//车辆行驶证图片地址
					//2002车辆行驶证，2000车辆合格证，2005道路运输许可证，2003车身，2001车辆登记证	
					for(int j=0;j<imgs.size();j++){
						ImageExternalBean img = imgs.get(j);
						if(img.getTypeCode().equals("2002")){
							imgUrl = img.getUrl();
						}
					}
					
					//如果系统中图片已经存在，则不做同步
					if(iCCard.getVehicleLicense() == null || "".equals(iCCard.getVehicleLicense())){
						iCCard.setVehicleLicense(imgUrl);
					}
					iicCardManager.update(iCCard);
		    	  logger.info("------->("+i+")" + "--更新iccard信息.");
		      }
		      logger.info(">>>>>>>>>>>>刷新完成："+list.size()+"条记录>>>>>>>>>>>>>>>>>>>>>>");
		      list.clear();
	      }
	}
	
}
