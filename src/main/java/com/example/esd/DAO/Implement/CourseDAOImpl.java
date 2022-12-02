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
        }
    }

//



    // to get list of courses under a certain faculty/employee
    @Override
    public List<Course> getCourses(Integer s_id) {
        List<Course> courseList = new ArrayList<>();
        System.out.print("Correct");
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



    // -------------New delete method -----------------
    @Override
    public boolean deleteNew(int courseID) {
        try (Session session = HibernateSessionUtil.getSession()) {
            Transaction t = session.beginTransaction();
            //first fetch object and then delete it
            Course courseObj = session.get(Course.class, courseID);
            List<Course> list = new ArrayList<>(courseObj.getParentCourseList());
            System.out.println(list);

            if (list.size() != 0) {
                for (Course course : list) {
                    course.getPreRequisiteList().clear();
                    session.update(course);
                }
            }

            session.delete(courseObj);
            t.commit();
            System.out.println("Course Deleted having ID - " + courseID);
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }



//


  // Update course
    @Override
    public boolean updateCourse(Course course) {
        try (Session session = HibernateSessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();

            int courseID = course.getCourseID();

            Query q = session.createQuery("from Course where courseID=:ID");
            q.setParameter("ID", courseID);

            Course result = (Course) q.list().get(0);
            if (q.list().isEmpty()) {
                //add course if it doesn't exist
                session.save(course);
            } else {
                List<Course> list = new ArrayList<>(result.getPreRequisiteList());
                System.out.println(list);

                if (list.size() != 0) {

                    //Removing parent entry of pre course to be deleted
                    for (Course courseObj : list) {
                        //checking if pre course has parent entry
                        if (!courseObj.getParentCourseList().isEmpty()) {
                            courseObj.getParentCourseList().remove(result);
                            session.update(courseObj);
                        }

                    }
                    //clearing old pre list
                    result.getPreRequisiteList().clear();
                }

                //getting list for course ids
                List<Course> list1 = new ArrayList<>(course.getPreRequisiteList());

                //Updating Pre List
                for (Course courseObj : list1) {
                    //getting course to be added
                    Course c = session.get(Course.class, courseObj.getCourseID());
                    //adding new pre course
                    result.getPreRequisiteList().add(c);
                    //adding parent entry for new pre course
                    c.getParentCourseList().add(result);
                    session.update(c);
                }

                result.setYear(course.getYear());
                result.setTerm(course.getTerm());
                result.setDescription(course.getDescription());
                result.setCredits(course.getCredits());
                result.setCapacity(course.getCapacity());
                result.setName(course.getName());
                session.update(result);
            }

            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.println("Hibernate Exception");
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }


//    public List<Course> getCoursePrerequisites(Integer course_id) {
//        return null;
//    }

    // to get course prerequisites
    @Override
    public List<Course> getCoursePrerequisites(int courseID) {
        try (Session session = HibernateSessionUtil.getSession()) {

            Course course=session.get(Course.class, courseID);
            course.getPreRequisiteList().forEach(course1 -> {course1.getPreRequisiteList().clear();});
            List<Course> coursePreList=new ArrayList<>(course.getPreRequisiteList());
            return coursePreList;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }


//    @Override
//    public boolean updateCourseName(int courseID, String updatedName) {
//        try(Session session= HibernateSessionUtil.getSession()) {
//            Transaction transaction=session.beginTransaction();
//
//            Query q=session.createQuery("from Course where courseID=:ID");
//            q.setParameter("ID",courseID);
//
//            Course result=(Course)q.list().get(0);
//            result.setName(updatedName);
//            session.update(result);
//            transaction.commit();
//            return true;
//        }
//        catch (HibernateException exception){
//            System.out.println("Hibernate Exception");
//            System.out.println(exception.getLocalizedMessage());
//            return false;
//        }
//    }

//    @Override
//    public List<Course> getPrereqList(int courseID) {
//        try (Session session = HibernateSessionUtil.getSession()) {
//            Transaction t = session.beginTransaction();
//            //first fetch object and then delete it
//            Course courseObj = session.get(Course.class, courseID);
//            List<Course> list = new ArrayList<>(courseObj.getParentCourseList());
//            System.out.println(list);
//
//            if (list.size() != 0) {
//                for (Course course : list) {
//                    course.getPreRequisiteList();
//                    session.update(course);
//                }
//            }
//
//         //   session.delete(courseObj);
//            t.commit();
//           // System.out.println("Course Deleted having ID - " + courseID);
//            return null;
//        } catch (HibernateException exception) {
//            System.out.print(exception.getLocalizedMessage());
//            return null;
//        }
//    }
}
