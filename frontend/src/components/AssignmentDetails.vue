<template>
  <div>
    <br>
    <h2>{{assignment.name}}</h2>
    <br>
    <h4>Assignment number: {{assignment.assignmentNumber}}</h4>
    <br>
    <h4>Due date: {{assignment.dueDate}}</h4>
    <br>

    <h4>Grade: {{assignment.studentScore}} </h4>
    <h4>Possible points: {{assignment.possiblePoints}}</h4>
    <br>
    <h5>Description: {{assignment.description}}</h5>
    <br>

    <h4 v-show="assignment.isSubmitted">Submission: {{assignment.submission}}</h4>

    <form v-if="!assignment.isSubmitted" v-on:submit.prevent="updateAssignmentDetails(assignment, assignmentID, courseID)" v-show="!isTeacher">
      <h3>Submission:</h3>
      <input type="text" v-model="assignment.submission" />
      <br>
      <button type="submit">Submit</button>
      <button v-on:click.prevent="resetSubmission">Cancel</button>
      <br>
    </form>

    <br>

    <form v-on:submit.prevent="updateAssignmentDetails(assignment, assignmentID, courseID)" v-show="isTeacher" v-if="assignment.isSubmitted">
      <h3>Feedback and grade:</h3>
      <input type="number" v-model="assignment.studentGrade">
      <input type="text" v-model="assignment.teacherFeedback" />
      <br>
      <button type="submit">Submit</button>
      <button v-on:click.prevent="resetFeedbackAndGradeForm">Cancel</button>
      <br>
    </form>

    <br>

    <h4 v-show="assignment.isGraded">Teacher feedback: {{assignment.teacherFeedback}}</h4>

    <br>

  </div>
</template>

<script>
import courseService from '../services/CourseService';

export default {
  name: 'assignment-details',

  props: ['isTeacher'],

  data() {
    return {
      assignment: {},
      assignmentID: this.$route.params.assignmentID,
      courseID: this.$route.params.courseID
    }
  },

  created() {
    this.getAssignmentDetails(this.courseID, this.assignmentID);
  },

  methods: {
    getAssignmentDetails(courseID, assignmentID) {
      courseService
        .getAssignmentDetails(courseID, assignmentID)
        .then(response => {
          this.assignment = response.data;
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

    updateAssignmentDetails(assignment, assignmentID, courseID) {
      courseService
        .updateAssignmentDetails(assignment, assignmentID, courseID)
        .then(response => {
          if (response && response.status == 201) {
            this.getAssignmentDetails(this.courseID, this.assignmentID);
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

</style>