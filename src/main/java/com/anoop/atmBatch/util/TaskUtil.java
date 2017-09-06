package com.anoop.atmBatch.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 532740
 * Adjunct component  
 */
public class TaskUtil 
{
	private int threadCount;
	
	public TaskUtil(int threadCount) {
		super();
		this.threadCount = threadCount;
	}

	/**
	 * splits given list on multiple threads
	 * 
	 */
 public <T> List<List<T>> splitListOnMultipleThreads(List<T> input) throws Exception
 {
	 List<List<T>> resultantList = null;
	 if(this.threadCount>0 && input!=null)
	 {
		int length = input.size();
		resultantList = new ArrayList<List<T>>();
		int index = 0;
		while(length>threadCount)
		{
			resultantList.add(input.subList(index, index+threadCount));
			index = index+threadCount;
			length = length-threadCount;
		}
		if(length>0)
			resultantList.add(input.subList(index, input.size()));
	 }
	 return resultantList;
 }
}
