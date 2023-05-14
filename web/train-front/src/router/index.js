import { createRouter, createWebHistory } from 'vue-router'
import store from '../store/index'

const routes = [
    {
        path: '/login/',
        name: 'login',
        meta: {
            requestAuth: false,
        },
        component: () => import('../views/LoginView.vue'),
    },
    {
        path: '/index/',
        name: 'index',
        meta: {
            requestAuth: true,
        },
        component: () => import('../views/WelcomeView.vue'),
    },
    {
        path: "/passenger/",
        name: "passenger",
        meta: {
            requestAuth: true,
        },
        component: () => import('../views/PassengerView.vue'),
    },
    {
        path: "/ticket/",
        name: "ticket",
        meta: {
            requestAuth: true,
        },
        component: () => import('../views/TicketView.vue'),
    },
    {
        path: "/order/",
        name: "order",
        meta: {
            requestAuth: true,
        },
        component: () => import('../views/OrderView.vue'),
    },
    {
        path: "/my_ticket/:userId",
        name: "my_ticket",
        meta: {
            requestAuth: true,
        },
        component: () => import('../views/MyTicketView.vue'),
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

//一个跳转页面的api 在router加载之前起作用 from..to 来自哪个页面和目标页面 next 要不要进行下一步
router.beforeEach((to, from, next) => {
    if ((to.meta.requestAuth && !store.state.user.is_login && !store.state.user.token)) {
        next({ name: "login" });
    } else {
        next();
    }
})

export default router
