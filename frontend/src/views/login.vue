<template>
    <div class="login_container">
      <div id="背景">      </div>
        <div class ="login_box">
            <div style="margin-bottom:20px"><img src="../assets/img/icon1.png" width="50%"></div>
            <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules">
                <!--用户名-->
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" prefix-icon="el-icon-user" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <!--密码-->
                <el-form-item prop="password">
                    <el-input type="password" v-model="loginForm.password" prefix-icon="el-icon-lock" show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <!--按钮区-->
                <el-button type="primary" @click="login">登录</el-button>
                <el-button @click="resetloginForm">重置</el-button>
                <el-button type="info" @click="login_to_regist">注册</el-button>
            </el-form>
        </div>
    </div>
</template>

<script>
const axios = require('axios');
export default {
  data () {
    return {
      loginForm: {
        username: '',
        password: '',
        iflogin: ''
      },
      // 表单验证规则
      loginFormRules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    resetloginForm () {
      this.$refs.loginFormRef.resetFields()
    },
    login () {
      const formData = new FormData();
      formData.set('username', this.loginForm.username);
      formData.set('password', this.loginForm.password);

      axios.post('http://47.106.193.0:8080/api/user/login', formData
      ).then(res => {
          if (res.data.code===200){
            this.$notify({
                title: '登陆成功，自动跳转到主页',
                message: res.data.msg,
                type: 'success',
                offset: 50
            })
            var currentuser={
                username: res.data.data.username,
                userid: res.data.data.id,
                token: res.data.data.token,
            }
            localStorage.setItem('currentuser', JSON.stringify(res.data.data))
            axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
            axios.post('http://47.106.193.0:8080/api/user/information').then(res => {
            console.log(res.data)
            localStorage.setItem('userAvatar', JSON.stringify(res.data.data))
            this.$router.push('/finalhome')
            })
          }
          else{
              this.$notify.error({
                title: '错误',
                message: res.data.msg,
                offset: 50
                })

          }
        })
    },
    login_to_regist () {
      this.$router.push('/regist')
    }
  }
}
</script>

<style scoped>
html, body, #app{
    height: 100%;
    width: 100%;
}
.login_container {
    margin-top: 100px;
    /* background-color: rgba(150, 183, 187, 0.596); */
    width: 100%;
    height: 78%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.login_box {
    width: 30%;
    height: auto;
    margin: 0 auto;
    text-align: center;
    background: #00000060;
    padding: 20px 50px;
    border-radius: 30px;
}
#背景{
   position: fixed;
      top: -200px;
      left: 0;
      width: 100%;
      height: 130%;
      z-index: -10;
      object-fit: cover;
      background-image: url('../assets/img/login_background.jpg');
}
</style>
