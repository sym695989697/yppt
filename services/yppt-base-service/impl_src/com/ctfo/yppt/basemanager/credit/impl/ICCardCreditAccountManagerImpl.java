package com.ctfo.yppt.basemanager.credit.impl;

import org.springframework.stereotype.Service;

import com.ctfo.common.manager.support.GenericManagerImpl;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIOExampleExtended;
import com.ctfo.yppt.baseservice.credit.intf.IICCardCreditAccountManager;

@Service("ICCardCreditAccountManagerImpl")
public class ICCardCreditAccountManagerImpl extends GenericManagerImpl<ICCardCreditAccountIO, ICCardCreditAccountIOExampleExtended>implements IICCardCreditAccountManager {

}
