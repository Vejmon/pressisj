<script setup>

const props = defineProps({
  pages: {
    type: Object,
    required: false,
    default: () => {
      return {"Hjem": "/"};
    }
  }
});

</script>

<template>
  <div class="app-header">
    <div class="flex flex-row justify-center gap-4 p-2">
        <router-link v-for="(v,k) in pages" :to="v" :key="k" class="link-hover hover:text-gray-300">
          {{ k }}
        </router-link>
      </div>
      <div>
      </div>
  </div>
</template>

<style scoped>

</style>