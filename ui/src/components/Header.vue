<script setup>

const props = defineProps({
  to: {
    type: String,
    required: false,
    default: '/'
  }
});

</script>

<template>
  <div class="app-header">
    <div class="flex flex-row justify-center gap-4 p-2">
        <router-link to="/" class="link-hover hover:text-gray-300">
          Hjem
        </router-link>
        <router-link to="/nyheter" class="link-hover hover:text-gray-300">
|          Nyheter
        </router-link>
        <router-link to="/kontakt" class="link-hover hover:text-gray-300">
          Kontakt
        </router-link>
      </div>
      <div>
      </div>
  </div>
</template>

<style scoped>

</style>