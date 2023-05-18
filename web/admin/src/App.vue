<template>
    <NavBar/>
    <router-view />
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import NavBar from './components/base/NavBar.vue';
export default {
    components: {
        NavBar,
    },
    created() {
        //在页面加载时读取localStorage里的状态信息
        if (localStorage.getItem('store')) {
            // 存储状态
            this.$store.replaceState(Object.assign({}, this.$store.state, JSON.parse(localStorage.getItem('store'))))
        }
        // 在页面刷新时将vue里的信息保存到localStorage里
        window.addEventListener('beforeunload', () => {
            localStorage.setItem('store', JSON.stringify(this.$store.state));
        })
    }
}
</script>

<style></style>
