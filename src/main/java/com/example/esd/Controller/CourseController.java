package com.example.esd.Controller;

import com.example.esd.Bean.Course;
import com.example.esd.DAO.CourseDAO;
import com.example.esd.DAO.Implement.CourseDAOImpl;
import com.example.esd.service.CourseService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/course")
public class CourseController extends Application {
    CourseDAO courseDAO=new CourseDAOImpl();
    CourseService courseService = new CourseService();
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response add_course(Course courseObj){
        System.out.printf(String.valueOf(courseObj));

        if(courseDAO.addCourse(courseObj)){
            return Response.status(200).entity("Success").build();
        }

        return Response.status(400).entity("Failure while adding department").build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses(@QueryParam("studentId") int employeeId) {
        List<Course> courseList = courseService.getCourses(employeeId);
        return Response.ok().entity(courseList).build();
    }
}
