<template>
  <div>

    <div v-for="course in courses" v-bind:key="course.courseID">
        <router-link v-bind:to="{ name: 'course-details', params: { courseID: course.courseID } }">
          {{course.title}} 
        </router-link>
          {{course.courseID}}
          [Options for teacher to edit or delete course?]
    </div>

    <button v-if="!showAddCourse" v-on:click="showAddCourse = !showAddCourse">Add New Course</button>
      <form v-on:submit.prevent="saveNewCourse" v-if="showAddCourse">
        <h3>Add New Course:</h3>
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
        <input type="number" min="0" v-model="newCourse.cost" />

        <button type="submit">Save</button>
        <button v-on:click.prevent="resetForm">Cancel</button>
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
        newCourse: {},
        showAddCourse: false
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
          this.courses = response.data;
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
    },

    saveNewCourse() {
      courseService
        .addCourse(this.newCourse)
        .then(response => {
          if(response && response.status == 201) {
          this.retrieveCourses();
          this.resetForm();
        }
      })
      .catch(error => {
        // log the error
        if (error.response) {
          this.errorMsg = "Error submitting new course. Response received was '" + error.response.statusText + "'.";
        } else if (error.request) {
          this.errorMsg = "Error submitting new course. Server could not be reached.";
        } else {
          this.errorMsg = "Error submitting new course. Request could not be created.";
        }
        });
    },

    resetForm() {
      this.newCourse = {};
      this.showAddCourse = false;
    }
  }
}

</script>

<style>

</style>