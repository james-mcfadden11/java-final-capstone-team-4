<template>
  <div>
      <h2>Students' Progress</h2>
      {{course.title}}
      <br>
      <br>

      <div v-for="student in students" v-bind:key="student.studentID">
          <div v-for="assignment in assignments" v-bind:key="assignment.assignmentID">
            <div v-if="assignment.studentID == student.studentID">
                <!-- this route needs updated to include studentID somehow -->
                <router-link v-bind:to="{ name: 'assignment', params: { courseID: assignment.courseID, assignmentID : assignment.assignmentID } }">
                    {{assignment.assignmentName}}
                </router-link>
                {{assignment.studentGrade}} out of {{assignment.possiblePoints}}
                Submitted: {{assignment.graded ? "Yes" : "No"}}
                Graded: {{assignment.submitted ? "Yes" : "No"}}
            </div>
          </div>

      </div>
  </div>
</template>

<script>
import courseService from '../services/CourseService';

export default {
    name: 'students-progress',

    date() {
        return {
            students: [],
            assignments: [],
            courseID: this.$route.params.courseID,
            course: {}
        }
    },

    created() {
        this.getStudentsForCourse(this.courseID);
        this.getCourseInfo(this.courseID);
        this.getAssignmentsForCourse(this.courseID);
    },

    methods: {
        getStudentsForCourse(courseID) {
            courseService
                .getStudentsForCourse(courseID)
                .then(response => {
                    this.students = response.data;
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

        getCourseInfo(courseID) {
            courseService
                .getCourseInfo(courseID)
                .then(response => {
                    this.course = response.data;
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

        getAssignmentsForCourse(courseID) {
            courseService
                .getAssignmentsForCourse(courseID)
                .then(response => {
                    this.assignments = response.data;
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