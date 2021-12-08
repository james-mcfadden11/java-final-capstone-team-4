package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/courses")
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class CourseController {

    private final CourseDao courseDao;

    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Course> list() {
        return courseDao.getAllCourses();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createCourse(@RequestBody Course course) {
        courseDao.createCourse(course);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable Integer id, Principal principal) {
        Course course = courseDao.getCourseById(id);
        validateAuthorizationToView(principal, course);
        return course;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{courseID}/lessons", method = RequestMethod.POST)
    public void createLesson(@RequestBody Lesson lesson, @PathVariable Integer courseID) {
        courseDao.createLesson(lesson, courseID);
    }

    @RequestMapping(value = "/{courseID}/lessons", method = RequestMethod.GET)
    public List<Lesson> getLessons(@PathVariable Integer courseID) {
        return courseDao.getLessons(courseID);
    }

    // Assignment Controls
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{courseID}/assignments", method = RequestMethod.POST)
    public void createAssignment(@RequestBody Assignment assignment, @PathVariable Integer courseID) {
        courseDao.createAssignment(assignment, courseID);
    }

    @RequestMapping(value = "/{courseID}/assignments", method = RequestMethod.GET)
    public void getAssignments(@PathVariable Integer courseID) {
        courseDao.getAssignments(courseID);
    }


    private void validateAuthorizationToView(Principal principal, Course course) {
        CourseAuthorization auth = new CourseAuthorization(principal, course);
        if(!auth.isAllowedToView()) {
            throw new AuthorizationException();
        }
    }

}
