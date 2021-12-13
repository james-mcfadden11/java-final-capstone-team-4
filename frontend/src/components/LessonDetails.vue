<template>
  <div class="main-div">
    <!-- Teacher Youtube Video Link Submission -->
    <div class="youtube-link-teacher" v-show="isTeacher">
      <p>Please paste the youtube video URL link for this lesson's content below:</p>
      <input class="vid-url-input" placeholder="Lesson Video URL"/>
      <button class="save-btn">Save</button>
    </div>

    <h1>Lesson Video</h1>

    <!-- Youtube Video -->
    <vue-youtube class="youtube-vid"></vue-youtube>

    <!-- Teacher Google Doc Link Submission -->
    <div class="google-doc-link-teacher" v-show="isTeacher">
      <p>Please paste the Google Doc URL link for this lesson's content below:</p>
      <input class="vid-url-input" placeholder="Lesson Video URL"/>
      <button class="save-btn">Save</button>
    </div>

    <h2>Lesson Text</h2>

    <!-- Student Clickable Link for Google Doc -->
    <p>Click the link below for additional text-based content for this lesson.</p>
    <a href="https://docs.google.com/document/d/1ZGLwgDGd6vg-GssCPe0d7TQDO6wJ0uAruhNiANNiG3s/edit">Lesson Text</a>
  </div>
</template>

<script>
import VueYoutube from '../components/VueYoutube.vue';

export default {
  components: { VueYoutube },
    name: 'lesson-details',
    
    
    props: ['isTeacher']

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

.vid-url-input {
  width: 33.2%;
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

</style>