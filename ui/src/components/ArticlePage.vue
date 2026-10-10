<script setup >
import { ref, onMounted, computed} from 'vue';
import { useInfiniteScroll } from '@vueuse/core';
import ArticlePreview from "@/components/ArticlePreview.vue";
import {getData} from "@/utils/api.js";

const emit = defineEmits(["updatePage"]);
const props = defineProps({
  _embedded: {
    type: Object,
    required: true,
  },
  _links: {
    type: Object,
    required: true,
  },
  page: {
    type: Object,
    required: true,
  },
});

const isLoading = ref(false);
const error = ref(false);
const currentLinks = ref(props._links);
const data = ref(props._embedded.articles)

const loadMore = async () => {
  if (isLoading.value) return;
  isLoading.value = true;
  const url = new URL(currentLinks.value.next.href)
  await getData(url.pathname + url.search)
      .then((newItems) => {
        currentLinks.value = newItems._links
        data.value.push(...newItems._embedded.articles)
        emit('updatePage', newItems)
      })
      .catch(() => {
        error.value = true;
      })
      .finally(() => {
        isLoading.value = false;
      })
};

const stopLoading = computed(() => {
  return !Object.keys(currentLinks.value).includes("next")
})

// Initialize infinite scroll
onMounted(() =>{
  useInfiniteScroll(
      window,
      loadMore,
      {
        distance: 100,
        canLoadMore: () => {
          if (error.value) return false

          return Object.keys(currentLinks.value).includes("next")
        }
      })
})

</script>


<template>
  <ul ref="listElement" class="flex flex-col gap-4">
    <li v-for="(item, index) in data" :key="index">
        <ArticlePreview v-bind="item" :link="item._links.self.href"/>
    </li>
  </ul>
  <div v-if="stopLoading">Ingen flere nyheter</div>
</template>



<style scoped>

</style>