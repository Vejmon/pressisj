<script setup>
import homeImg from '@/assets/home.jpg';
import { onMounted } from 'vue'
import { marked } from 'marked'
import {getData} from "@/utils/api.js";

onMounted(async () => {
  const homeContent = document.getElementById('homeContent')
  const response = await getData('/home.md')
      .then(res => {
        homeContent.innerHTML = marked.parse(res)
      })
      .catch(error => homeContent.innerHTML = marked.parse(error.message))

})

</script>

<template>
    <div class="max-w-2xl p-4">
      <section class="container" id="homeContent"></section>
    </div>
</template>

<style scoped>
</style>
