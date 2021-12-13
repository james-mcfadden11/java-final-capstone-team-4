import axios from 'axios';

export default {

  // Endpoint #1: Get All Courses
  getCourseData() {
    // this gets a list of all courses
    return axios.get('/courses');
  }, 

  // Endpoint #2: Create a Course
  addCourse(newCourse) {
    return axios.post('/courses', newCourse);
  },

  // Endpoint #3: Get Assignments for a Specific Course
  getAssignments(courseID) {
    return axios.get(`/courses/${courseID}/assignments`);
  },

  //Endpoint #4: Get Lessons for a Specific Course
  getLessons(courseID) {
    return axios.get(`/courses/${courseID}/lessons`);
  },

  //Endpoint #5: Get Course Info for a Specific Course
  getCourseInfo(courseID) {
    // returns a course object
    return axios.get(`/courses/${courseID}/info`);
  },

  //Endpoint #6: Get Teacher Name for a Specific Course
  getTeacherName(courseID) {
    // returns a string
    return axios.get(`/courses/${courseID}/teacher`);
  },

  //Endpoint #7: Get Assignment Details for a Specific Assignment ID
  getAssignmentDetails(courseID, assignmentID) {
    return axios.get(`/courses/${courseID}/assignments/${assignmentID}`);
  },

  //Endpoint #8: Get Lesson Details for a Specific Lesson ID
  getLessonDetails(courseID, lessonID) {
    return axios.get(`/courses/${courseID}/lessons/${lessonID}`);
  },

  //Endpoint #9: Create Assignment for a Specific Course
  addAssignment(newAssignment, courseID) {
    return axios.post(`courses/${courseID}/assignments`, newAssignment);
  },

  //Endpoint #10: Create a New Lesson for a Specific Course
  addLesson(newLesson, courseID) {
    return axios.post(`courses/${courseID}/lessons`, newLesson);
  },

  //Endpoint #11: Register Student for Specific Course
  registerStudentForCourse(courseID) {
    // will need to use Principal on back-end to access studentID
    return axios.post(`courses/${courseID}/register`);
  },

  //Endpoint #12: Return List of Enrolled Courses for Logged In Student
  //@RequestMapping(value = "/myEnrolledCourses", method = RequestMethod.GET)
  getMyEnrolledCourses() {
    return axios.get(`courses/myEnrolledCourses`)
  },
  //Endpoint #13: Return List of a Logged In Teacher's Courses
  //@RequestMapping(value = "/myCoursesToTeach", method = RequestMethod.GET)
  getMyCoursesToTeach() {
    return axios.get(`courses/myCoursesToTeach`)
  },

  // [removed endpoints 14 & 15]
  
  //Endpoint #16: Return List of Graded Assignments for Logged In Student
  //@RequestMapping(value = "/myGradedAssignments", method = RequestMethod.GET)
  getMyGradedAssignments() {
    return axios.get(`courses/myGradedAssignments`)
  }
}