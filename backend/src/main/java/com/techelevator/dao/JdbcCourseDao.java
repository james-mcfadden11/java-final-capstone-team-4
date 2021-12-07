package com.techelevator.dao;

import com.techelevator.model.Course;
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
        List<Course> courses = new ArrayList<Course>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT course_id, course_name, course_description, difficulty_level, course_cost FROM course");
        while(results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }
        return courses;
    }


    private Course mapRowToCourse (SqlRowSet rs) {
        return new Course(rs.getString("course_name"),
                rs.getString("course_description"),
                rs.getInt("difficulty_level"),
                rs.getDouble("course_cost"),
                rs.getInt("course_id"));
    }

    @Override
    public Course getCourseById(int courseID) {
        Course testCourse = new Course("Java 101", courseID);
        return testCourse;
    }


    @Override
    public Course createCourse(Course newCourse) {
        System.out.println("#####################################################################################################################################################################################################################################################################################################################################");
        String sql = "INSERT INTO course (course_name, course_description, difficulty_level, course_cost) VALUES (?, ?, ?, ?);";

        String course_name = newCourse.getTitle();
        String course_description = newCourse.getDescription();
        int difficulty_level = newCourse.getDifficultyLevel();
        double course_cost = newCourse.getCost();

        jdbcTemplate.update(sql, course_name, course_description, difficulty_level, course_cost);

        return newCourse;
    }
}






