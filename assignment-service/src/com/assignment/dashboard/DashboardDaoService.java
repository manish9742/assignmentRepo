package com.assignment.dashboard;

import java.io.Serializable;
import java.util.List;





public interface DashboardDaoService<E,PK extends Serializable>   {
	public void addEmployeeData(String employee);
    public String insertdataintoEmployeeTable(com.demo.utility.Employee e);
    public List<com.demo.utility.Employee> getEmployeeList(String cqlQuery,com.demo.utility.Employee e);
    public String getMessage(String message);

}
