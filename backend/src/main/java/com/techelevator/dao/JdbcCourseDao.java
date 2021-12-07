package com.techelevator.dao;

import com.techelevator.model.Course;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCourseDao implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

//    // Added by Will to Get All Courses from SQL Database
//    @Override
//    public List<Course> getMyCourses() {
//        List<Course> courses = new ArrayList<Course>();
//        SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT course_id, course_name, course_description, difficulty_level, course_cost, FROM courses");
//        while(results.next()) {
//            Course course = mapRowToCourse(results);
//            courses.add(course);
//        }
//        return courses;
//    }

    @Override
    public Course getCourseById(int courseID) {

        Course testCourse = new Course("Java 101", courseID);

        return testCourse;

    }

    // Helper method added by Will to map row to course
//    private Course mapRowToCourse (SqlRowSet rs) {
//        return new Course(rs.getInt("course_id"),
//                rs.getString("course_name"),
//                rs.getString("course_description"),
//                rs.getInt("difficulty_level"),
//                rs.getBigDecimal("course_cost"));
    }
}
