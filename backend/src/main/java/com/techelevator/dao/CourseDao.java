package com.techelevator.dao;

import com.techelevator.model.Assignment;
import com.techelevator.model.Course;
import com.techelevator.model.Lesson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CourseDao {

    List<Course> getAllCourses();

    Course getCourseById(int courseID);

    Course createCourse(Course newCourse, String username);

    Lesson createLesson(Lesson newLesson, Integer courseID);

    List<Lesson> getLessons(Integer courseID);

    Lesson getLessonForLessonID(Integer lessonID);

    String getTeacher(Integer courseID);

    Course getCourseInfo(Integer courseID);

    Assignment createAssignment(Assignment newAssignment, Integer courseID);

    List<Assignment> getAssignments(Integer courseID);

    Assignment getAssignmentForAssignmentID(Integer assignmentID);

    void registerStudentInCourse(String username, int courseID);

    List<Course> getMyEnrolledCourses(String username);

    List<Course> getMyCoursesToTeach(String username);

    List<Assignment> getMyGradedAssignments(String username);

    Lesson getFullLessonForLessonID(String username, Integer lessonID);

    int getTeacherID(String username);


    int getStudentID(String username);


    boolean checkIfStudentIsRegistered(int courseID, int studentID);


    boolean checkIfTeacherIsCourseTeacher(int courseID, int teacherID);

    String setVideoLessonForID(Integer lessonID, Lesson lesson);

//    public Integer getLessonIDForYoutube(int lessonNumber, int courseID);

}
