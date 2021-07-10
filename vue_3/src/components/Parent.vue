<template>
  <div class="home">
    <children :count="count" />
    <button @click="change">+1</button>
  </div>
</template>

<script>
import children from "../components/Children.vue";
import { ref } from "vue";
export default {
  components: {
    children,
  },
  setup() {
    const count = ref(0);
    const change = () => {
      return count.value++;
    };
    return {
      count,
      change,
    };
  },
};
</script>
