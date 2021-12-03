package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.model.AuthorizationException;
import com.techelevator.model.Course;
import com.techelevator.model.CourseAuthorization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/courses")
//@PreAuthorize("isAuthenticated()")
public class CourseController {

    private final CourseDao courseDao;

    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Course> list() {
        return courseDao.getAllCourses();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable Integer id, Principal principal) {
        Course course = courseDao.getCourseById(id);
        validateAuthorizationToView(principal, course);
        return course;
    }

    private void validateAuthorizationToView(Principal principal, Course course) {
        CourseAuthorization auth = new CourseAuthorization(principal, course);
        if(!auth.isAllowedToView()) {
            throw new AuthorizationException();
        }
    }

}
