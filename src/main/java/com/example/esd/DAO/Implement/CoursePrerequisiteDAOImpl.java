package com.example.esd.DAO.Implement;

import com.example.esd.Bean.Course;
import com.example.esd.Bean.CoursePrerequisite;
import com.example.esd.DAO.CoursePrerequisiteDAO;
import com.example.esd.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CoursePrerequisiteDAOImpl implements CoursePrerequisiteDAO {
    @Override
    public boolean addCoursePrerequisite(CoursePrerequisite preObj) {
        try(Session session= HibernateSessionUtil.getSession()) {
            Transaction transaction=session.beginTransaction();
            session.persist(preObj);
            transaction.commit();
            return true;
        }
        catch (HibernateException exception){
            System.out.println("Hibernate Exception");
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }
//    @Override
//    public List<CoursePrerequisite> getCoursePrerequisites(Integer course_id) {
//        try (Session session = HibernateSessionUtil.getSession()) {
//
//            CoursePrerequisite course=session.get(CoursePrerequisite.class, course_id);
//            course.getPreRequisiteList().forEach(course1 -> {course1.getPreRequisiteList().clear();});
//            List<CoursePrerequisite> coursePreList=new ArrayList<>(course.getPreRequisiteList());
//            return coursePreList;
//        } catch (HibernateException exception) {
//            System.out.print(exception.getLocalizedMessage());
//        }
//        return null;
//    }
}
