import {ref} from "vue";
import {defineStore} from "pinia";

export const useArticleStore = defineStore('articles', () => {
    const data = ref([])
    const currentPage = ref(null)

    const addResponse = (response) => {
        console.log(response)
        currentPage.value = response
        data.value.push(...response._embedded.articles)
    }
    const getComplete = () => {
        const returnPage = ref(currentPage.value);
        if (currentPage.value?._embedded) {
            currentPage.value._embedded.articles = data.value;
        }
        console.log('returnPage', returnPage)
        return returnPage
    }
    return { addResponse, getComplete }
})