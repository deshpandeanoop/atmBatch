package com.anoop.atmBatch.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anoop.atmBatch.bean.AccountHolder;
import com.anoop.atmBatch.dao.AccountHolderDAO;
import com.anoop.atmBatch.util.Constants;
import com.anoop.atmBatch.util.DataBaseOperation;
import com.anoop.atmBatch.util.DataGenerate;
import com.anoop.atmBatch.util.TaskUtil;
import com.anoop.atmBatch.util.ThreadUtil;

/**
 * 
 * @author 532740
 * 
 */
public class DataLoadMain{
	private static ApplicationContext applicationContext;
	public static void main(String[] args) {
		try
		{
			applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
			DataLoadMain dataLoad = applicationContext.getBean(Constants.DATA_LOAD_BEAN,DataLoadMain.class);
			dataLoad.startProcess();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}

	}
/**
 * Action: Load process begin
 */
	@SuppressWarnings("unchecked")
	private void startProcess() throws Exception
	{
		DataGenerate dataGenerateBean = null;
		DataBaseOperation<AccountHolderDAO,AccountHolder> databaseOperation = null;
		ThreadUtil<List<String>> threadUtilBeanForPermutate = null;
		ThreadUtil<Integer> threadUtilBeanForInsert = null;
		List<Callable<List<String>>> permutateTargets = new ArrayList<Callable<List<String>>>();
		List<Callable<Integer>> insertTargets = new ArrayList<Callable<Integer>>();
		dataGenerateBean = applicationContext.getBean(Constants.FIRST_NAME_GENERATE_BEAN, DataGenerate.class);
		permutateTargets.add(dataGenerateBean);
		dataGenerateBean = applicationContext.getBean(Constants.LAST_NAME_GENERATE_BEAN, DataGenerate.class);
		permutateTargets.add(dataGenerateBean);
		dataGenerateBean = applicationContext.getBean(Constants.ACC_NUMBER_GENERATE_BEAN, DataGenerate.class);
		permutateTargets.add(dataGenerateBean);
		dataGenerateBean = applicationContext.getBean(Constants.DEBIT_CARD_GENERATE_BEAN, DataGenerate.class);
		permutateTargets.add(dataGenerateBean);
		threadUtilBeanForPermutate = (ThreadUtil<List<String>>)applicationContext.getBean(Constants.PERMUTATE_THREAD_BEAN);
		threadUtilBeanForPermutate.startMultiThreading(permutateTargets);
	
		while(!threadUtilBeanForPermutate.getExecutionShutDown());
		
		AccountHolder accountHolderBean = applicationContext.getBean(Constants.ACCOUNT_HOLDER_BEAN,AccountHolder.class);
		TaskUtil taskUtilBean = applicationContext.getBean(Constants.TASK_UTIL_BEAN,TaskUtil.class);
		List<List<AccountHolder>> distributedList = taskUtilBean.splitListOnMultipleThreads(accountHolderBean.getAccountHolderList());
		
		for(List<AccountHolder> list:distributedList)
		{
			databaseOperation = new DataBaseOperation<AccountHolderDAO,AccountHolder>(AccountHolderDAO.class,list);
			insertTargets.add(databaseOperation);
		}
		threadUtilBeanForInsert = applicationContext.getBean(Constants.INSERT_THREAD_BEAN, ThreadUtil.class);
		threadUtilBeanForInsert.startMultiThreading(insertTargets);
		
		while(!threadUtilBeanForInsert.getExecutionShutDown());
	}
}
