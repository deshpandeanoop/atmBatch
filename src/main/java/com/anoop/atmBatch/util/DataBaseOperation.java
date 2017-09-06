package com.anoop.atmBatch.util;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anoop.atmBatch.idao.IDao;


/**
 * 
 * @author 532740
 * Loads Account Holder details into table
 */
public class DataBaseOperation <U,T> implements Callable<Integer>
{
	private Class<U> claz;
	private List<T> dataLoad;
	private ApplicationContext applicationContext;
	public Integer call()  {
		insertData(this.dataLoad, this.claz);
		return 0;
	}
	public DataBaseOperation(Class<U> claz, List<T> dataLoad) {
		super();
		this.claz = claz;
		this.dataLoad = dataLoad;
	}
	@SuppressWarnings("unchecked")
    private  void insertData(final List<T> list,Class<U> claz)
    {
      try
      {
    	  applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
    	  IDao<T> dao = (IDao<T>) applicationContext.getBean(claz.getSimpleName(), claz);
    	  dao.invoke(Constants.INSERT_OPERATION, list);
      }
      catch(Exception exception)
      {
    	  exception.printStackTrace();
      }
    }
	 
}
