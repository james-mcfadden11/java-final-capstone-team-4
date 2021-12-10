<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <br>
    <router-view v-bind:isTeacher="this.$store.state.isTeacher"/>
  </div>
</template>

<style> 
body
{
  background-color: cadetblue;
  /* background-image: url(C:\Users\Student\workspace\java-final-capstone-team-4\frontend\src\assests\darkthemebg.jpg); */
  background-position: center;
  background-size: 100% 
  }
</style>
