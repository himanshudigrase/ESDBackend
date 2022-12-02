package com.example.esd.Bean;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "faculty",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Course> courseList;
    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "department",nullable = false)
    private Department department;


    public Employee(int employeeID, String firstName, String lastName, String email, String title, String password, List<Course> courseList, Department department) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.password = password;
        this.courseList = courseList;
        this.department = department;
    }

    public Employee(String firstName, String lastName, String email, String title, String password, List<Course> courseList, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.password = password;
        this.courseList = courseList;
        this.department = department;
    }

    public Employee() {
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", photographPath='" + password+ '\'' +
                ", department=" + department +
                '}';
    }
}
