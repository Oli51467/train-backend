import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'index',
        meta: {
            requestAuth: true,
        },
        component: () => import('../views/WelcomeView.vue'),
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
