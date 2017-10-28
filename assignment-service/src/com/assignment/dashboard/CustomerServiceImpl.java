package com.assignment.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.demo.master.MasterDao;
import com.demo.utility.Employee;
@Configuration
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	MasterDao<Employee, ?>   dao;
	@Override
	public List<Employee> getCustomerData(String cqlString, Employee e) {
		List<Employee> lst=(List<Employee>) dao.selectDataFromDB("select * from employee",  e);
		return lst;
	}

}
