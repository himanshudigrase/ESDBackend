package com.example.esd.DAO;

import com.example.esd.Bean.Employee;

import java.util.List;

public interface EmployeeDAO {
    boolean addEmployee(Employee empObj);
    
    List<Employee> getEmployeeList();

    Employee getEmployeeByID(int id);

    Employee login(Employee employee);
}
