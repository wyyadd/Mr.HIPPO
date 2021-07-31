<template>
  <div class="body">
      <!-- 阿斯顿发送到发送到 -->
      <div class="帖子标题">
            <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入您的您的帖子标题"
                v-model="title">
            </el-input>
      </div>
      <div class="发帖">
            <quill-editor ref="text" v-model="content" :options="editorOption" />
      </div>
      <div class="按钮">
          <el-button type="primary" @click="submit">晒一下～</el-button>
      </div>
    <!-- <div v-html="content"></div> -->
  </div>
</template>

<script>
const axios = require('axios');
import { quillEditor } from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
export default {
    components: {
    quillEditor
  },
  data () {
  return {
        title:'',
        content: '',
        editorOption: {} 
  }
},
methods: {
  submit () {
      if (localStorage.hasOwnProperty('currentuser')){
        axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
      }
        axios.post('/api/forum/add',{
            "title":this.title,
            "content":this.content
        })
        // console.log(this.$refs.text.value)
  }
}

}
</script>

<style>
.按钮{
    margin: 20px;
}
.ql-toolbar{
    height: 70px;
    text-align: left;
}
.ql-editor{
	min-height: 300px;
}
</style>

<style scoped>
.body{
    height: 700px;
}
</style>