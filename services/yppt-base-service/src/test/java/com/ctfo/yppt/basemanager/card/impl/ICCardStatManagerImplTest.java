package com.ctfo.yppt.basemanager.card.impl;

import java.math.BigDecimal;

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
import com.ctfo.yppt.baseservice.system.beans.CardStat;
import com.ctfo.yppt.baseservice.system.intf.IICCardStatManager;
/**
 * 
 * 
 * 卡统计单元测试.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月22日    dxs    新建
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all.xml")
public class ICCardStatManagerImplTest {
    private Log logger = LogFactory.getLog(this.getClass());
    
    @Autowired
    IICCardStatManager iICCardStatManager;

    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testGetIICCardStatByCardId()
            throws BusinessException{
        String cardId="ddddddd";
        CardStat cs=iICCardStatManager.getIICCardStatByCardId(cardId);
        JsonSupport js=new JsonSupport();
        logger.info(String.format("查询结果为%s", js.serialize(cs)));
    }
    
    
    @Test
    public void testGetICCardLastConsumptionAndRecharge()
            throws BusinessException{
        String cardId="ddddddd";
        BigDecimal[] bd=iICCardStatManager.getICCardLastConsumptionAndRecharge(cardId,"2015-01");
        JsonSupport js=new JsonSupport();
        logger.info(String.format("查询结果为%s", js.serialize(bd)));
    }
}
