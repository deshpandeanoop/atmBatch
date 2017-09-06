package com.anoop.atmBatch.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.anoop.atmBatch.util.Constants;
import com.anoop.atmBatch.util.ThreadUtil;

/**
 * Holds Account holder information
 * @author 532740
 *
 */
public class AccountHolder implements ApplicationContextAware
{
private String firstName;
private String lastName;
private String accountNumber;
private String debitCardNumber;
private List<AccountHolder> accountHolderList;
private ApplicationContext applicationContext;

public AccountHolder()
{
	
}

public AccountHolder(String firstName, String lastName, String accountNumber, String debitCardNumber) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.accountNumber = accountNumber;
	this.debitCardNumber = debitCardNumber;
	
}


public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getDebitCardNumber() {
	return debitCardNumber;
}

public void setDebitCardNumber(String debitCardNumber) {
	this.debitCardNumber = debitCardNumber;
}

public ApplicationContext getApplicationContext() {
	return applicationContext;
}

public List<AccountHolder> getAccountHolderList()
{
	return this.accountHolderList;
}


public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
this.applicationContext = applicationContext;
generateAccountHoldersList();
}
@SuppressWarnings("unchecked")
private void generateAccountHoldersList()
{
	try
	{
	if(applicationContext!=null)
	{
		ThreadUtil<List<String>> threadUtilBean = applicationContext.getBean(Constants.PERMUTATE_THREAD_BEAN, ThreadUtil.class);
		List<Future<List<String>>> futureList = threadUtilBean.getFutureList();
		List<String> firstNameList = null;
		List<String> lastNameList = null;
		List<String> accountNumberList = null;
		List<String> debitCardNumberList = null;
		
		firstNameList = futureList.get(0).get();
		lastNameList = futureList.get(1).get();
		accountNumberList = futureList.get(2).get();
		debitCardNumberList = futureList.get(3).get();
		int length = firstNameList.size();
		AccountHolder accountHolder = null;
		this.accountHolderList = new ArrayList<AccountHolder>();
		
		for(int index=0;index<length;index++)
		{
			accountHolder = new AccountHolder(firstNameList.get(index), lastNameList.get(index), accountNumberList.get(index), debitCardNumberList.get(index));
			accountHolderList.add(accountHolder);
		}
	}
	}
	catch(Exception exception)
	{
		exception.printStackTrace();
	}
}

public String getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
}
