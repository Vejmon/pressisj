<script setup lang="ts">
import ArticlePage from "@/components/ArticlePage.vue";
import {onMounted, onUnmounted, ref} from "vue";
import {getData} from "@/utils/api.js"
import ArticleSkeleton from "@/components/ArticleSkeleton.vue";
import {useArticleStore} from "@/stores/articles.js";


const articleStore = useArticleStore();
const loading = ref(articleStore.getComplete().value == null);
const error = ref(false);
const currentPage = ref(articleStore.getComplete().value);
onMounted( async () => {
  if (currentPage.value != null) return;
  await getData("/api/articles/search/created")
    .then(data => {
      error.value = false;
      currentPage.value = data
      articleStore.addResponse(data)
  })
    .catch(() =>{
      error.value = true;
  })
    .finally(() =>{
      loading.value = false;
  })
});

onUnmounted(() =>{
  loading.value = true
})
</script>

<template>
  <div class="flex flex-col gap-4 w-full content-stretch p-10">
    <div v-if="loading" class="flex flex-col gap-4">
      <ArticleSkeleton></ArticleSkeleton>
      <ArticleSkeleton></ArticleSkeleton>
      <ArticleSkeleton class="gradient-fade"></ArticleSkeleton>
    </div>
    <p v-else-if="error">Hups, her har det skjedd en feil!</p>
    <ArticlePage v-else v-bind="currentPage" @updatePage="(val) => {articleStore.addResponse(val)}"/>
  </div>
</template>

<style scoped>
</style>
