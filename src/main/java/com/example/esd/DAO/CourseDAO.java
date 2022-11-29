package com.example.esd.DAO;

import com.example.esd.Bean.Course;

import java.util.List;

public interface CourseDAO {
    boolean addCourse(Course courseObj);

    List<Course> getCourseList();

    boolean updateCourseName(int courseID, String updatedName);

    boolean deleteCourse(int courseID);

    List<Course> getCourses(Integer s_id);
}
