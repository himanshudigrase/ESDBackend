package com.example.esd.DAO.Implement;

import com.example.esd.Bean.Course;
import com.example.esd.DAO.CourseDAO;
import com.example.esd.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean addCourse(Course courseObj) {
        try(Session session= HibernateSessionUtil.getSession()) {
            Transaction transaction=session.beginTransaction();
            session.persist(courseObj);
            transaction.commit();
            return true;
        }
        catch (HibernateException exception){
            System.out.println("Hibernate Exception");
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Course> getCourseList() {
        try(Session session= HibernateSessionUtil.getSession()) {

            List<Course> courseList=new ArrayList<>();

            for (final Object c:session.createQuery("from Course").list()){
                courseList.add((Course) c);
            }

            return courseList;
        }
        catch (HibernateException exception){
            System.out.println("Hibernate Exception");
            System.out.println(exception.getLocalizedMessage());
            return null;
        }    }

    @Override
    public boolean updateCourseName(int courseID, String updatedName) {
        try(Session session= HibernateSessionUtil.getSession()) {
            Transaction transaction=session.beginTransaction();

            Query q=session.createQuery("from Course where courseID=:ID");
            q.setParameter("ID",courseID);

            Course result=(Course)q.list().get(0);
            result.setName(updatedName);
            session.update(result);
            transaction.commit();
            return true;
        }
        catch (HibernateException exception){
            System.out.println("Hibernate Exception");
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCourse(int courseID) {
        try(Session session= HibernateSessionUtil.getSession()) {
            Transaction transaction=session.beginTransaction();

            Query q=session.createQuery("from Course where courseID=:ID");
            q.setParameter("ID",courseID);

            Course result=(Course)q.list().get(0);

            session.remove(result);

            transaction.commit();


            return true;
        }
        catch (HibernateException exception){
            System.out.println("Hibernate Exception");
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Course> getCourses(Integer s_id) {
        List<Course> courseList = new ArrayList<>();

        try(Session session = HibernateSessionUtil.getSession()){
            for (
                    final Object course : session
                    .createQuery("FROM Course WHERE faculty.employeeID=:s_id ")
                    .setParameter("s_id", s_id).list()
            )
                courseList.add((Course) course);
        }catch (HibernateException exception) {
            System.out.println(exception.getLocalizedMessage());
        }
        return courseList;
    }
}
