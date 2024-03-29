package com.ctfo.yppt.baseservice.dao.card;

import com.ctfo.yppt.baseservice.card.beans.ICCardMain;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExample;
import com.ctfo.yppt.baseservice.card.beans.ICCardMainExampleExtended;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ICCardMainDAOImpl extends SqlMapClientDaoSupport implements ICCardMainDAO {

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public ICCardMainDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void insert(ICCardMain record) {
        getSqlMapClientTemplate().insert(
                "TB_IC_CARD_MAIN.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public int updateByPrimaryKey(ICCardMain record) {
        int rows = getSqlMapClientTemplate().update(
                "TB_IC_CARD_MAIN.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public int updateByPrimaryKeySelective(ICCardMain record) {
        int rows = getSqlMapClientTemplate().update(
                "TB_IC_CARD_MAIN.abatorgenerated_updateByPrimaryKeySelective",
                record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public List selectByExample(ICCardMainExample example) {
        List list = getSqlMapClientTemplate().queryForList(
                "TB_IC_CARD_MAIN.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public ICCardMain selectByPrimaryKey(String id) {
        ICCardMain key = new ICCardMain() {
        };
        key.setId(id);
        ICCardMain record = (ICCardMain) getSqlMapClientTemplate()
                .queryForObject(
                        "TB_IC_CARD_MAIN.abatorgenerated_selectByPrimaryKey",
                        key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public int deleteByExample(ICCardMainExample example) {
        int rows = getSqlMapClientTemplate().delete(
                "TB_IC_CARD_MAIN.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public int deleteByPrimaryKey(String id) {
        ICCardMain key = new ICCardMain() {
        };
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete(
                "TB_IC_CARD_MAIN.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public int countByExample(ICCardMainExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
                "TB_IC_CARD_MAIN.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public int updateByExampleSelective(ICCardMain record,
            ICCardMainExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update(
                "TB_IC_CARD_MAIN.abatorgenerated_updateByExampleSelective",
                parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public int updateByExample(ICCardMain record, ICCardMainExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update(
                "TB_IC_CARD_MAIN.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public List selectByExampleWithPage(ICCardMainExample example) {
        List list = getSqlMapClientTemplate().queryForList(
                "TB_IC_CARD_MAIN.abatorgenerated_selectByExamplePage", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public List selectByExampleWithPage(
            ICCardMainExampleExtended exampleExtended) {
        List list = getSqlMapClientTemplate().queryForList(
                "TB_IC_CARD_MAIN.abatorgenerated_selectByExampleExtendedPage",
                exampleExtended);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public List getKeyBy(ICCardMainExample example) {
        List list = getSqlMapClientTemplate().queryForList(
                "TB_IC_CARD_MAIN.abatorgenerated_selectKeyBy", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getNameSpace() {
        return "TB_IC_CARD_MAIN";
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public List getKeyBy(ICCardMainExampleExtended example) {
        List list = getSqlMapClientTemplate().queryForList(
                "TB_IC_CARD_MAIN.abatorgenerated_selectKeyByExtended", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public int countByExample(ICCardMainExampleExtended example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
                "TB_IC_CARD_MAIN.abatorgenerated_countByExampleExtended",
                example);
        return count.intValue();
    }

    /**
     * This class was generated by Abator for iBATIS. This class corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    private static class UpdateByExampleParms extends ICCardMainExample {
        private Object record;

        public UpdateByExampleParms(Object record, ICCardMainExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}