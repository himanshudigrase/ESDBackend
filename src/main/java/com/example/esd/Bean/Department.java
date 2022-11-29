package com.example.esd.Bean;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentID;

    @Column(name = "dept_name", nullable = false, unique = true)
    private String departmentName;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "department",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Employee> employeeList;

    public Department(int departmentID, String departmentName, int capacity, List<Employee> employeeList) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.capacity = capacity;
        this.employeeList = employeeList;
    }

    public Department(String departmentName, int capacity, List<Employee> employeeList) {
        this.departmentName = departmentName;
        this.capacity = capacity;
        this.employeeList = employeeList;
    }

    public Department() {
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID=" + departmentID +
                ", departmentName='" + departmentName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
