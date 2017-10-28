package com.demo.master;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.assingment.entity.Deal;

public interface MasterDaoTemplate<E, PK extends Serializable> {

	public void insertBatchCollection(List<Deal> lst);

	public boolean checkExistData(String file_name);

	public void deleteByQuery(String query);

	public List<Map<String, Object>> selectAll(String query);

	public void insterByQuery(String sql);

	public Map<String, Long> getAccumulativeCountList();

	public void insertAccumulativeCount(List<String> cumulavtivCountList);

	public boolean checkExistFiel(String fileName);

	public void deletAccumulativeCountData();
	 
	public List<String> getListForCount();

	public List<E> selectAllData(String query);
	
	public int selectCount(String query);
	
	 
}
