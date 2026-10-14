<script setup>
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faLink } from "@fortawesome/free-solid-svg-icons";
import {faFacebook} from "@fortawesome/free-brands-svg-icons";
// imports of used icons must reflect available icons at /articles/available-icons

const props = defineProps({
  icon: {
    type: String,
    required: false,
    default: "faLink"
  }
});

const availableIcons = {
  "faFacebook": faFacebook,
  "faLink": faLink,
}

</script>

<template>
  <FontAwesomeIcon :icon="availableIcons[props.icon]"></FontAwesomeIcon>
</template>

<style scoped>

</style>