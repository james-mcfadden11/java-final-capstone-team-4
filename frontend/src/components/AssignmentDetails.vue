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

    <!-- need assignment.submission property -->
    <form v-if="!assignment.isSubmitted" v-on:submit.prevent="submitAssignment" v-show="!isTeacher">
      <h3>Submission:</h3>
      <input type="text" v-model="assignment.submission" />
      <br>
      <button type="submit">Submit</button>
      <button v-on:click.prevent="resetSubmission">Cancel</button>
      <br>
    </form>

    <!-- needs to have v-if="assignment.isSubmitted" in form element -->
    <!-- need assignment.submission property -->
    <form v-on:submit.prevent="submitFeedbackAndGrade" v-show="isTeacher">
      <h3>Feedback and grade:</h3>
      <input type="number" v-model="assignment.studentGrade">
      <input type="text" v-model="assignment.teacherFeedback" />
      <br>
      <button type="submit">Submit</button>
      <button v-on:click.prevent="resetFeedbackAndGradeForm">Cancel</button>
      <br>
    </form>

    <br>

    <h4>Teacher feedback: {{assignment.teacherFeedback}}</h4>

    <br>

  </div>
</template>

<script>
export default {
  name: 'assignment-details',

  props: ['isTeacher'],

  data() {
    return {
      assignment: {}
    }
  }
}
</script>

<style>

</style>