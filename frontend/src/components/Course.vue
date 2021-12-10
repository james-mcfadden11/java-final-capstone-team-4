<template>
  <div>
    <br>
    <div class="card-details" v-for="course in courses" v-bind:key="course.courseID">
        <div class="course-title">
          Course title:
          <router-link v-bind:to="{ name: 'course-details', params: { courseID: course.courseID } }">
            {{course.title}} 
            <br>
          </router-link>
        </div>
          Course ID: {{course.courseID}}
          <br>
          Course Description: {{course.description}}
          <br>
          Difficulty Level: {{course.difficultyLevel}}
          <br>
          Cost: ${{course.cost}}

          <br>

          <!-- would be nice to gray this out if student is already registered for that particular course -->
          <button v-on:click="registerStudentForCourse(course.courseID)" v-show="!isTeacher">
            Register
          </button>

          <br>
          <br>

    </div>

    <button class="add-course-btn" v-if="!showAddCourse" v-on:click="showAddCourse = !showAddCourse" v-show="isTeacher">Add New Course</button>
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

  props: ['isTeacher'],
  
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
    },

    registerStudentForCourse(courseID) {
      courseService
          .registerStudentForCourse(courseID)
          .then(response => {
            if(response && response.status == 201) {
            // this.retrieveCourses();
            // this.resetForm();
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
    }
  }
}

</script>

<style>
.card-details {
background-image: linear-gradient(to bottom right, rgba(0, 0, 0, 0.315), rgb(68, 68, 68));
border-style: solid;
border-width: 1px;
border-radius: 8px;
box-shadow: 0px 0px 100px rgba(8, 8, 8, 0.144);
margin-top: 15px;
padding-top: 1.5%;
padding-bottom: 0%;
padding-left: 10px;
padding-bottom: auto;
font-family: sans-serif;
}

.card-details:hover {
font-size: 101%;
}


.course-title {
font-weight: bold;

}

.add-course-btn {
margin: 15px;
}


</style>