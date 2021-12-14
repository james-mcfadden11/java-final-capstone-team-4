<template>
    <div class="iframe">
        <youtube v-bind:video-id="videoId" ref="youtube" @playing="playing"></youtube>
        



        
        <iframe width = 650px height = 1000px src="https://docs.google.com/document/d/e/2PACX-1vT7hVH5HKfvIgYx08fSwQtX1HjiqjgV_5ofdLMChv78EjOjgUMW_h1is_R0x_8PxQccuMzTblzMd7uW/pub?embedded=true"></iframe>
    </div>
</template>

<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'
import courseService from '../services/CourseService'

Vue.use(VueYoutube)

export default {
  data() {
    return {
      lessons: [],
      videoId: 'SSo_EIwHSd4',
      videoUrl: '',
      lessonID: this.$route.params.lessonID
    }
  },
  created() {
    this.getVideoUrl(this.lessonID)
  },

  methods: {
    
    getVideoId () {
      return this.$youtube.getIdFromUrl(this.video.url)
    },
    
    setVideoId () {
      courseService
        .getVideoUrl
    },

    getVideoUrl(lessonID) {
      courseService
        .getVideoUrl(lessonID)
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
  computed: {
    player () {
      return this.$refs.youtube.player
    }
    }
  }
}
</script>

<style scoped>
.iframe {
  width: 100%;
  max-width: 650px; /* Also helpful. Optional. */
}
</style>
