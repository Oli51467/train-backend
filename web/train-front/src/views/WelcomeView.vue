<template>
    <a-layout>
        <HeaderView></HeaderView>
        <a-layout>
            <SiderView></SiderView>
            <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
                <router-view></router-view>
            </a-layout-content>
        </a-layout>
    </a-layout>
</template>
<script>
import { defineComponent } from 'vue';
import HeaderView from "@/components/base/HeaderView.vue";
import SiderView from "@/components/base/SiderView.vue";

export default defineComponent({
    components: {
        HeaderView,
        SiderView,
    },
    setup() {
        return {
        };
    },
});
</script>
<style></style>