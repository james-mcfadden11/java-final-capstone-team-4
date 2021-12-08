package com.techelevator.dao;

import com.techelevator.model.Course;
import com.techelevator.model.Lesson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CourseDao {

    List<Course> getAllCourses();

    Course getCourseById(int courseID);

    Course createCourse(Course newCourse);

    Lesson createLesson(Lesson newLesson, Integer courseID);

    List<Lesson> getLessons(Integer courseID);

}
