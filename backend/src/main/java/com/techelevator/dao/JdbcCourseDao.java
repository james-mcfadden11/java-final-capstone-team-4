package com.techelevator.dao;

import com.techelevator.model.Assignment;
import com.techelevator.model.Course;
import com.techelevator.model.Lesson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
    public List<Course> getMyEnrolledCourses(String username) {
        List<Course> myCourseList = new ArrayList<>();
        int studentID = getStudentID(username);
        SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT course_id, course_name, course_description, difficulty_level, course_cost FROM courses JOIN student_courses USING (course_id) WHERE student_id = ?", studentID);
        while(results.next()) {
            Course course = mapRowToCourse(results);
            myCourseList.add(course);
        }
        return myCourseList;
    }

    @Override
    public List<Course> getMyCoursesToTeach(String username) {
        List<Course> myCourseList = new ArrayList<>();
        int teacherID = getTeacherID(username);
        SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT course_id, course_name, course_description, difficulty_level, course_cost FROM courses JOIN teacher_courses USING (course_id) WHERE teacher_id = ?", teacherID);
        while(results.next()) {
            Course course = mapRowToCourse(results);
            myCourseList.add(course);
        }
        return myCourseList;
    }

    @Override
    public Course createCourse(Course newCourse, String username) {

        String sql = "INSERT INTO courses (course_name, course_description, difficulty_level, course_cost) VALUES (?, ?, ?, ?);";

        String course_name = newCourse.getTitle();
        String course_description = newCourse.getDescription();
        int difficulty_level = newCourse.getDifficultyLevel();
        double course_cost = newCourse.getCost();

        //QueryForRowSet ... RETURNING course_id... capture row set that comes back, advance cursor, grab that number

        jdbcTemplate.update(sql, course_name, course_description, difficulty_level, course_cost);
        String sql3 = "SELECT MAX(course_id) FROM courses;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql3);
        int courseID = 0;
        if (results.next()) {
         courseID = results.getInt("max");

        }
        int teacherID = getTeacherID(username);

        createTeacherCourse(teacherID, courseID);
        newCourse.setCourseID(courseID);
        return newCourse;
    }

    public int getTeacherID(String username) {
        String sql = "SELECT teacher_id FROM teachers JOIN users USING( user_id) WHERE username = ?;";
        int teacherID = 0;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if (results.next()) {
            teacherID = results.getInt("teacher_id");

        }

        return teacherID;

    }

    public void registerStudent(String username, int courseID) {
        int studentID = getStudentID(username);

        String sql = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?);";

        jdbcTemplate.update(sql, studentID, courseID);


    }

    public int getStudentID(String username) {
        String sql = "SELECT student_id FROM students JOIN users USING( user_id) WHERE username = ?;";
        int studentID = 0;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if (results.next()) {
            studentID = results.getInt("student_id");
        }
        return studentID;

    }

    public void createTeacherCourse(int teacherID, int courseID) {
        String sql2 = "INSERT INTO teacher_courses (teacher_id, course_id)" +
                " VALUES(?, ?);";
        jdbcTemplate.update(sql2, teacherID, courseID);
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

        String sql3 = "SELECT MAX(assignment_id) FROM assignments;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql3);
        int assignmentID = 0;
        if (results.next()) {
            assignmentID = results.getInt("max");

        }

        String sql4 = "SELECT student_id FROM student_courses WHERE course_id = ?;";

        SqlRowSet students = jdbcTemplate.queryForRowSet(sql4, courseID);
        int studentID = 0;
        while (students.next()) {
            studentID = students.getInt("student_id");
            String sql2 = "INSERT INTO student_assignments (student_id, homework_id, possible_points, is_submitted, is_graded) VALUES(?, ?, ?, false, false);";


            jdbcTemplate.update(sql2, studentID, assignmentID, possiblePoints);
        }

//        String sql2 = "INSERT INTO student_assignments (student_id, homework_id, possible_points, is_submitted, is_graded) VALUES(?, ?, ?, false, false);";
//
//
//        jdbcTemplate.update(sql2, assignmentID, possiblePoints, courseID);


        return newAssignment;
    }

    @Override
    public List<Lesson> getLessons(Integer courseID) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT lesson_number, lesson_id, lesson_name, description FROM lessons WHERE course_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseID);

        while (results.next()) {
            Lesson lesson = mapRowToLesson(results);
            lessons.add(lesson);
        }

        return lessons;
    }


    @Override
    public Lesson getLessonForLessonID(Integer lessonID) {
        Lesson lesson = new Lesson();
        String sql = "SELECT lesson_number, lesson_id, lesson_name, description FROM lessons WHERE lesson_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, lessonID);

        while (results.next()) {
            lesson = mapRowToLesson(results);
        }
        return lesson;
    }

        @Override
    public Assignment getAssignmentForAssignmentID(Integer assignmentID) {
        Assignment assignment = new Assignment();
        String sql = "SELECT course_id, assignment_id, assignment_number, assignment_name, description, assignments.possible_points, due_date, submission, student_grade, submission_date_time, is_submitted, is_graded, teacher_feedback FROM assignments Join student_assignments on assignment_id = homework_id WHERE assignment_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, assignmentID);

        if (results.next()) {
            assignment = mapRowToAssignment(results);
        }
        return assignment;
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
        return new Lesson(
                rs.getInt("lesson_id"),
                rs.getInt("lesson_number"),
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
        assignment.setStudentGrade(rs.getDouble("student_grade"));
        assignment.setSubmission(rs.getString("submission"));
        assignment.setTeacherFeedback(rs.getString("teacher_feedback"));
        assignment.setGraded(rs.getBoolean("is_graded"));
        assignment.setSubmitted(rs.getBoolean("is_submitted"));
        if(rs.getDate("submission_date_time") != null) {
            assignment.setSubmittedDateTime(rs.getDate("submission_date_time").toLocalDate());
        } else {
            assignment.setSubmittedDateTime(rs.getDate("0000-00-00").toLocalDate());
        }

        if(rs.getDate("due_date") != null) {
            assignment.setDueDate(rs.getDate("due_date").toLocalDate());
        }
        return assignment;
    }

    //#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$%#$#%#$%#
    //FRANK'S NEW CODE FROM December 12th Starts Here. Please no merge conflicts please no merge conflicts.
    @Override
    public List<Assignment> getMyGradedAssignments(String username) {
        int studentID = getStudentID(username);
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT course_id, assignment_id, assignment_number, assignment_name, description, " +
                "assignments.possible_points, due_date, student_id, homework_id, student_grade, submission, " +
                "teacher_feedback, submission_date_time, is_submitted, is_graded FROM student_assignments " +
                "JOIN assignments ON (assignment_id = homework_id) WHERE student_id = ? " +
                "AND is_graded = true";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, studentID);

        while (results.next()) {
            Assignment assignment = mapRowToAssignment(results);
            assignments.add(assignment);
        }

        return assignments;
    }


}






