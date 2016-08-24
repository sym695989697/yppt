package com.ctfo.yppt.bean;

import java.math.BigDecimal;

import com.ctfo.yppt.baseservice.system.beans.UserStat;

public class UserStatBean extends UserStat {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 使用中油卡数量
     */
    private int usedCardCount;
    /**
     * 申请中油卡数量
     */
    private int applyCardCount;
    /**
     * 申请失败卡数量
     */
    private int applyCardFailCount;
    /**
     * 卡余额
     */
    private BigDecimal cardBalance;
    /**
     * @return the usedCardCount
     */
    public int getUsedCardCount() {
        return usedCardCount;
    }
    /**
     * @param usedCardCount the usedCardCount to set
     */
    public void setUsedCardCount(int usedCardCount) {
        this.usedCardCount = usedCardCount;
    }
    /**
     * @return the applyCardCount
     */
    public int getApplyCardCount() {
        return applyCardCount;
    }
    /**
     * @param applyCardCount the applyCardCount to set
     */
    public void setApplyCardCount(int applyCardCount) {
        this.applyCardCount = applyCardCount;
    }
    /**
     * @return the cardBalance
     */
    public BigDecimal getCardBalance() {
        return cardBalance;
    }
    /**
     * @param cardBalance the cardBalance to set
     */
    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }
	public int getApplyCardFailCount() {
		return applyCardFailCount;
	}
	public void setApplyCardFailCount(int applyCardFailCount) {
		this.applyCardFailCount = applyCardFailCount;
	}
    
}
