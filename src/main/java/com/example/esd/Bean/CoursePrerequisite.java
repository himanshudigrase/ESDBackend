package com.example.esd.Bean;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="course_pre_requisite")
public class CoursePrerequisite implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "p_course_id",nullable = false)
    private Course courseForID;



    public CoursePrerequisite(int ID, Course courseForID) {
        this.ID = ID;
        this.courseForID = courseForID;
    }

    public CoursePrerequisite(Course courseForID) {
        this.courseForID = courseForID;
    }

    public CoursePrerequisite() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Course getCourseForID() {
        return courseForID;
    }

    public void setCourseForID(Course courseForID) {
        this.courseForID = courseForID;
    }


}
