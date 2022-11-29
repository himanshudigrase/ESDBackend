package com.example.esd.service;


import com.example.esd.Bean.Course;
import com.example.esd.DAO.Implement.CourseDAOImpl;

import java.util.List;

public class CourseService {
    CourseDAOImpl courseDAO = new CourseDAOImpl();

    public List<Course> getCourses(Integer s_id){
        List<Course> courseList = courseDAO.getCourses(s_id);

        // Removing employee attribute from returned list to kill cyclic references
        for (Course course: courseList)
            course.setFaculty(null);

        return courseList;
    }
//   // public Boolean payBill(Integer billId) {
//        return billDAO.payBill(billId);
//    }
}