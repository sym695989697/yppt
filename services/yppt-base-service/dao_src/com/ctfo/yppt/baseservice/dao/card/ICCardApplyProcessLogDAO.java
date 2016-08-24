package com.ctfo.yppt.baseservice.dao.card;

import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLog;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLogExample;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyProcessLogExampleExtended;
import java.util.List;

public interface ICCardApplyProcessLogDAO {

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    void insert(ICCardApplyProcessLog record);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    int updateByPrimaryKey(ICCardApplyProcessLog record);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    int updateByPrimaryKeySelective(ICCardApplyProcessLog record);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    List selectByExample(ICCardApplyProcessLogExample example);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    ICCardApplyProcessLog selectByPrimaryKey(String id);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    int deleteByExample(ICCardApplyProcessLogExample example);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    int countByExample(ICCardApplyProcessLogExample example);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    int updateByExampleSelective(ICCardApplyProcessLog record,
            ICCardApplyProcessLogExample example);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    int updateByExample(ICCardApplyProcessLog record,
            ICCardApplyProcessLogExample example);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    List selectByExampleWithPage(ICCardApplyProcessLogExample example);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    List selectByExampleWithPage(
            ICCardApplyProcessLogExampleExtended exampleExtended);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    List getKeyBy(ICCardApplyProcessLogExample example);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    String getNameSpace();

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    List getKeyBy(ICCardApplyProcessLogExampleExtended example);

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_APPLY_PROCESS
     * @abatorgenerated  Sun Jan 25 16:48:53 CST 2015
     */
    int countByExample(ICCardApplyProcessLogExampleExtended example);
}