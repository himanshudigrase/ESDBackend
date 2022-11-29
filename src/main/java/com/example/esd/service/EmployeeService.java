package com.example.esd.service;

import com.example.esd.Bean.Employee;
import com.example.esd.DAO.Implement.EmployeeDAOImpl;

public class EmployeeService {
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public Employee login(Employee employee){
        Employee loggedInEmployee= employeeDAO.login(employee);

        // If no login happens, then return null
        if (loggedInEmployee == null)
            return null;

//        // Setting billList to null to avoid cyclic dependency issues
        loggedInEmployee.setCourseList(null);

        return loggedInEmployee;
    }

}