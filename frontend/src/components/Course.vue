<template>
  <div>
    <div v-for="course in courses" v-bind:key="course.courseID">
      {{course.courseName}} {{course.courseID}}
    </div>

    <h3>Add New Course:</h3>
      <form v-on:submit.prevent="saveNewCourse">
        Course Name:
        <input type="text" v-model="newCourse.title" />
        Course Description:
        <input type="text" v-model="newCourse.description" />
        Difficulty Level:
        <select v-model="newCourse.difficultyLevel">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
        Cost:
        <input type="number" v-model="newCourse.cost" />

        <button type="submit">Save</button>
        <button>Cancel</button>
      </form>
  </div>
</template>

<script>
import courseService from '../services/CourseService';

export default {
  name: 'course',
  
  data() {
    return {
        courses: [],
        newCourse: {}
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