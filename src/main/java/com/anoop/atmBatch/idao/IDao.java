/**
 * 
 */
package com.anoop.atmBatch.idao;

import java.util.List;

/**
 * @author Anoop Deshpande
 * Interface that exposes business functionalities
 */
public interface IDao <T>{
	public void invoke(String operationType,List<T> list)throws Exception;
}
