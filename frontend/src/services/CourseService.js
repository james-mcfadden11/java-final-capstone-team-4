import axios from 'axios';

export default {

  getCourseData() {
    return axios.get('/courses');
  }, 

  addCourse(newCourse) {
    return axios.post('/courses', newCourse);
  }

}