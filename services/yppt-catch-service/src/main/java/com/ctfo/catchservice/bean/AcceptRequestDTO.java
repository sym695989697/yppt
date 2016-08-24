package com.ctfo.catchservice.bean;

/**
 * 
 * @Description:队列任务封装DTO，依据此对象数据，进行队列任务中具体任务执行
 * @author yuguangyang
 * @date 2015年2月28日 上午11:07:09
 *
 */
public class AcceptRequestDTO {
	//-------条件，通过请求传送所得
	private String userName;//用户名
	private String hostType;//网站类型
	private String cardNo;//卡号
	private String startDate;//开始日期
	private String endDate;//结束日期
	//-----定义队列所需相关属性，暂包括队列属性及其当前运行状态
	private  String runningStatel;//任务运行状态：0：待处理，默认为待处理
	private int  workerType;//任务属性，对应此类WorkerTypes
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHostType() {
		return hostType;
	}
	public void setHostType(String hostType) {
		this.hostType = hostType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRunningStatel() {
		return runningStatel;
	}
	public void setRunningStatel(String runningStatel) {
		this.runningStatel = runningStatel;
	}
	public int getWorkerType() {
		return workerType;
	}
	public void setWorkerType(int  workerType) {
		this.workerType = workerType;
	}
}
