<template>
  <div>
    Course name:
    <h1>{{courseInfo.title}}</h1>

    Teacher name:
    <h3>{{teacherName}}</h3>

    Description:
    <h4>{{courseInfo.description}}</h4>

    Course lessons:
    <div v-for="lesson in lessons" v-bind:key="lesson.lessonID">
      {{lesson.lessonNumber}}
      <router-link v-bind:to="{ name: 'lesson', params: { courseID: lesson.courseID, lessonID : lesson.lessonID } }">
         {{lesson.lessonName}}
      </router-link>
      {{lesson.description}}
    </div>

    Assignments:
    <div v-for="assignment in assignments" v-bind:key="assignment.assignmentID">
      {{assignment.assignmentNumber}}
      <router-link v-bind:to="{ name: 'assignment', params: { courseID: assignment.courseID, assignmentID : assignment.assignmentID } }">
        {{assignment.assignmentName}}
      </router-link>
      {{assignment.description}}
      {{assignment.possiblePoints}}
      {{assignment.dueDate}}
    </div>

    <p></p>
    <p></p>

    Forms for teacher to add course lessons and homework:

    <button v-if="!showAssignmentForm" v-on:click="showAssignmentForm = !showAssignmentForm">Add New Assignment</button>
      <form v-on:submit.prevent="saveNewAssignment" v-if="showAssignmentForm">
        <h3>Add New Assignment:</h3>
        Assignment Number:
        <input type="number" v-model="newAssignment.assignmentNumber" />
        Assignment Name:
        <input type="text" v-model="newAssignment.assignmentName" />
        Assignment Description:
        <input type="text" v-model="newAssignment.description" />
        Possible points:
        <input type="number" v-model="newAssignment.possiblePoints" />
        Due date:
        <input type="date" v-model="newAssignment.dueDate" />

        <button type="submit">Save</button>
        <button v-on:click.prevent="resetAssignment">Cancel</button>
      </form>

    <button v-if="!showLessonForm" v-on:click="showLessonForm = !showLessonForm">Add New Lesson</button>
      <form v-on:submit.prevent="saveNewLesson" v-if="showLessonForm">
        <h3>Add New Lesson:</h3>
        Lesson Number:
        <input type="number" v-model="newLesson.lessonNumber" />
        Lesson Name:
        <input type="text" v-model="newLesson.lessonName" />
        Lesson Description:
        <input type="text" v-model="newLesson.description" />

        <button type="submit">Save</button>
        <button v-on:click.prevent="resetLesson">Cancel</button>
      </form>

  </div>
</template>

<script>
import courseService from '../services/CourseService';

export default {
  name: 'course-details',

  data() {
    return {
        lessons: [],
        assignments: [],
        teacherName: '',
        courseInfo: {},
        courseID: this.$route.params.courseID,
        newAssignment: {},
        newLesson: {},
        showAssignmentForm: false,
        showLessonForm: false
    }
  },
  
  created() {
    this.getAssignments(this.courseID);
    this.getLessons(this.courseID);
    this.getTeacherName(this.courseID);
    this.getCourseInfo(this.courseID);
  },

  methods: {
    getAssignments(courseID) {
      courseService
        .getAssignments(courseID)
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
    },

    getLessons(courseID) {
      courseService
        .getLessons(courseID)
        .then(response => {
          this.lessons = response.data;
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

    saveNewAssignment() {
      courseService
        .addAssignment(this.newAssignment, this.courseID)
        .then(response => {
          if(response && response.status == 201) {
          this.getAssignments(this.courseID);
          this.resetAssignment();
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

    saveNewLesson() {
      courseService
        .addLesson(this.newLesson, this.courseID)
        .then(response => {
          if(response && response.status == 201) {
          this.getLessons(this.courseID);
          this.resetLesson();
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

    resetAssignment() {
      this.newAssignment = {};
      this.showAssignmentForm = false;
    },

    resetLesson() {
      this.newLesson = {};
      this.showLessonForm = false;
    },

    getCourseInfo(courseID) {
      courseService
        .getCourseInfo(courseID)
        .then(response => {
          this.courseInfo = response.data;
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

    getTeacherName(courseID) {
      courseService
        .getTeacherName(courseID)
        .then(response => {
          this.teacherName = response.data;
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