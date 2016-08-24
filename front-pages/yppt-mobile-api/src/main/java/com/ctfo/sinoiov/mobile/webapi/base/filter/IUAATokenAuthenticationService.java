package com.ctfo.sinoiov.mobile.webapi.base.filter;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Index;


public interface IUAATokenAuthenticationService {
	
	public String validateTokenId(String tokenId);
	
	public Index queryUserInfoByUserLogin(String userLogin) throws Exception;
}
