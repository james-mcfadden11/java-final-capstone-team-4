<template>
  <div>
    <div class="teachers-eyes-only" v-show="!isTeacher">
      <p>Please paste the youtube video url for this lesson's content below:</p>
      <input class="vid-url-input" placeholder="Lesson Video URL"/>
      <button>Save</button>
    </div>
    
  </div>
</template>

<script>


export default {
    name: 'lesson-details',
    
    props: ['isTeacher']

}
</script>

<style>
.vid-url-input {
  width: 33.2%;
}

p {
margin-bottom: 5px;
}
button {
  display: block;
  margin-top: 8px;
  width: 60px;
}

</style>