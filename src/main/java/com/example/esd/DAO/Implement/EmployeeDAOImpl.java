package com.example.esd.DAO.Implement;

import com.example.esd.Bean.Employee;
import com.example.esd.DAO.EmployeeDAO;
import com.example.esd.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            String employeeEmail = emp.getEmail();
            System.out.print(employeeEmail);
            String employeePassword = emp.getPassword();
            System.out.print(employeePassword);
           // Integer departmentId = emp.getDepartmentId();
           // System.out.print(departmentId);
            List<Object> result = new ArrayList<Object>(
                    session.createQuery(
                                    "FROM Employee WHERE email = :employeeEmail and password = :employeePassword "//andepartmentId = :departmentId"
                            )
                            .setParameter("employeeEmail", employeeEmail)
                            .setParameter("employeePassword", employeePassword)
             //               .setParameter("departmentId",departmentId)
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