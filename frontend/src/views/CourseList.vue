<template>
  <course v-bind:isTeacher="this.$store.state.isTeacher"/>
</template>

<script>
import Course from '../components/Course';

export default {
    components: {
        Course
    }
}
</script>

<style>

</style>