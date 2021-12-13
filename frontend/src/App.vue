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
  background: cadetblue;
  background-image: url('./assets/darkthemebg.jpg');
  background-position: center;
  background-size: 100%;
  color: rgb(221, 221, 197);
  font-family: sans-serif;
}

a {
  color: rgb(221, 221, 197);
}


a:visited {
color: rgb(172, 172, 152);
}

button {
  border-style: solid;
  border-width: 1px;
  border-radius: 3px;
  padding: 5px;
  background-color: rgb(43, 43, 43);
  color: rgb(221, 221, 197);
}

button:hover {
  border-style: solid;
  border-width: 1px;
  border-radius: 3px;
  padding: 6px;
  background-color: rgb(43, 43, 43);
  color: rgb(221, 221, 197);
}

</style>
