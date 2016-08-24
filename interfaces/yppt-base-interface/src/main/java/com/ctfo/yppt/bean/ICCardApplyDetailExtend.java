package com.ctfo.yppt.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetail;

/**
 * 
 * 
 * 卡申请明细表扩展.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月26日    dxs    新建
 * </pre>
 */
public class ICCardApplyDetailExtend extends  ICCardApplyDetail implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -5893045243931915254L;
    /**
     * 卡状态
     * */
    private String state ;
    
    /**
     * 用户ID
     * */
    private String userId;
    
    /**
     * 创建时间
     */
    private Long createTime;
    
    /**
     * 审核时间
     */
    private Long auditTime;
    
    /**
     * 审核信息
     */
    private String reason;
    
    /**
     * 卡余额
     */
    private BigDecimal balance;
    

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the createTime
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the auditTime
     */
    public Long getAuditTime() {
        return auditTime;
    }

    /**
     * @param auditTime the auditTime to set
     */
    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}
