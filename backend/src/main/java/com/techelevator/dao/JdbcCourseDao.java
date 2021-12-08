package com.techelevator.dao;

import com.techelevator.model.Course;
import com.techelevator.model.Lesson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
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
        SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT course_id, course_name, course_description, difficulty_level, course_cost FROM courses");
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
        String sql = "INSERT INTO courses (course_name, course_description, difficulty_level, course_cost) VALUES (?, ?, ?, ?);";

        String course_name = newCourse.getTitle();
        String course_description = newCourse.getDescription();
        int difficulty_level = newCourse.getDifficultyLevel();
        double course_cost = newCourse.getCost();

        jdbcTemplate.update(sql, course_name, course_description, difficulty_level, course_cost);

        return newCourse;
    }

    @Override
    public Lesson createLesson(Lesson newLesson, Integer courseID) {
        String sql = "INSERT INTO lessons (lesson_number, lesson_name, description, course_id) " +
                "VALUES (?, ?, ?, ?);";

        int lessonNumber = newLesson.getLessonNumber();
        String lessonName = newLesson.getLessonName();
        String description = newLesson.getDescription();

        jdbcTemplate.update(sql, lessonNumber, lessonName, description, courseID);

        return newLesson;
    }

    @Override
    public List<Lesson> getLessons(Integer courseID) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT lesson_number, lesson_name, description FROM lessons WHERE course_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseID);

        while (results.next()) {
            Lesson lesson = mapRowToLesson(results);
            lessons.add(lesson);
        }

        return lessons;
    }

    private Lesson mapRowToLesson(SqlRowSet rs) {
        return new Lesson(rs.getInt("lesson_number"),
                rs.getString("lesson_name"),
                rs.getString("description"));
    }

}






