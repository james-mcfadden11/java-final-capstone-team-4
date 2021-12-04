import axios from 'axios';

export default {

  getCourseData() {
    return axios.get('/courses');
  }

}