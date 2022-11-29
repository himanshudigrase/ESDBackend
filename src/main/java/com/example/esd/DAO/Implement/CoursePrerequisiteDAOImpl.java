package com.example.esd.DAO.Implement;

import com.example.esd.Bean.CoursePrerequisite;
import com.example.esd.DAO.CoursePrerequisiteDAO;
import com.example.esd.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
