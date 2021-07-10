<template>
  <div>
    <h1>子组件：{{ count }}</h1>
  </div>
</template>

<script>
import { computed, toRefs } from "vue";
export default {
  props: ["count"],
  setup(props, context) {
    console.log('打印context:', context)
    // toRefs 将props中接收到的属性转为响应式
    //let { count } = toRefs(props);

    //计算属性computed
    const count = computed({
        get: () => props.count
    })
    return {
      count,
    };
  },
};
</script>
