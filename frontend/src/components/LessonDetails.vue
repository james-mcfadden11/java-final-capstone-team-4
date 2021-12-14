<template>
  <div class="main-div">
    <!-- Teacher Youtube Video Link Submission -->
    <div class="forms-div" v-show="isTeacher">
      <form class="youtube-url-form">
        <p>Please paste the youtube video URL link for this lesson's content below:</p>
        <input class="vid-url-input" type="url" placeholder="Lesson Video URL"/>
        <button class="youtube-save-btn" type="submit" v-on:click.prevent="updateLessonVideo(this.lesson ,course.courseID, lesson.lessonID)">Save</button>
      </form>
      <form class="google-url-form">
        <p>Please paste the Google Doc URL link for this lesson's content below:</p>
        <input class="doc-url-input" type="url" placeholder="Google Doc URL"/>
        <button class="google-save-btn" type="submit" v-on:click.prevent="updateLessonDoc(this.lesson, course.courseID, lesson.lessonID)">Save</button>
      </form>
      <form class="youtube-description-form">
        <p>Please enter in a description for the video content of this lesson below:</p>
        <textarea class="vid-description" placeholder="Video Description..."/>
        <button class="save-vid-description" type="submit" v-on:click.prevent="updateVidDescription(this.lesson, course.courseID, lesson.lessonID)">Save</button>
      </form>
    </div>
    <h1>Lesson Video</h1>
    <!-- Youtube Video & Google Doc Embedd -->
    <div class="iframe">
        <youtube v-bind:video-id="videoId" ref="youtube" @playing="playing"></youtube>
        <iframe width = 650px height = 1000px src="https://docs.google.com/document/d/e/2PACX-1vT7hVH5HKfvIgYx08fSwQtX1HjiqjgV_5ofdLMChv78EjOjgUMW_h1is_R0x_8PxQccuMzTblzMd7uW/pub?embedded=true"></iframe>
    </div>
    <h2>Additional Resources</h2>
    <!-- Student Clickable Link for Google Doc -->
    <p>Click the link below for additional resources for this lesson.</p>
    <a href="https://docs.google.com/document/d/1ZGLwgDGd6vg-GssCPe0d7TQDO6wJ0uAruhNiANNiG3s/edit">Lesson Text</a>
  </div>
</template>
<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'
import courseService from '../services/CourseService';
Vue.use(VueYoutube)
export default {
  name: 'lesson-details',
  props: ['isTeacher'],
  data() {
    return {
        lesson: {},
        courseID: this.$route.params.courseID,
        lessonID: this.$route.params.lessonID,
        videoId: ''
    }
  },
  created() {
    this.getLessonDetails(this.courseID, this.lessonID);
  },
  methods: {
    // These two methods are required for the YouTube API to work with Vue
    playVideo() {
      this.player.playVideo()
    },
    playing() {
      console.log('o/ we are watching!!!')
    },
    getLessonDetails() {
      courseService
        .getLessonDetails()
        .then(response => {
          this.lesson = response.data;
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
    updateLessonVideo(courseID, lessonID) {
      courseService
          .updateLessonVideo(courseID, lessonID)
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
    },
    updateLessonDoc(courseID, lessonID) {
      courseService
          .updateLessonDoc(courseID, lessonID)
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
    },
    updateVidDescription(courseID, lessonID) {
      courseService
          .updateVidDescription(courseID, lessonID)
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
  },
  computed: {
    // This computed property is required for the YouTube API to work in Vue
    player () {
      return this.$refs.youtube.player
    }
  }
}
</script>
<style>
.iframe.youtube-vid {
display: block;
margin-left: auto;
margin-right: auto;
margin-top: 10px;
text-align: center;
}
.main-div {
  text-align: center;
}
p {
margin-bottom: 5px;
}
.save-btn {
  display:inline-block;
  margin-top: 8px;
  margin-bottom: 8px;
  margin-left: 8px;
  width: 60px;
}
h1, h2 {
  text-decoration: underline;
}
.forms-div {
  background-image: linear-gradient(to bottom right, rgba(0, 0, 0, 0.315), rgb(68, 68, 68));
  border-style: solid;
  border-radius: 5px;
  border-width: 1px;
  border-color: rgb(221, 221, 197);
  width: 50%;
  margin-left: auto;
  margin-right: auto;
}
.forms-div > p {
display: block;
text-align: center;
margin-top: 20px;
margin-bottom: 20px;
margin-left: 20px;
margin-right: 20px;
}
.vid-url-input, .doc-url-input, .vid-description{
  display: block;
  margin-right: auto;
  margin-left: auto;
}
.forms-div, input {
  width: 80%;
}
.youtube-save-btn, .google-save-btn, .save-vid-description {
  display: block;
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
  margin-bottom: 20px;
  width: 81%;
}
textarea {
  background-color: rgb(68, 68, 68);
  color: white;
  width: 80%;
  height: 100px;
  margin-bottom: 20px;
  border-radius: 3px;
  border-style: 1px;
  border-color: rgb(221, 221, 197);
  border-style: 1px;
  font-family: sans-serif;
}
</style>