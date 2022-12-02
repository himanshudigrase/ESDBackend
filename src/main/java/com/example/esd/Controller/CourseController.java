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
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getPrereq(@QueryParam("courseIdPre") int courseId) {
//        List<Course> courseList = courseService.getCoursesPrereq(courseId);
//        return Response.ok().entity(courseList).build();
//    }


    //Delete API
    @DELETE
   // @Path("{courseId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteCourse(@QueryParam("courseId") int id){
        //System.out.printf(String.valueOf(courseObj));
        System.out.print("Coming till here");
        if(courseService.deleteNew(id)){
            return Response.status(200).entity("Success").build();
        }

        return Response.status(400).entity("Failure while deleting").build();
    }

    @POST
    //@Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Course courseObj){
        //System.out.printf(String.valueOf(courseObj));

        if(courseService.update(courseObj)){
            return Response.status(200).entity("Success").build();
        }

        return Response.status(400).entity("Failure while updating").build();
    }

    @GET
    @Path("/get_pre/{c_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response get_prerequisites(@PathParam("c_id") int id) {//throws JsonProcessingException {
        System.out.println(id);

        List<Course> preRequisites= courseService.getCoursePrerequisites(id);

        System.out.println(preRequisites);

        return Response.status(200).entity(preRequisites).build();
    }


}
