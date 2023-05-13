<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <router-link class="navbar-brand title" :to="{ name: 'index' }">Oli51467</router-link>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'index' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'index' }">欢迎</router-link>
                    </li>
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'passenger' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'passenger' }">乘客</router-link>
                    </li>
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'ticket' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'ticket' }">购票</router-link>
                    </li>
                    <li class="nav-item nv">
                        <router-link v-bind:class="route_name == 'order' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'order' }">订单</router-link>
                    </li>
                </ul>

                <!--若用户已登录则展示用户信息菜单-->
                <ul class="navbar-nav" v-if="$store.state.user.is_login">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                            {{ $store.state.user.mobile }}
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <router-link class="dropdown-item"
                                    :to="{ name: 'my_ticket', params: { userId: $store.state.user.id } }">我的车票</router-link>
                            </li>
                            <!-- <li>
                                <router-link class="dropdown-item"
                                    :to="{ name: 'update_info', params: { userId: $store.state.user.id } }">个人信息</router-link>
                            </li> -->
                            <li>
                                <hr class="dropdown-divider">
                            </li> 
                            <li><a class="dropdown-item" href="#" @click="logout">退出登陆</a></li>
                        </ul>
                    </li>
                </ul>

                <!--若用户未登录则展示登录-注册界面-->
                <ul class="navbar-nav" v-else>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'login' }">登录</router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

</template>
    
<script>
import { useRoute } from 'vue-router';
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
    setup() {
        const route = useRoute();
        const store = useStore();
        let route_name = computed(() => route.name);

        const logout = () => {
            store.dispatch("logout");
        }

        return {
            logout,
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