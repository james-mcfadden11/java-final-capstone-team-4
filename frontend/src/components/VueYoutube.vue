<template>
    <div class="iframe">
        <youtube :video-id="videoId" ref="youtube" @playing="playing"> {{ getVideoId }} </youtube>
    </div>
</template>

<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'

Vue.use(VueYoutube)

export default {
  data() {
    return {
      videoId: 'nQEXNUy-IIA'
    }
  },
  methods: {
    playVideo() {
      this.player.playVideo()
    },
    playing() {
      console.log('o/ we are watching!!!')
    },
    getVideoId () {
      return this.$youtube.getIdFromUrl(this.video.url)
    }
  },
  computed: {
    player () {
      return this.$refs.youtube.player
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
