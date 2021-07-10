<template>
  <h1>{{ msg }}</h1>
  <h3 style="color: #832691">Hello World</h3>
  <el-button type="primary" @click="state.count++">count is: {{ state.count }}</el-button>

  <router-link to="/setup">setup</router-link>
  
</template>

<script setup>
import { reactive, ref } from 'vue'


defineProps({
  msg: String
})

const state = reactive({ count: 0 })


</script>

<style scoped>
a {
  color: #42b983;
}
</style>
