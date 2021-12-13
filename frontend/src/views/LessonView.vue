<template>
    <div>
        <lesson-details></lesson-details>
        <br>
        <vue-youtube></vue-youtube>
        <br>
        <vue-google-api></vue-google-api>
        
    </div>
</template>

<script>
import LessonDetails from '../components/LessonDetails.vue';
import VueGoogleApi from '../components/VueGoogleApi.vue';
import VueYoutube from '../components/VueYoutube.vue';

export default {
    components: {
        LessonDetails,
        VueYoutube,
        VueGoogleApi
    }
}
</script>

<style>

</style>