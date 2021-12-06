import axios from 'axios';

export default {

  getCourseData() {
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
  }

}