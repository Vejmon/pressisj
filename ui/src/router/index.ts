import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/views/Home.vue";
import Liste from '@/views/Liste.vue';
import Landing from '@/views/Landing.vue';
import Kontakt from '@/views/Kontakt.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'serverHome',
            component: Home,
            children: [
                {
                    path: '',
                    name: 'Landing',
                    component: Landing,
                    children: [
                    ]
                },
                {
                    path: 'nyheter',
                    name: 'Liste',
                    component: Liste,
                    children: [
                    ]
                },
                {
                    path: 'kontakt',
                    name: 'Kontakt',
                    component: Kontakt,
                    children: [
                    ]
                }
            ]
        },
        {
            path: '/:pathMatch(.*)*',
            redirect: '/',
        },
    ],
})

export default  router
