package com.example.esd.Controller;


import com.example.esd.Bean.Department;
import com.example.esd.DAO.DepartmentDAO;
import com.example.esd.DAO.Implement.DepartmentDAOImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/department")
public class DepartmentController extends Application {
    DepartmentDAO deptDAO=new DepartmentDAOImpl();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response add_department(Department dept){
        System.out.printf(String.valueOf(dept));

        if(deptDAO.addDepartment(dept)){
            return Response.status(200).entity("Success").build();
        }

        return Response.status(400).entity("Failure while adding department").build();
    }
    


//    @GET
//    @Path("/get")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response get_all_departments(){
//        List<Department> depts = deptDAO.getDepartmentList();
//        System.out.printf("Hello world");
//        return Response.status(200).entity(depts).build();
//    }
}
