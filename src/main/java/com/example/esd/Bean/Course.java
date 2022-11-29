package com.example.esd.Bean;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;

    @Column(name = "course_code", nullable = false, unique = true)
    private int courseCode;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "term", nullable = false)
    private int term;

    @Column(name = "credits", nullable = false)
    private int credits;

    @Column(name = "capacity", nullable = false)
    private int capacity;
    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "course_faculty_id",nullable = false)
    private Employee faculty;

    @OneToMany(mappedBy = "courseForID",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<CoursePrerequisite> coursePrerequisiteListForID;



    public Course(int courseID, int courseCode, String name, String description, int year, int term, int credits, int capacity, Employee faculty, List<CoursePrerequisite> coursePrerequisiteListForID) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.name = name;
        this.description = description;
        this.year = year;
        this.term = term;
        this.credits = credits;
        this.capacity = capacity;
        this.faculty = faculty;
    }

    public Course(int courseCode, String name, String description, int year, int term, int credits, int capacity, Employee faculty) {
        this.courseCode = courseCode;
        this.name = name;
        this.description = description;
        this.year = year;
        this.term = term;
        this.credits = credits;
        this.capacity = capacity;
        this.faculty = faculty;
    }

    public Course() {
    }

//    public int getCourseID() {
//        return courseID;
//    }
//
//    public void setCourseID(int courseID) {
//        this.courseID = courseID;
//    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Employee getFaculty() {
        return faculty;
    }

    public void setFaculty(Employee faculty) {
        this.faculty = faculty;
    }





    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseCode=" + courseCode +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", term=" + term +
                ", credits=" + credits +
                ", capacity=" + capacity +
                ", faculty=" + faculty +
                '}';
    }
}
