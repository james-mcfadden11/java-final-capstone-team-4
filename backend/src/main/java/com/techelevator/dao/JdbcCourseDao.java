package com.techelevator.dao;

import com.techelevator.model.Assignment;
import com.techelevator.model.Course;
import com.techelevator.model.Lesson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
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
    public Assignment createAssignment(Assignment newAssignment, Integer courseID) {
        String sql = "INSERT INTO assignments (course_id, assignment_number, assignment_name, description, possible_points, due_date) " +
                "VALUES (?, ?, ?, ?, ?, ?);";


        int assignmentNumber = newAssignment.getAssignmentNumber();
        String assignmentName = newAssignment.getAssignmentName();
        String description = newAssignment.getDescription();
        int possiblePoints = newAssignment.getPossiblePoints();
        LocalDate dueDate = newAssignment.getDueDate();

        jdbcTemplate.update(sql, courseID, assignmentNumber, assignmentName, description, possiblePoints, dueDate);

        return newAssignment;
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

    @Override
    public String getTeacher(Integer courseID) {

        String sql = "SELECT first_name, last_name FROM teachers JOIN teacher_courses USING (teacher_id) WHERE course_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseID);
        String firstName = "";
        String lastName = "";
        while (results.next()) {
            firstName = results.getString("first_name");
            lastName = results.getString("last_name");
        }
        return firstName + " " + lastName;

    }

    @Override
    public List<Assignment> getAssignments(Integer courseID) {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT course_id, assignment_id, assignment_number, assignment_name, description, possible_points, due_date FROM assignments WHERE course_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseID);

        while (results.next()) {
            Assignment assignment = mapRowToAssignment(results);
            assignments.add(assignment);
        }

        return assignments;
    }

    @Override
    public Course getCourseInfo(Integer courseID) {
        Course course = new Course();
        String sql = "SELECT course_id, course_name, course_description, difficulty_level, course_cost FROM courses WHERE course_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseID);

        while(results.next()) {
            course = mapRowToCourse(results);
        }
        return course;
    }

    private Lesson mapRowToLesson(SqlRowSet rs) {
        return new Lesson(rs.getInt("lesson_number"),
                rs.getString("lesson_name"),
                rs.getString("description"));
    }


    private Assignment mapRowToAssignment(SqlRowSet rs) {
        Assignment assignment = new Assignment();
        assignment.setCourseID(rs.getInt("course_id"));
        assignment.setAssignmentID(rs.getInt("assignment_id"));
        assignment.setAssignmentNumber(rs.getInt("assignment_number"));
        assignment.setAssignmentName(rs.getString("assignment_name"));
        assignment.setDescription(rs.getString("description"));
        assignment.setPossiblePoints(rs.getInt("possible_points"));

        if(rs.getDate("due_date") != null) {
            assignment.setDueDate(rs.getDate("due_date").toLocalDate());
        }
        return assignment;
    }
}






