<script setup>
import {reactive, ref, watch} from "vue";
import {postData} from "@/utils/api.js";

const props = defineProps({
  errors: {
    type: Object,
    required: false,
    default: () => {
      return null;
    }
  }
});

console.log('errors', props.errors)
const errors = ref(props.errors?.errors)
const emailError = ref(props.errors?.errors?.find(error => error.field === 'email'))
const nameError = ref(props.errors?.errors?.find(error => error.field === 'name'))
const messageError = ref(props.errors?.errors?.find(error => error.field === 'message'))
const formData = reactive({
  email: '',
  name: '',
  message: ''
})

watch(() => errors, (newValue, oldValue) => {
  console.log('newValue', newValue.value)
  emailError.value = newValue.value.errors?.find(error => error.field === 'email')
  nameError.value = newValue.value.errors?.find(error => error.field === 'name')
  messageError.value = newValue.value.errors?.find(error => error.field === 'message')

  },
  {deep: true}
);

const submitForm = ( async () => {
  console.log('submitForm', formData)
  await postData("/open/email", formData)
      .then(() => window.location.href = "/")
      .catch((err) => {
        errors.value = err?.response?.data;
        console.log('error', errors.value)
      })

});

</script>

<template>
  <form class="flex flex-col gap-2" action="/open/email" method="post" @submit.prevent="submitForm">
    <div class="flex flex-col">
      <div class="flex flex-row justify-between">
        <label for="email">Epost</label>
        <div v-if="emailError" class="text-red-400">
          {{ emailError.defaultMessage }}
        </div>
      </div>
      <input v-model="formData.email" class="ring-2 rounded-lg" name="email" type="email" placeholder="epost@epostsen.no" required/>
    </div>

    <div class="flex flex-col">
      <div class="flex flex-row justify-between">
          <label for="name">Navn</label>
        <div v-if="nameError" class="text-red-400">
          {{ nameError.defaultMessage }}
        </div>
      </div>
      <input v-model="formData.name" class="ring-2 rounded-lg" name="navn" type="text" placeholder="Navn Navnesen" required/>
    </div>

    <div class="flex flex-col pb-4">
      <div class="flex flex-row justify-between">
        <label for="message">Melding</label>
        <div v-if="messageError" class="text-red-400">
          {{ messageError.defaultMessage }}
        </div>
      </div>
      <textarea v-model="formData.message" class="ring-2 rounded-lg" name="message" type="text" placeholder="Melding Meldingsen" required/>
    </div>
    <button type="submit" class="button bg-blue-800 rounded-lg cursor-pointer p-2 ">Send</button>
  </form>
</template>

<style scoped>

</style>