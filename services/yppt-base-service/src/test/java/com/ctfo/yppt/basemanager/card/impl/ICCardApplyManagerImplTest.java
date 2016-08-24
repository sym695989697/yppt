package com.ctfo.yppt.basemanager.card.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.soa.support.JsonSupport;
import com.ctfo.gatewayservice.interfaces.service.IMessageService;
import com.ctfo.gatewayservice.interfaces.service.IUserService;
import com.ctfo.gatewayservice.interfaces.service.IVehicleService;
import com.ctfo.yppt.baseservice.card.beans.ICCardApply;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetail;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLog;
import com.ctfo.yppt.baseservice.card.cons.ICCardCons;
import com.ctfo.yppt.baseservice.card.intf.IICCardApplyManager;
import com.ctfo.yppt.bean.ICCardApplyDetailExtend;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;

/**
 * 
 * 
 * IC卡申请管理单元测试.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月22日    dxs    新建
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all.xml")
public class ICCardApplyManagerImplTest {
    @Autowired
    IICCardApplyManager iICCardApplyManager;

    @Before
    public void setUp() throws Exception {
//        String url = "http://localhost:8080/ypptBaseService/hessian-remote/com.ctfo.yppt.baseservice.card.intf.IICCardApplyManager";
//        com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
//        iICCardApplyManager = (IICCardApplyManager) factory.create(
//                IICCardApplyManager.class, url);
    }

     @Test
     public void testAddIcCardApply() throws Exception {
     ICCardApplyMetaBean icCardApplyMetaBean=new ICCardApplyMetaBean();
     ICCardApply iCCardApply=new ICCardApply();
     List<ICCardApplyDetail> list=new ArrayList<ICCardApplyDetail>();
    
     iCCardApply.setUserId("9c036e32-9d82-48b2-8641-28e81b5cab7a");
     iCCardApply.setReceiverPhoneNum("1111111111");
     iCCardApply.setReceiverName("1111111111");
     iCCardApply.setProvince("100000");
     iCCardApply.setCity("100000");
     iCCardApply.setIdNo("111111111111111111");
     iCCardApply.setDistrict("100000");
     iCCardApply.setAddress("100000100000100000");
     iCCardApply.setCreator("idontknow");
     iCCardApply.setUserType("idontknow");
     iCCardApply.setUserName("idontknow");
     iCCardApply.setApplyUserName("idontknow");
     iCCardApply.setCardType("100000");
     iCCardApply.setCardNum(10);
     iCCardApply.setDataSource(ICCardCons.DATA_SOURCE_WEB);;
     iCCardApply.setOftenArea("110000,120000,140000");
    
     ICCardApplyDetail iad=new ICCardApplyDetail();
     iad.setAcceptMessage("1");
     iad.setCardNum("20150125006");
     iad.setCardType("中国石化");
     iad.setMark("1111");
     iad.setPhoneNum("111");
     iad.setVehicleColor("111");
     iad.setVehicleLicense("111");
     iad.setVehicleNo("111");
     
     list.add(iad);
     
     icCardApplyMetaBean.setiCCardApply(iCCardApply);
     icCardApplyMetaBean.setiCCardApplyDetail(list);
    
     iICCardApplyManager.addIcCardApply(icCardApplyMetaBean);
     }
    
    
    
     @Test
     public void testAddCardImportBatch() throws Exception {
     ICCardApplyMetaBean icCardApplyMetaBean=new ICCardApplyMetaBean();
     ICCardApply iCCardApply=new ICCardApply();
     ICCardApplyDetail iCCardApplyDetail=new ICCardApplyDetail();
     List<ICCardApplyDetail> list=new ArrayList<ICCardApplyDetail>();
     ICCardApplyProcessLog auditLogParam=new ICCardApplyProcessLog();
    
    
     list.add(iCCardApplyDetail);
    
     iCCardApply.setUserId("9c036e32-9d82-48b2-8641-28e81b5cab7a");
     iCCardApply.setReceiverPhoneNum("13001133185");
     iCCardApply.setReceiverName("1111111111");
     iCCardApply.setUserRegPhone("13001133185");
     iCCardApply.setProvince("100000");
     iCCardApply.setCity("100000");
     iCCardApply.setIdNo("111111111111111111");
     iCCardApply.setDistrict("100000");
     iCCardApply.setAddress("100000100000100000");
     iCCardApply.setCreator("idontknow");
     iCCardApply.setUserType("idontknow");
     iCCardApply.setUserName("idontknow");
     iCCardApply.setCardType("100000");
     iCCardApply.setCardNum(10);
     iCCardApply.setDataSource(ICCardCons.DATA_SOURCE_APP);;
     iCCardApply.setOftenArea("110000,120000,140000");
     iCCardApply.setApplyUserName("dxslalala");
     
     ICCardApplyDetail iad=new ICCardApplyDetail();
     iad.setAcceptMessage("1");
     iad.setCardNum("20150125006");
     iad.setCardType("中国石化");
     iad.setMark("1111");
     iad.setPhoneNum("111");
     iad.setVehicleColor("111");
     iad.setVehicleLicense("111");
     iad.setVehicleNo("111");
     icCardApplyMetaBean.setiCCardApply(iCCardApply);
     icCardApplyMetaBean.setiCCardApplyDetail(list);
    
    
     //日志信息
     auditLogParam.setSuggestion("11");
     auditLogParam.setStepNo("0");
     auditLogParam.setState("0");
     auditLogParam.setRemark("好不好");
     auditLogParam.setApprovalRoleId("11");
     auditLogParam.setApprovalRole("11");
     auditLogParam.setApprovalPersonType("11");
     auditLogParam.setApprovalPersonId("11");
     auditLogParam.setApprovalPerson("11");
    
     iICCardApplyManager.addCardImportBatch(icCardApplyMetaBean,auditLogParam);
     }

     @Test
     public void testSendMessage() throws BusinessException{
         ICCardApplyManagerImpl iCCardApplyManagerImpl=(ICCardApplyManagerImpl)iICCardApplyManager;
         List list=new ArrayList();
         list.add("10");
         list.add("韵达");
         list.add("100000000");
         iCCardApplyManagerImpl.sendMessage("13001133185", list, 10, ICCardCons.APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS);
     }
     
     @Test
     public void testQueryProcessCard() throws Exception {
         Map<String,String> param=new HashMap<String,String>();
         param.put("start", "1");
         param.put("limit", "10");
         param.put("userId", "7aa34f25-f313-4dc3-b948-0e658ea2831a");
         PaginationResult<ICCardApplyDetailExtend>  list=iICCardApplyManager.queryProcessCard(param);
         if(list.getData()!=null && list.getData().size()>0){
             Assert.assertEquals(1, 1);
         }else{
             Assert.assertEquals(1, 2);
         }
     }
     
     @Test
     public void testQueryUserAllAplly() throws Exception {
         Map<String,String> param=new HashMap<String,String>();
         param.put("start", "1");
         param.put("limit", "10");
         param.put("isFetchBalance", "true");
         param.put("userId", "7aa34f25-f313-4dc3-b948-0e658ea2831a");
         param.put("condition", "鄂B44444");  //手机号/车牌号/卡号
          
         PaginationResult<ICCardApplyDetailExtend>  list=iICCardApplyManager.queryUserAllAplly(param);
         if(list.getData()!=null && list.getData().size()>0){
             Assert.assertEquals(1, 1);
         }else{
             Assert.assertEquals(1, 2);
         }
     }
     
     
    @Test
    public void testIUserService() throws Exception {
        IUserService userService = (IUserService) ServiceFactory.getFactory()
                .getService(IUserService.class);
        if (userService == null) {
            throw new BusinessException("获取用户服务失败，不能进行用户相关服务！");
        }
        userService.authenLogin("dxs", "dxs");
    }

    @Test
    public void testIVehicleService() throws BusinessException {
        IVehicleService vehicleService = (IVehicleService) ServiceFactory
                .getFactory().getService(IVehicleService.class);
        if (vehicleService == null) {
            throw new BusinessException("获取车辆服务失败，不能进行车辆相关服务！");
        }
        vehicleService.getVehicleById("车牌号");

    }

    @Test
    public void testIMessageService() throws BusinessException {
        IMessageService iMessageService = (IMessageService) ServiceFactory
                .getFactory().getService(IMessageService.class);
        if (iMessageService == null) {
            throw new BusinessException("获取消息服务失败，不能发送短信！");
        }
        iMessageService.sendMessage("11111111111","11111111111");
    }

    @Test
    public void testGetIcCardApplyDetail() throws BusinessException {
        ICCardApplyMetaBean ic=iICCardApplyManager.getIcCardApplyDetail("fb37c498-bedf-420d-aa6e-ec4998e7aaa5");
        if(ic==null){
            System.out.println("查询不到");    
        }else{
            JsonSupport js=new JsonSupport();
            System.out.println(js.serialize(ic));
        }
    }
    
    
}
