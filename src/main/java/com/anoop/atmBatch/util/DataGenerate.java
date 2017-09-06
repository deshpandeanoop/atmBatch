package com.anoop.atmBatch.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 
 * @author 532740
 * Generates Permutated Lists
 */
public class DataGenerate implements Callable<List<String>>
{
private List<String> permutatedList = new ArrayList<String>();
private String input;
public DataGenerate(String input) {
	super();
	this.input = input;
}
public List<String> call() {
	permutate(this.input);
	return this.permutatedList;
}

/**
 * Delegates request for permutation
 * 
 */
private void permutate(String input)
{
	permutate(Constants.EMPTY_STRING, input);
}
/**
 * Generates permutated list
 * 
 */
private void permutate(String prefix,String input)
{
	int length = input.length();
	if(length==0)
	{
		permutatedList.add(prefix);
	}
	else
	{
		for(int index=0;index<length;index++)
		{
			permutate(prefix+input.charAt(index), input.substring(0, index)+input.substring(index+1, length));
		}
	}
}
}
