<template>
  <div id="app1">
    {{ name }}
    <p>{{ age }}</p>
    <el-input v-model="name" placeholder="请输入内容"></el-input>
    <el-button @click="changeAge()" size="small">age++</el-button>
    <el-button @click="changeName(name)" size="small">修改name</el-button>
  </div>
  <div style="height: 300px">
    <el-image :src="imageUrl" lazy></el-image>
  </div>
  <div>
    <el-button @click="changeImage()" size="small">刷新图片</el-button>
    <el-button type="text" @click="open()">点击打开 Dialog</el-button>
  </div>
  <div>
    <el-dialog
      title="提示"
      v-model="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <code
        ref="dialogopen"
        :l="42"
        :r="10"
        :w="catcha.w"
        :h="catcha.h"
        :blocky="catcha.blocky"
        :imgurl="catcha.imgurl"
        :miniimgurl="catcha.miniimgurl"
        :slider-text="catcha.text"
        @success="onSuccess"
        @fail="onFail"
        @refresh="onRefresh"
      >
      </code>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import code from "./Code.vue";
import { ref } from "vue";
export default {
  name: "app1",
  data() {
    return {
      dialogVisible: false,
      catcha: {
        blocky: 0,
        imgurl: "",
        miniimgurl: "",
        text: "向右滑动完成拼图",
        h: 200,
        w: 350,
        sh: 45,
        sw: 55,
        modifyImg: "",
      }, // 图片验证码传值
    };
  },
  setup() {
    const name = ref("小四");
    const age = ref(18);
    const imageUrl = ref("https://picsum.photos/500/300");

    function changeName(value) {
      name.value = value; //想改变值或获取值 必须.value
    }
    function changeAge() {
      age.value++; //想改变值或获取值 必须.value
    }
    function changeImage() {
      imageUrl.value =
        "https://picsum.photos/500/300?random=" + Math.round(Math.random() * 10000);
    }
    return {
      //必须返回 模板中才能使用
      name,
      age,
      changeName,
      changeAge,
      imageUrl,
      changeImage,
    };
  },
  created() {
    //this.getImageVerifyCode();
  },
  methods: {
    open() {
      this.dialogVisible = true;
      this.getImageVerifyCode();
    },
    // 获取图形验证码
    getImageVerifyCode() {
      this.catcha.imgurl =
        "https://picsum.photos/500/300?random=" + Math.round(Math.random() * 1000);
      this.catcha.miniimgurl =
        "data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==";
      /* getKaptchaImg().then(res => {
        if (res && res.data) {
          // console.log(res, this.$refs.dialogopen)
          var imgobj = res.data
          this.catcha.blocky = imgobj.puzzleYAxis
          this.catcha.imgurl = 'data:image/png;base64,' + imgobj.modifyImg
          this.catcha.miniimgurl = 'data:image/png;base64,' + imgobj.puzzleImg
          this.$nextTick(() => {
            if (this.$refs.dialogopen) {
              this.$refs.dialogopen.reset(imgobj.puzzleYAxis)
            }
          })
        }
      }) */
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    onFail() {
      console.log("fail");
    },
    onSuccess(left) {
      this.loginForm.distance = left;
      console.log("succss", left);
      // 验证是否成功checkKaptchaImg是后台验证接口方法
    },

    // 刷新
    onRefresh() {
      this.imgurl = "";
      this.miniimgurl = "";
      this.getImageVerifyCode();
    },
  },
};
</script>
