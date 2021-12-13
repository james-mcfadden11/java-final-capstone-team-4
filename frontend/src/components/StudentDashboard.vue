<template>
  <div>
    <h2>Student Dashboard</h2>

    <br>

    <h3>My courses:</h3>
    <div v-for="course in coursesEnrolledIn" v-bind:key="course.courseNumber"></div>
      <div class="course-title">
          <router-link v-bind:to="{ name: 'course-details', params: { courseID: course.courseID } }">
            {{course.title}} 
            <br>
          </router-link>
      </div>

    <br>

    <!-- <h3 v-for="assignment in upcomingAssignments" v-bind:key="assignment.assignmentID">My Upcoming Assignments:</h3>
      <div class="course-title">
          <router-link v-bind:to="{ name: 'assignment', params: { courseID: assignment.courseID, assignmentID : assignment.assignmentID } }">
            {{assignment.assignmentName}} 
            <br>
          </router-link>
      </div>

    <br>

    <h3 v-for="lesson in upcomingLessons" v-bind:key="lesson.lessonID">Upcoming Lessons:</h3>
      <div class="course-title">
          <router-link v-bind:to="{ name: 'lesson', params: { courseID: lesson.courseID, lessonID : lesson.lessonID } }">
            {{lesson.lessonName}} 
            <br>
          </router-link>
      </div>

    <br> -->

    <h3>Placeholder for progress bar</h3>

    <br>
    <br>
    
  </div>
</template>

<script>
import courseService from '../services/CourseService';
export default {
    name: 'student-dashboard',

    data() {
      return {
        coursesEnrolledIn: [],
        upcomingAssignments: [],
        upcomingLessons: [],
        gradedAssignments: []
      }
    },

    created() {
      this.getMyEnrolledCourses();
      // this.getMyUpcomingAssignments();
      // this.getMyUpcomingLessons();
      // this.getMyGradedAssignments();
    },

    methods: {
      getMyEnrolledCourses() {
        courseService
          .getMyEnrolledCourses()
          .then(response => {
            this.coursesEnrolledIn = response.data;
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

      getMyUpcomingAssignments() {
        courseService
          .getMyUpcomingAssignments()
          .then(response => {
            this.upcomingAssignments = response.data;
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

      getMyUpcomingLessons() {
        courseService
          .getMyUpcomingLessons()
          .then(response => {
            this.upcomingLessons = response.data;
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

      getMyGradedAssignments() {
        courseService
          .getMyGradedAssignments()
          .then(response => {
            this.gradedAssignments = response.data;
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