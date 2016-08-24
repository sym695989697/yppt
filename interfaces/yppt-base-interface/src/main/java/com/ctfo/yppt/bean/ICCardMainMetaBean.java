package com.ctfo.yppt.bean;

import java.io.Serializable;

import com.ctfo.yppt.baseservice.card.beans.ICCardMain;

/**
 * 
 * 
 * IC卡复杂复杂对象.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月21日    dxs    新建
 * </pre>
 */
public class ICCardMainMetaBean extends ICCardMain implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -6489214373188599544L;
    /**
     * 子卡数量
     */
    private int childCardNum;
    /**
     * @return the childCardNum
     */
    public int getChildCardNum() {
        return childCardNum;
    }
    /**
     * @param childCardNum the childCardNum to set
     */
    public void setChildCardNum(int childCardNum) {
        this.childCardNum = childCardNum;
    }
}
