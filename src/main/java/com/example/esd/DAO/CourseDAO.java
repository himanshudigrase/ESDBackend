package com.example.esd.DAO;

import com.example.esd.Bean.Course;

import java.util.List;

public interface CourseDAO {
    boolean addCourse(Course courseObj);

    List<Course> getCourseList();

    List<Course> getCourses(Integer s_id);

    boolean deleteNew(int courseID);


    // Update course
    boolean updateCourse(Course course);

//     List<Course> getCoursePrerequisites(Integer course_id);

    //    public List<Course> getCoursePrerequisites(Integer course_id) {
//        return null;
//    }

    // to get course prerequisites
    List<Course> getCoursePrerequisites(int courseID);
}
