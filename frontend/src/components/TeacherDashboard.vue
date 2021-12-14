<template>
  <div>
    <h2>Teacher Dashboard</h2>
    
    <br>

    <h3>My Courses:</h3>
    <div v-for="course in myCoursesToTeach" v-bind:key="course.courseNumber">
      <div>
          <router-link v-bind:to="{ name: 'course-details', params: { courseID: course.courseID } }">
            {{course.title}} 
          </router-link>
          <br>
          <br>
      </div>
    </div>

    <br>
    <br>
    
  </div>
</template>

<script>
import courseService from '../services/CourseService';
export default {
    name: 'teacher-dashboard',

    data() {
      return {
        myCoursesToTeach: []
      }
    },

    created() {
      this.getMyCoursesToTeach();
    },

    methods: {
      getMyCoursesToTeach() {
        courseService
          .getMyCoursesToTeach()
          .then(response => {
            this.current = response.data;
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

.teacher-notifications {
  display: block;
  text-align: right;
}
</style>