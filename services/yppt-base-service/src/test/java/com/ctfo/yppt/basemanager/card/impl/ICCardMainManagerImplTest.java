package com.ctfo.yppt.basemanager.card.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.soa.support.JsonSupport;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardMainManager;
import com.ctfo.yppt.bean.ICCardMainMetaBean;

/**
 * 
 * 
 * 主卡管理单元测试.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月22日    dxs    新建
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all.xml")
public class ICCardMainManagerImplTest {
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    IICCardMainManager iICCardMainManager;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testQueryIICCardMainAndChildCardNum() throws Exception {
        try {
            ICCardMainExampleExtended icmee = new ICCardMainExampleExtended();
            PaginationResult<ICCardMainMetaBean> bb = iICCardMainManager
                    .queryIICCardMainAndChildCardNum(icmee);
            JsonSupport jsonSupport = new JsonSupport();
            logger.info(String.format("查询结果为：%s", jsonSupport.serialize(bb)));
        } catch (Exception e) {
            logger.error(String.format(
                    "testQueryIICCardMainAndChildCardNum执行报错%s", e));
            throw new BusinessException(String.format(
                    "testQueryIICCardMainAndChildCardNum执行报错%s", e), e);
        }
    }

    @Test
    public void testAdd() throws Exception {
        try {
            ICCardMain icm = new ICCardMain();
            icm.setCardNumber("1000113500002493297");
            icm.setCardType("0");
            icm.setOpencardofficecode("5031327b-39ec-4001-9748-2b0b533a850d");
            icm.setCtrdituser("2d982b63-f94b-4b23-91ef-9f3aaad66a48");
            icm.setAccountId("idontknow");
            iICCardMainManager.add(icm);
        } catch (Exception e) {
            logger.error(String.format(
                    "testQueryIICCardMainAndChildCardNum执行报错%s", e));
            throw new BusinessException(String.format(
                    "testQueryIICCardMainAndChildCardNum执行报错%s", e));
        }
    }

    @Test
    public void testUpdate() throws Exception {
        try {
            ICCardMainExampleExtended icmee = new ICCardMainExampleExtended();
            icmee.createCriteria().andCardNumberEqualTo("1000113500002493297");
            List<ICCardMain> list = iICCardMainManager.getList(icmee);

            if (list != null && list.size() > 0) {
                ICCardMain icm = new ICCardMain();
                icm.setId(list.get(0).getId());
                icm.setAccountId("iknow");
                iICCardMainManager.update(icm);
            }

        } catch (Exception e) {
            logger.error(String.format("testUpdate执行报错%s", e));
            throw new BusinessException(String.format("testUpdate执行报错%s", e));
        }
    }

    @Test
    public void testUpdateICCardMainBalance() throws Exception {
        try {
            int result = iICCardMainManager.updateICCardMainBalance(
                    "CARD-TYPE-02", "91302900004148411", new BigDecimal("100"));
            System.out.println("更新结果："+result);

        } catch (Exception e) {
            logger.error(String.format("testUpdateICCardMainBalance执行报错%s", e));
            throw new BusinessException(String.format(
                    "testUpdateICCardMainBalance执行报错%s", e));
        }
    }

}
