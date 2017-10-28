package com.assignment.dashboard;




import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import com.demo.master.MasterDao;
import com.demo.utility.Employee;

@Configuration
@Service("dashboardservice")
public class DashBoardServiceImpl<E,PK extends Serializable>   implements DashboardDaoService<E,PK>  {
	@Autowired
	MasterDao<E,PK>   dao;
	 
	@Override
	
	public void addEmployeeData(String employee) {
		 System.out.println("insd save ment");
        dao.getMessageDisp(employee);
		
	}
	 
	
	 
	@SuppressWarnings("unchecked")
	@Override
	public String insertdataintoEmployeeTable(com.demo.utility.Employee entity) {
		//dao.insertDataIntoTableMaster((E) entity);
		 return null;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeeList(String cqlQuery, Employee e) {
		List<Employee> lst=(List<Employee>) dao.selectDataFromDB("select * from employee", (E) e);
		return lst;
	}



	@Override
	public String getMessage(String message) {
		
		dao.getMessageDisp(message);
		
		List<Map<String, Object>> rows=dao.getQueryData("select * from PHASE");
		for(Map<String, Object> row : rows){
	          String id = row.get("ID").toString();
	          System.out.println(id);
	        }
		
		return message;
	}
	
}
