import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'index',
        component: () => import('../views/WelcomeView.vue'),
    },
    {
        path: '/station/',
        name: 'station',
        component: () => import('../views/business/DailyTrainStationView.vue'),
    },
    {
        path: '/train/',
        name: 'train',
        component: () => import('../views/business/TrainView.vue'),
    },
    {
        path: '/train-station/',
        name: 'train-station',
        component: () => import('../views/business/TrainStationView.vue'),
    },
    {
        path: '/train-carriage/',
        name: 'train-carriage',
        component: () => import('../views/business/TrainCarriageView.vue'),
    },
    {
        path: '/train-seat/',
        name: 'train-seat',
        component: () => import('../views/business/TrainSeatView.vue'),
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
