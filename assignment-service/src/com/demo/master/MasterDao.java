package com.demo.master;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.assingment.entity.Deal;
import com.assingment.entity.Employee;




public interface MasterDao<E,PK extends Serializable> {
   	 String getMessageDisp(String messageKey);
     void inserData(E e);
     List<E> selectDataFromDB(String sqlQuery,E e);
	 List<E> selectAll(String sqlQuery);
	 List<Map<String, Object>> getQueryData(String sql);
	 public PK saveData(E entity);
	 
	 public void insertBatchCollection(String str,List<Deal> deals);
	 
	 
	 
	 
	 
}
