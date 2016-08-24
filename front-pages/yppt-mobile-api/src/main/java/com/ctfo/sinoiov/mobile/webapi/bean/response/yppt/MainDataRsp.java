package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.io.Serializable;
import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;


/**
 * 参数名称	参数编码	是否必填	说明
	累计返利旺金币数量	totalBackCoinCount	是	类型：整型；累计旺金币返利的int值
	上月返利	lastMonthBackCoinCount	是	类型：int；上月返利的金币数量
	累计加油（元）	totalOilingY	是	类型：double；累计加油消费的钱数
	累计加油（L）	totalOilingL	是	类型：double；累计加油的量，单位是升（L）
	上月加油（元）	lastMonthOilingY	是	类型：double；上月加油消费的钱数
	上月加油（升）	lastMonthOilingL	是	类型：double；上月加油的量，单位是升（L）
	我的油卡张数	cardCount	是	类型：整型；用户绑定油卡张数的int值
	油卡余额	cardBalance	是	类型：double；用户油卡余额
	油卡优惠信息图片	cardFavorableUrl	是	类型：字符串；图片的url
	车旺油卡宣传图片1	cardPromotionalPictureFirstUrl	是	类型：字符串；图片的url
	车旺油卡宣传图片2	cardPromotionalPictureSecondUrl	是	类型：字符串；图片的url
	车旺油卡宣传图片3	cardPromotionalPictureThirdUrl	是	类型：字符串；图片的url

 *
 */
	
public class MainDataRsp implements Body ,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String totalOilingY;		//累计加油（元）
	
	private String totalOilingL;		//累计加油（L）
	
	private String lastMonthOilingY;	//上月加油（元）
	
	private String lastMonthOilingL;	//上月加油（升）

	private String lastMonthBackCoinCount ; 		//上月返利旺金币数量
	
	private String totalBackCoinCount ;   //累计返利旺金币数量
	
	private int cardCount ; 			//我的油卡张数
	
	private String cardFavorableUrl; 	// 油卡优惠信息图片
	
	private String cardBalance;  //卡余额
	
	
	private List<ImageBean> imageUrl;
	
	private String applyCardCount; 		//申请中的卡数量
	
	private String applyCardFailCount; 		//申请失败的卡数量
	
	
	private String coinIntroduceUrl;	//旺金币介绍
	

	public int getCardCount() {
		return cardCount;
	}
	
	public String getTotalOilingY() {
		return totalOilingY;
	}

	public void setTotalOilingY(String totalOilingY) {
		this.totalOilingY = totalOilingY;
	}

	public String getTotalOilingL() {
		return totalOilingL;
	}

	public void setTotalOilingL(String totalOilingL) {
		this.totalOilingL = totalOilingL;
	}

	public String getLastMonthOilingY() {
		return lastMonthOilingY;
	}

	public void setLastMonthOilingY(String lastMonthOilingY) {
		this.lastMonthOilingY = lastMonthOilingY;
	}

	public String getLastMonthOilingL() {
		return lastMonthOilingL;
	}

	public void setLastMonthOilingL(String lastMonthOilingL) {
		this.lastMonthOilingL = lastMonthOilingL;
	}

	public String getLastMonthBackCoinCount() {
		return lastMonthBackCoinCount;
	}

	public void setLastMonthBackCoinCount(String lastMonthBackCoinCount) {
		this.lastMonthBackCoinCount = lastMonthBackCoinCount;
	}

	public String getTotalBackCoinCount() {
		return totalBackCoinCount;
	}

	public void setTotalBackCoinCount(String totalBackCoinCount) {
		this.totalBackCoinCount = totalBackCoinCount;
	}

	public String getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}

	

	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}

	public String getCardFavorableUrl() {
		return cardFavorableUrl;
	}

	public void setCardFavorableUrl(String cardFavorableUrl) {
		this.cardFavorableUrl = cardFavorableUrl;
	}

	public String getApplyCardCount() {
		return applyCardCount;
	}

	public void setApplyCardCount(String applyCardCount) {
		this.applyCardCount = applyCardCount;
	}

	public List<ImageBean> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(List<ImageBean> imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCoinIntroduceUrl() {
		return coinIntroduceUrl;
	}

	public void setCoinIntroduceUrl(String coinIntroduceUrl) {
		this.coinIntroduceUrl = coinIntroduceUrl;
	}

	public String getApplyCardFailCount() {
		return applyCardFailCount;
	}

	public void setApplyCardFailCount(String applyCardFailCount) {
		this.applyCardFailCount = applyCardFailCount;
	}
	
	

	

	
}
