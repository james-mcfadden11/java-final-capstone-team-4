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

    // Endpoint #1: Get All Courses
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Course> list() {
        return courseDao.getAllCourses();
    }

    // Endpoint #2: Create a Course
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createCourse(@RequestBody Course course, Principal principal) {
        courseDao.createCourse(course, principal.getName());
    }

    // Endpoint #3: Get Assignments for a Specific Course
    @RequestMapping(value = "/{courseID}/assignments", method = RequestMethod.GET)
    public List<Assignment> getAssignments(@PathVariable Integer courseID) {
        return courseDao.getAssignments(courseID);
    }

    //Endpoint #4: Get Lessons for a Specific Course
    @RequestMapping(value = "/{courseID}/lessons", method = RequestMethod.GET)
    public List<Lesson> getLessons(@PathVariable Integer courseID) {
        return courseDao.getLessons(courseID);
    }

    //Endpoint #5: Get Course Info for a Specific Course
    @RequestMapping(value = "/{courseID}/info", method = RequestMethod.GET)
    public Course getCourseInfo(@PathVariable Integer courseID) {
        return courseDao.getCourseInfo(courseID);
    }

    //Endpoint #6: Get Teacher Name for a Specific Course
    @RequestMapping(value = "/{courseID}/teacher", method = RequestMethod.GET)
    public String getTeacher(@PathVariable Integer courseID) {
        return courseDao.getTeacher(courseID);
    }

    //Endpoint #7: Get Assignment Details for a Specific Assignment ID
    //Needs to return an Assignment Object

    //Endpoint #8: Get Lesson Details for a Specific Lesson ID
    //Needs to return an Lesson Object

    //Endpoint #9: Create Assignment for a Specific Course
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{courseID}/assignments", method = RequestMethod.POST)
    public void createAssignment(@RequestBody Assignment assignment, @PathVariable Integer courseID) {
        courseDao.createAssignment(assignment, courseID);
    }

    //Endpoint #10: Create a New Lesson for a Specific Course
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{courseID}/lessons", method = RequestMethod.POST)
    public void createLesson(@RequestBody Lesson lesson, @PathVariable Integer courseID) {
        courseDao.createLesson(lesson, courseID);
    }

    //Endpoint #11: Register Student for Specific Course
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{courseID}/register", method = RequestMethod.POST)
    public void registerStudent(Principal principal, @PathVariable Integer courseID) {
        courseDao.registerStudent(principal.getName(), courseID);
    }

    //Endpoint #12: Return List of Enrolled Courses for Logged In Student
    @RequestMapping(value = "/myEnrolledCourses", method = RequestMethod.GET)
    public List<Course> getMyEnrolledCourses(Principal principal) {
        return courseDao.getMyEnrolledCourses(principal.getName());
    }

    //Endpoint #13: Return List of a Logged In Teacher's Courses
    @RequestMapping(value = "/myCoursesToTeach", method = RequestMethod.GET)
    public List<Course> getMyCoursesToTeach(Principal principal) {
        return courseDao.getMyCoursesToTeach(principal.getName());
    }


    // Leaving this here because it might be a good model for authorization? Get a Specific Course by ID (Dummy)
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable Integer id, Principal principal) {
        Course course = courseDao.getCourseById(id);
        validateAuthorizationToView(principal, course);
        return course;
    }

    //Not currently using, but probably should be?
    private void validateAuthorizationToView(Principal principal, Course course) {
        CourseAuthorization auth = new CourseAuthorization(principal, course);
        if(!auth.isAllowedToView()) {
            throw new AuthorizationException();
        }
    }


}
