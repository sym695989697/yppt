/**
 * 北京中交兴路车联网科技有限公司 2011 版权所有.
 */
package com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BaseBeanReq;

/**
 * 
 * 查询支付中心交易信息列表
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
public class QueryPayCenterTradeListReq  extends BaseBeanReq {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     */
    private String userId ;
    /**
     * 账户号
     */
    private String accountNo;
    
    /**
     * deduction 支出; recorded收入  
     */
    private String bookAccountTypr;
    
    /** 开始页码 */
    private String page="1";
    
    /** 每页多少 */
    private String rows="10";
    
    /** 共计多少页 */
    private String pagesize = "1";
    
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
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }
    /**
     * @param accountNo the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    /**
     * @return the page
     */
    public String getPage() {
        return page;
    }
    /**
     * @param page the page to set
     */
    public void setPage(String page) {
        this.page = page;
    }
    /**
     * @return the rows
     */
    public String getRows() {
        return rows;
    }
    /**
     * @param rows the rows to set
     */
    public void setRows(String rows) {
        this.rows = rows;
    }
    /**
     * @return the pagesize
     */
    public String getPagesize() {
        return pagesize;
    }
    /**
     * @param pagesize the pagesize to set
     */
    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
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
    
}
