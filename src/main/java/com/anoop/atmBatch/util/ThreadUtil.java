package com.anoop.atmBatch.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @author 532740
 * Executes a process on multiple threads
 */
public class ThreadUtil<T> {
private int threadCount;
private ExecutorService service;
private boolean executionShutDown=false;
private List<Future<T>> futureList = new ArrayList<Future<T>>();
public  List<Future<T>> getFutureList() {
	return futureList;
}
public ThreadUtil()
{
	
}
public ThreadUtil(int threadCount)
{
	if(threadCount>0)
	{
	this.threadCount = threadCount;
	service = Executors.newFixedThreadPool(this.threadCount);
	}
}
/**
 * Subject targets to multi-threading post validation 
 */
public  void startMultiThreading(final List<Callable<T>> targets)
{
	if(this.service != null && targets!=null && targets.size()>0)
		execute(targets);
}
/**
 * 
 * Multi-thread using concurrent api begins
 */
@SuppressWarnings("static-access")
private void execute(final List<Callable<T>> targets)
{
	try
	{
		for(int index =0; index<targets.size();index++)
		{
			Future<T> future = service.submit(targets.get(index));
			futureList.add(future);
		}
		this.service.shutdown();
		Thread.currentThread().sleep(100);
		this.executionShutDown=true;
	}
	catch(Exception exception)
	{
		exception.printStackTrace();
	}
}
public boolean getExecutionShutDown()
{
	return this.executionShutDown;
}
}
