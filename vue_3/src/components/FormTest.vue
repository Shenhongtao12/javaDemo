<template>
  <div style="width: 400px; margin: auto">
    <el-radio-group v-model="labelPosition" size="small">
      <el-radio-button label="left">左对齐</el-radio-button>
      <el-radio-button label="right">右对齐</el-radio-button>
      <el-radio-button label="top">顶部对齐</el-radio-button>
    </el-radio-group>
    <div style="margin: 20px"></div>
    <el-form
      :label-position="labelPosition"
      label-width="80px"
      :model="formLabelAlign"
    >
      <el-form-item label="名称">
        <el-input v-model="formLabelAlign.name"></el-input>
      </el-form-item>
      <el-form-item label="活动区域">
        <el-input v-model="formLabelAlign.region"></el-input>
      </el-form-item>
      <el-form-item label="活动形式">
        <el-input v-model="formLabelAlign.type"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('formLabelAlign')"
          >立即创建</el-button
        >
        <el-button @click="reset('formLabelAlign')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import { reactive } from "vue";

export default {
  setup(props, context) {
    let formLabelAlign = reactive({
      name: "",
      region: "",
      type: "",
    });

    function submitForm(formName) {
      console.log("submitForm", formLabelAlign);
    }

    function resetForm() {
      formLabelAlign.name = "";
      formLabelAlign.region = "";
      formLabelAlign.type = "";
    }

    return { formLabelAlign, submitForm, resetForm };
  },
  data() {
    return {
      labelPosition: "right",
    };
  },
  methods: {
    reset(formName) {
      this.resetForm();
      if (this.$refs[formName] !== undefined) {
        this.$refs[formName].resetFields();
      }
    },
  },
};
</script>
