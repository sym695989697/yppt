package com.ctfo.yppt.portal.view.oilcard;

import java.io.Serializable;
import java.util.List;

/**
 * 油卡充值集合
 * 
 * @author fuxiaohui
 *
 * @datetime 2014-11-29 下午02:01:22
 *
 */
public class OilCardChargeVos implements Serializable{
    
	/**
     * 
     */
    private static final long serialVersionUID = 7620540925290630614L;
    /**
     * 
     */
    private List<OilCardChargeVo> cardRecharges;

	public List<OilCardChargeVo> getCardRecharges() {
		return cardRecharges;
	}

	public void setCardRecharges(List<OilCardChargeVo> cardRecharges) {
		this.cardRecharges = cardRecharges;
	}

}
