package com.example.esd.DAO.Implement;

import com.example.esd.Bean.Department;
import com.example.esd.Bean.Employee;
import com.example.esd.DAO.EmployeeDAO;
import com.example.esd.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean addEmployee(Employee empObj) {
        try (Session session = HibernateSessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(empObj);
            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.println("Hibernate Exception");
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        try (Session session = HibernateSessionUtil.getSession()) {
//
            for (final Object d : session.createQuery("from Employee").list()) {
                employeeList.add((Employee) d);
            }

            return employeeList;
        }
      catch (HibernateException exception) {
         System.out.print(exception.getLocalizedMessage());
        return null;
      }
    }

    @Override
    public Employee getEmployeeByID(int id) {
        return null;
    }

    @Override
    public Employee login(Employee emp) {
        try (Session session = HibernateSessionUtil.getSession();){
        String sql1 = "select distinct d.departmentID FROM Department d where d.departmentName = :adminName";
        Query query1 = session.createQuery(sql1);
        query1.setParameter("adminName", "Admin");
        List deptIdList = query1.list();
        int adminId;
        if(deptIdList.size() == 1){
            adminId = ((int) deptIdList.get(0));
            System.out.println(adminId + "admin's Id");
        }else return null;

     // ------------------------------------------------------------------

            String employeeEmail = emp.getEmail();
            System.out.print(employeeEmail);
            String employeePassword = emp.getPassword();
            System.out.print(employeePassword);
            System.out.print(adminId);
           // Integer employeedepartment = emp.getDepartment().getDepartmentID();
          //  System.out.print(employeedepartment);
            List<Object> result = new ArrayList<Object>(
                    session.createQuery(
                                    "FROM Employee e WHERE e.email = :employeeEmail and e.password = :employeePassword   and e.department.departmentID = :adminId"
                            )
                            .setParameter("employeeEmail", employeeEmail)
                            .setParameter("employeePassword", employeePassword)
                            .setParameter("adminId",adminId)
                            .list()
            );

            // If no valid Student found, return null so that login failure is understood
            if (result.size() == 0)
                return null;
            else
                return (Employee) result.get(0);
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }

        return null;
    }
}