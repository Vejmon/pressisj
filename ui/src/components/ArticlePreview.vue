<script setup>
import { useTemplateRef, onMounted, onUnmounted, ref} from 'vue';
import { getPrintableDate } from "@/utils/api.js";
import ShowIcon from "@/components/ShowIcon.vue";

const props = defineProps({
  id: {
    type: String,
    required: true,
  },
  title: {
    type: String,
    required: true,
    default: '/'
  },
  subtitle: {
    type: String,
    required: false,
    default: ''
  },
  text: {
    type: String,
    required: true,
  },
  created: {
    type: String,
    required: true,
  },
  links:{
    type: Array,
    required: false,
  },
  _links: {
    type: Object,
    required: false,
  }
});

let observer = null
const articleText = useTemplateRef('articleText')
const overflows = ref(false)
const printableDate = ref(getPrintableDate(new Date(props.created)))
function check() {
  if (!articleText.value) return
  overflows.value = articleText.value.scrollHeight > articleText.value.parentElement.clientHeight
}

onMounted(() => {
  check()
  observer = new ResizeObserver(check)
  observer.observe(articleText.value)
})

onUnmounted(() => observer?.disconnect())

</script>


<template>
  <div class="flex flex-col p-2 max-w-2xl ring-2 rounded-lg min-w-60 hover-with-trigger">
    <div class="flex flex-col p-2 rounded-lg trigger"
         @click="window.location.href = `article/${props.id}`">
      <div class="flex flex-row justify-between align-middle">
        <div class="text-2xl font-bold">
          {{ props.title }}
        </div>
        <div>
          {{printableDate}}
        </div>
      </div>
      <div class="text-base pt-2 pb-2">
        {{ props.subtitle }}
      </div>
      <div
           class="rounded-lg max-h-32 overflow-y-hidden"
           :class="overflows ? 'gradient-fade' : ''">
        <div ref="articleText" class="text-base">
          {{ props.text }}
        </div>
      </div>
    </div>
    <div v-if="props.links.length !== 0">
      <ul class="p-2 pt-5 pl-5">
        <li v-for="link in links" :key="link.name" class="link-hover pt-2 w-fit" @click="window.location.href=`${link.href}`">
          <a><ShowIcon :icon=link.faIcon></ShowIcon> {{ link.name }}</a>
        </li>
      </ul>
    </div>
  </div>
</template>



<style scoped>

</style>