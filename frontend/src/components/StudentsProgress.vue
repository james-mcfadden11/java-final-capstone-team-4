<template>
  <div>
      <h2>Students' Progress</h2>
      <h3>{{course.title}}</h3>

      <br>
      <br>

      <div v-for="student in students" v-bind:key="student.student_id">
          <h4>{{student.firstName}} {{student.lastName}}</h4>
          <progress id="file"  v-bind:value="completedAssignments(student.student_id)" v-bind:max="totalAssignments(student.student_id)"> 15% </progress>
          <div v-for="assignment in assignments" v-bind:key="assignment.uniqueID">
            <div v-if="assignment.studentID == student.student_id">
                <router-link v-bind:to="{ name: 'assignment-teacher', params: { courseID: assignment.courseID, assignmentID : assignment.assignmentID, studentID : student.student_id } }">
                    {{assignment.assignmentName}}
                </router-link>
                {{assignment.studentGrade}} out of {{assignment.possiblePoints}}
                Submitted: {{assignment.submitted ? "Yes" : "No"}}
                Graded: {{assignment.graded ? "Yes" : "No"}}
            </div>
          </div>

      </div>
  </div>
</template>

<script>
import courseService from '../services/CourseService';

export default {
    name: 'students-progress',

    data() {
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

        completedAssignments(studentID) {
            let counter = 0;
            for (let assignment of this.assignments) {
                if(assignment.studentID==studentID && assignment.submitted) {
                    counter++;
                }
            }
            return counter;
        },

        totalAssignments(studentID) {
                 let counter = 0;
            for (let assignment of this.assignments) {
                if(assignment.studentID==studentID) {
                    counter++;
                }
            }
            return counter;
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
                    let counter = 1;
                    for (let entry of this.assignments) {
                        entry.uniqueID = counter;
                        counter++;
                    }
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