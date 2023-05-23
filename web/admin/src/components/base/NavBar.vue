<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <router-link class="navbar-brand title" :to="{ name: 'index' }">控制台</router-link>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'index' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'index' }">欢迎</router-link>
                    </li>
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'station' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'station' }">车站管理</router-link>
                    </li>
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'train' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'train' }">火车管理</router-link>
                    </li>
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'train-station' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'train-station' }">火车车站</router-link>
                    </li>
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'train-carriage' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'train-carriage' }">车厢管理</router-link>
                    </li>
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'train-seat' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'train-seat' }">车座管理</router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

</template>
    
<script>
import { useRoute } from 'vue-router';
import { computed } from 'vue';

export default {
    setup() {
        const route = useRoute();
        let route_name = computed(() => route.name);

        return {
            route_name,
        }
    },
}
</script>
    
<style scoped>
img {
    width: 4vh;
    height: 4vh;
    margin-right: 10px;
    cursor: pointer;
}

.nv {
    margin-left: 1vw;
}

.title {
    margin-left: 5vw;
}
</style>