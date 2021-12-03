package com.techelevator.dao;

import com.techelevator.model.Course;

import java.util.List;

public interface CourseDao {

    List<Course> getAllCourses();

    Course getCourseById(int courseID);

}
