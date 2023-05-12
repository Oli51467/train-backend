import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        component: () => import('../views/WelcomeView.vue'),
    },
    {
        path: '/login/',
        name: 'login',
        component: () => import('../views/LoginView.vue'),
    },
    {
        path: '/welcome/',
        name: 'welcome',
        component: () => import('../views/WelcomeView.vue'),
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// 一个跳转页面的api 在router加载之前起作用 from..to 来自哪个页面和目标页面 next 要不要进行下一步
// router.beforeEach((to, from, next) => {
//     if ((to.meta.requestAuth && !store.state.user.is_login)) {
//         next({ name: "login" });
//     } else {
//         next();
//     }
// })

export default router
