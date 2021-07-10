<template>
  <div id="app1">
    {{ name }}
    <p>{{ age }}</p>
    <el-input v-model="name" placeholder="请输入内容"></el-input>
    <el-button @click="changeAge()" size="small">age++</el-button>
    <el-button @click="changeName(name)" size="small">修改name</el-button>
  </div>
</template>

<script lang="ts">
import { ref } from "vue";
export default {
  name: "app1",
  data() {
    return {};
  },
  setup() {
    const name = ref("小四");
    const age = ref(18);

    function changeName(value) {
      name.value = value; //想改变值或获取值 必须.value
    }
    function changeAge() {
      age.value++; //想改变值或获取值 必须.value
    }
    return {
      //必须返回 模板中才能使用
      name,
      age,
      changeName,
      changeAge,
    };
  },
};
</script>
