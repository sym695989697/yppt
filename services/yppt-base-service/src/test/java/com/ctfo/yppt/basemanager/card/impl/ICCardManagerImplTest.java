package com.ctfo.yppt.basemanager.card.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.csm.soa.support.JsonSupport;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;

/**
 * 
 * 
 * 子卡管理测试单元.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月22日    dxs    新建
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all.xml")
public class ICCardManagerImplTest {
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    IICCardManager iICCardManager;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetCardsBalanceByUserId() throws BusinessException {
        try {
            BigDecimal bd = iICCardManager.getCardsBalanceByUserId("abc2130f-aadb-42b9-b0f0-db360d960790");

            JsonSupport jsonSupport = new JsonSupport();
            logger.info(String.format("查询结果为：%s",
                    jsonSupport.serialize(bd.doubleValue())));
        } catch (Exception e) {
            logger.error(String.format("testGetCardsBalanceByUserId执行报错%s", e.getCause()));
            throw new BusinessException(String.format(
                    "testGetCardsBalanceByUserId执行报错%s", e.getCause()));
        }
    }

    @Test
    public void testAddEmptyCardBatch() throws BusinessException {
        try {
            List<ICCard> list = new ArrayList<ICCard>();

            ICCard iCCard = new ICCard();
            iCCard.setCardNumber("1000113500002499420");
            iCCard.setParentId("0a3bb982-29da-4ab1-93a7-fb223444a40d");
            iCCard.setCardType("1");
            iCCard.setCtrdituser("2d982b63-f94b-4b23-91ef-9f3aaad66a48");
            
            ICCard iCCard2 = new ICCard();
            iCCard.setCardNumber("1000113500002499422");
            iCCard.setParentId("0a3bb982-29da-4ab1-93a7-fb223444a40d");
            iCCard.setCardType("1");
            iCCard.setCtrdituser("2d982b63-f94b-4b23-91ef-9f3aaad66a48");
            
            ICCard iCCard3 = new ICCard();
            iCCard.setCardNumber("1000113500002499423");
            iCCard.setParentId("0a3bb982-29da-4ab1-93a7-fb223444a40d");
            iCCard.setCardType("1");
            iCCard.setCtrdituser("2d982b63-f94b-4b23-91ef-9f3aaad66a48");
            
            list.add(iCCard);
            list.add(iCCard2);
            list.add(iCCard3);
            
            Map<String,String> result = iICCardManager.addEmptyCardBatch(list);

            JsonSupport jsonSupport = new JsonSupport();
            logger.info(String.format("添加结果为：%s", jsonSupport.serialize(result)));
        } catch (Exception e) {
            logger.error(String.format("testAddEmptyCardBatch执行报错%s", e.getCause()));
            throw new BusinessException(String.format(
                    "testAddEmptyCardBatch执行报错%s", e.getCause()));
        }
    }

    @Test
    public void testAdd() throws BusinessException {
        try {
           
            ICCard iCCard = new ICCard();
            iCCard.setCardNumber("1000113500002499424");
            iCCard.setParentId("0a3bb982-29da-4ab1-93a7-fb223444a40d");
            iCCard.setCardType("1");
            iCCard.setCtrdituser("2d982b63-f94b-4b23-91ef-9f3aaad66a48");
            String uuid=iICCardManager.add(iCCard);
            
            JsonSupport jsonSupport = new JsonSupport();
            logger.info(String.format("添加结果为：%s", jsonSupport.serialize(uuid)));
        } catch (Exception e) {
            logger.error(String.format("testAdd执行报错%s", e.getCause()));
            throw new BusinessException(String.format("testAdd执行报错%s", e.getCause()));
        }
    }

    @Test
    public void testUpdate() throws BusinessException {
        try {
            
            ICCardExampleExtended icee=new ICCardExampleExtended();
            icee.createCriteria().andCardNumberEqualTo("1000113500002499424");
            List<ICCard> list=iICCardManager.getList(icee);
            if(list!=null && list.size()>0){
                ICCard iCCard = new ICCard();
                iCCard.setId(list.get(0).getId());
                iCCard.setState("t");
                int number=iICCardManager.update(iCCard);
                JsonSupport jsonSupport = new JsonSupport();
                logger.info(String.format("修改结果为：%s", jsonSupport.serialize(number)));
            }
           
        } catch (Exception e) {
            logger.error(String.format("testUpdate执行报错%s", e));
            throw new BusinessException(String.format("testUpdate执行报错%s", e.getCause()));
        }
    }
    
    
    @Test
    public void testUpdateICCardBalance() throws Exception {
        try {
            int result = iICCardManager.updateICCardBalance(
                    "CARD-TYPE-01", "8130280000408810", new BigDecimal("100"));
            System.out.println("更新结果："+result);

        } catch (Exception e) {
            logger.error(String.format("testUpdateICCardBalance执行报错%s", e));
            throw new BusinessException(String.format(
                    "testUpdateICCardBalance执行报错%s", e));
        }
    }

}
