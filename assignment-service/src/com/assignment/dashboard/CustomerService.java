package com.assignment.dashboard;

import java.util.List;

import com.demo.utility.Employee;

public interface CustomerService {
 public List<Employee>  getCustomerData(String cqlString ,Employee e);
}
