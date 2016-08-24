/**
 * 北京中交兴路车联网科技有限公司 2011 版权所有.
 */
package com.ctfo.sinoiov.mobile.webapi.bean.vo;

import java.io.Serializable;

/**
 * 
 * 支付中心交易信息实体
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
public class PayCenterTradeVO implements Serializable{
        
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 交易ID
     */
    private String id ;
    
    /**
     * 交易备注
     */
    private String orderRemarks ;
    /**
     * 交易号
     */
    private String orderNo;
    
    /**
     * 交易钱数
     */
    private String bookAccountMoney;
    
    /**
     * 交易后钱数
     */
    private String bookCurrentMoney;
    
    /**
     * 交易时间
     */
    private String bookAccountTime;
    /**
     * 交易类型 （支出/收入）
     */
    private String bookAccountTypr;
    
    /**
     * 科目类型标识
     */
    private String orderType ;
    
    /**
     * 科目类型名字   转账（付款）等
     */
    private String orderTypeLuc;
    
    /**
     * @return the orderRemarks
     */
    public String getOrderRemarks() {
        return orderRemarks;
    }
    /**
     * @param orderRemarks the orderRemarks to set
     */
    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }
    /**
     * @return the orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }
    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    /**
     * @return the bookAccountMoney
     */
    public String getBookAccountMoney() {
        return bookAccountMoney;
    }
    /**
     * @param bookAccountMoney the bookAccountMoney to set
     */
    public void setBookAccountMoney(String bookAccountMoney) {
        this.bookAccountMoney = bookAccountMoney;
    }
    /**
     * @return the bookAccountTime
     */
    public String getBookAccountTime() {
        return bookAccountTime;
    }
    /**
     * @param bookAccountTime the bookAccountTime to set
     */
    public void setBookAccountTime(String bookAccountTime) {
        this.bookAccountTime = bookAccountTime;
    }
    /**
     * @return the bookAccountTypr
     */
    public String getBookAccountTypr() {
        return bookAccountTypr;
    }
    /**
     * @param bookAccountTypr the bookAccountTypr to set
     */
    public void setBookAccountTypr(String bookAccountTypr) {
        this.bookAccountTypr = bookAccountTypr;
    }
    /**
     * @return the orderType
     */
    public String getOrderType() {
        return orderType;
    }
    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    /**
     * @return the orderTypeLuc
     */
    public String getOrderTypeLuc() {
        return orderTypeLuc;
    }
    /**
     * @param orderTypeLuc the orderTypeLuc to set
     */
    public void setOrderTypeLuc(String orderTypeLuc) {
        this.orderTypeLuc = orderTypeLuc;
    }
    /**
     * @return the bookCurrentMoney
     */
    public String getBookCurrentMoney() {
        return bookCurrentMoney;
    }
    /**
     * @param bookCurrentMoney the bookCurrentMoney to set
     */
    public void setBookCurrentMoney(String bookCurrentMoney) {
        this.bookCurrentMoney = bookCurrentMoney;
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
 
}
