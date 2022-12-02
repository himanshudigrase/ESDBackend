package com.example.esd.Controller;
import com.example.esd.Bean.Employee;
import com.example.esd.DAO.Implement.EmployeeDAOImpl;
import com.example.esd.DAO.EmployeeDAO;

import com.example.esd.service.EmployeeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/employee")
public class EmployeeController  //extends HelloApplication
{
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    EmployeeService employeeService = new EmployeeService();


    @GET
    @Path("/get_all")

    @Produces(MediaType.APPLICATION_JSON)
    public Response get_all_employee() {
        List<Employee> emps = this.employeeDAO.getEmployeeList();

        return Response.status(200).entity(emps).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Employee emp) {
        Employee loggedInEmployee = employeeService.login(emp);

        if (loggedInEmployee == null)
            return Response.status(401).build();
        else
            return Response.ok().entity(loggedInEmployee).build();
    }

    @GET
    @Path("/get/{e_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response get_employee(@PathParam("e_id") int id) {
        System.out.println(id);

        Employee emp = this.employeeDAO.getEmployeeByID(id);
        emp.setDepartment(emp.getDepartment());
        System.out.println(emp);

        return Response.status(200).entity(emp).build();
    }
}
