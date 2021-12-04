<template>
  <div>
    <div v-for="day in days" v-bind:key="day.dayNumber">

        <router-link v-bind:to="{ name: 'content', params: { courseID: ??, dayNumber: day.dayNumber } }">
        {{day.instructionalContent}} 
        </router-link>
        
        <router-link v-bind:to="{ name: 'homework', params: { courseID: ??, dayNumber: day.dayNumber } }">
        {{day.homeworkAssignemnt}}
        </router-link>

    </div>
  </div>
</template>

<script>
export default {
    name: 'course-details'

}
</script>

<style>

</style>