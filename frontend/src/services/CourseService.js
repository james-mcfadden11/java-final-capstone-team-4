import axios from 'axios';

export default {

  getCourseData() {
    // this gets a list of all courses
    return axios.get('/courses');
  }, 

  addCourse(newCourse) {
    return axios.post('/courses', newCourse);
  },

  getAssignments(courseID) {
    return axios.get(`/courses/${courseID}/assignments`);
  },

  getLessons(courseID) {
    return axios.get(`/courses/${courseID}/lessons`);
  },

  getCourseInfo(courseID) {
    // returns a course object
    return axios.get(`/courses/${courseID}/info`);
  },

  getTeacherName(courseID) {
    // returns a string
    return axios.get(`/courses/${courseID}/teacher`);
  },

  getAssignmentDetails(courseID, assignmentID) {
    return axios.get(`/courses/${courseID}/assignments/${assignmentID}`);
  },

  getLessonDetails(courseID, lessonID) {
    return axios.get(`/courses/${courseID}/lessons/${lessonID}`);
  },

  addAssignment(newAssignment, courseID) {
    return axios.post(`courses/${courseID}/assignments`, newAssignment);
  },

  addLesson(newLesson, courseID) {
    return axios.post(`courses/${courseID}/lessons`, newLesson);
  },

  registerStudentForCourse(courseID) {
    // will need to use Principal on back-end to access studentID
    return axios.post(`courses/${courseID}/register`);
  }

}