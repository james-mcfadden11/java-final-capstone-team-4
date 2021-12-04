<template>
  <div>
    <div v-for="course in courses" v-bind:key="course.courseID">
      {{course.courseName}} {{course.courseID}}
    </div>
  </div>
</template>

<script>
import courseService from '../services/CourseService';

export default {
  name: 'course',
  
  data() {
    return {
        courses: []
    }
  },
  
  created() {
    this.retrieveCourses();
  },

  methods: {
    retrieveCourses() {
      courseService
        .getCourseData()
        .then(response => {
          this.$store.commit("SET_COURSES", response.data);
          this.courses = this.$store.state.courses;
        })
        .catch(error => {
          if (error.response) {
            this.errorMsg = `Error retrieving. Response received was ' ${error.response.statusText}'.`;                "'.";
          } else if (error.request) {
            this.errorMsg = "Error retrieving. Server could not be reached.";
          } else {
            this.errorMsg = "Error retreiving. Request could not be created.";
          }
        });
    }
  }
}

</script>

<style>

</style>