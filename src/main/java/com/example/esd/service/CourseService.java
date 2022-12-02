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

//    public List<Course> getCoursesPrereq(Integer c_id){
//        List<Course> coursePreList = courseDAO.getPrereqList(c_id);
//
//        return coursePreList;
//    }

    public  boolean deleteNew(int course_id){
       // CourseDAO courseDAO=new CourseDAOImpl();
        //int course_id= 7;
        boolean res= courseDAO.deleteNew(course_id);

        if(res) return true;
        else return false;

        //Debugging code following ----
//        if(res)
//            System.out.println("--- \tCourse deleted successfully!");
//        else
//            System.out.println("can not delete ");
//
//        System.out.println("-----------------------------------------------------------");
    }


    //Update API
    public  boolean update(Course course ) {
       // CourseDAO courseDAO=new CourseDAOImpl();
        // update project name to AI_ML for project ID =1
       // int course_id= 1;
        boolean res= courseDAO.updateCourse(course);
        if(res)
            System.out.println("--- \tCourse name update successfully! to AI_ML ---");
        else
            System.out.println("can not update ");
        return false;
    }


    public List<Course> getCoursePrerequisites(int courseID){
        List<Course> listt = courseDAO.getCoursePrerequisites(courseID);

        if(listt.isEmpty()) return null;

        else return listt;
    }

}