<template>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <slot class="child"></slot>
                <!--slot存放父组件传来的所有children-->
            </div>
        </div>
    </div>

</template>
  
<script>
export default {
    components: {
    }
}
</script>
  
<style scoped>
.container {
    margin-top: 3vh;
    margin-bottom: 3vh;
    width: 75%;
}

.card-body {
    text-align: center;
    height: auto;
    width: auto;
}
</style>