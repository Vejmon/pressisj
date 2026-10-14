<script setup>
import Header from "@/components/Header.vue";

const pages = JSON.parse(document.getElementById("pagesMeta").content);
</script>

<template>
  <header>
    <Header :pages="pages"/>
  </header>
  <main>
    <router-view />
  </main>

</template>

<style scoped>

</style>