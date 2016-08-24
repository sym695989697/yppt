package com.ctfo.gatewayservice.interfaces.service;

import java.util.List;

import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.bean.pay.Account;
import com.ctfo.gatewayservice.interfaces.bean.pay.CreateAccountParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.CreateAccountSmsCodeParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneyParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureResponse;
import com.ctfo.gatewayservice.interfaces.bean.pay.FreezeBalanceParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.GetCashierDeductUrlParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.RechargeToUserAccountParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.TransferAccountParams;

public interface IPayService {

	/**
	 * 创建帐户(老车后使用，不需要手机验证码)
	 * 
	 * @param createAccountParams
	 * @return 创建的帐户号码
	 * @throws YpptGatewayException
	 */
	public String createAccount(CreateAccountParams createAccountParams) throws YpptGatewayException;

	/**
	 * 创建帐户(需要手机验证码)
	 * 
	 * @param createAccountSmsCodeParams
	 * @return 创建的帐户号码
	 * @throws YpptGatewayException
	 */
	public String createAccountSmsCode(CreateAccountSmsCodeParams createAccountSmsCodeParams)
			throws YpptGatewayException;

	/***
	 * 根据账号查询账户信息
	 * 
	 * @param accountNo
	 *            账号
	 * @return 账号信息
	 * @throws YpptGatewayException
	 */
	public Account queryAccountByAccountNo(String accountNo) throws YpptGatewayException;

	/***
	 * 根据用户Id查询账户信息
	 * 
	 * @param userId
	 *            用户Id
	 * @return 账号信息(如果帐户不存在返回null)
	 * @throws YpptGatewayException
	 */
	public Account queryAccountByUserId(String userId) throws YpptGatewayException;

	/**
	 * 转帐
	 * 
	 * @param transferAccountParams
	 * @return 支付订单
	 * @throws YpptGatewayException
	 */
	public String transferAccounts(TransferAccountParams transferAccountParams) throws YpptGatewayException;

	/**
	 * 冻结帐户
	 * 
	 * @param freezeBalanceParams
	 * @return 支付订单
	 * @throws YpptGatewayException
	 */
	public String freezeBalance(FreezeBalanceParams freezeBalanceParams) throws YpptGatewayException;

	/**
	 * 解冻帐户
	 * 
	 * @param freezeBalanceParams
	 * @return 支付订单
	 * @throws YpptGatewayException
	 */
	public String unFreezeBalance(FreezeBalanceParams freezeBalanceParams) throws YpptGatewayException;

	/**
	 * 对帐
	 * 
	 * @return
	 * @throws YpptGatewayException
	 */
	public List<String> merchantOrderClearing() throws YpptGatewayException;

	/**
	 * 扣款确认
	 * 
	 * @param params
	 * @return 订单号和成功和失败的确认
	 * @throws YpptGatewayException
	 */
	public DeductMoneySureResponse deductMoneySure(DeductMoneySureParams params) throws YpptGatewayException;

	/**
	 * 获取取银台扣款URL
	 * 
	 * @param params
	 * @return 扣款URL
	 * @throws YpptGatewayException
	 */
	public String getCashierDeductMoneyUrl(GetCashierDeductUrlParams params) throws YpptGatewayException;

	/**
	 * 给车后老用户充值
	 * 
	 * @param params
	 * @return 支付订单
	 * @throws YpptGatewayException
	 */
	public String rechargeToUserAccount(RechargeToUserAccountParams params) throws YpptGatewayException;

	/**
	 * 扣款(油卡充值到中交收益帐户)
	 * 
	 * @param params
	 * @return 支付订单
	 * @throws YpptGatewayException
	 */
	public String deductMoney(DeductMoneyParams params) throws YpptGatewayException;

}
