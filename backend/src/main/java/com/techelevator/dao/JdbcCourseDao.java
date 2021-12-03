package com.techelevator.dao;

import com.techelevator.model.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCourseDao implements CourseDao {

    @Override
    public List<Course> getAllCourses() {

        List<Course> courseList = new ArrayList<>();

        Course testCourse0 = new Course("Java 101", 0);
        Course testCourse1 = new Course("Python 201", 1);
        Course testCourse2 = new Course("FORTRAN 301", 2);

        courseList.add(testCourse0);
        courseList.add(testCourse1);
        courseList.add(testCourse2);

        return courseList;
    }

    @Override
    public Course getCourseById(int courseID) {

        Course testCourse = new Course("Java 101", courseID);

        return testCourse;

    }
}
