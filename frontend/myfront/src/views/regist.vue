<template>
 <div class="regist_container">
   <div id="背景" >      </div>
        <div id="左侧"></div>
        <div class ="regist_box">
            <div style="margin-bottom:20px"><img src="../assets/img/icon1.png" width="50%"></div>
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  class="demo-ruleForm">
                    <el-form-item prop="username">
                    <el-input  v-model="ruleForm.username" autocomplete="off" prefix-icon="el-icon-user" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item  prop="e_mail">
                    <el-input  v-model="ruleForm.e_mail" autocomplete="off" prefix-icon="el-icon-message" placeholder="请输入邮箱"></el-input>
                </el-form-item>
                 <el-form-item  prop="vercode">
                     <div class="验证码">
                        <div id="验证码栏">
                            <el-input  v-model="ruleForm.vercode" autocomplete="off"   placeholder="请输入验证码"></el-input>
                        </div>
                        <el-button v-show="show" id="获取验证码" type="primary" @click="getvercode">获取验证码</el-button>
                        <el-button disabled v-show="!show" id="获取验证码" type="primary">获取验证码 {{count}}</el-button>
                     </div>
                </el-form-item>
                <el-form-item  prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off" prefix-icon="el-icon-lock" show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item  prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" prefix-icon="el-icon-lock" show-password placeholder="请确认密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
                </el-form>
        </div>
 </div>
</template>

<script>
const axios = require('axios');
export default {
  data () {
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('年龄不能为空'))
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'))
        } else {
          if (value < 18) {
            callback(new Error('必须年满18岁'))
          } else {
            callback()
          }
        }
      }, 1000)
    }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
    count:'',
    timer: null,
    show: true,
      ruleForm: {
        pass: '',
        checkPass: '',
        age: '',
        username: '',
        e_mail: '',
        vercode: '',
        vercodeId: 0,
      },
      rules: {
        vercode: [{ required: true, message: '请输入验证码', trigger: 'blur' }],
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' },
          { pattern: /^[a-zA-Z0-9\u4e00-\u9fa5]+$/, message: '只能输入汉字、数字、字母', trigger: 'blur' }],
        e_mail: [{ required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱！', trigger: 'blur' }],
        pass: [
          { validator: validatePass, trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' },
          { pattern: /^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        age: [
          { validator: checkAge, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.regist()
        } else {
          this.$notify.error({
            title: '错误',
            message: '请正确填写注册信息',
            offset:50
          })
          return false
        }
      })
    },

    regist () {
      axios.post('http://47.106.193.0:8080/api/user/register', {
        username: this.ruleForm.username,
        password: this.ruleForm.pass,
        email: this.ruleForm.e_mail,
        verCodeId: this.ruleForm.vercodeId,
        verCode: this.ruleForm.vercode
      }).then(res => {
        console.log(res.data)
        if (res.data.code === 200) {
          this.ruleForm.vercodeId = 0
          this.$notify({
            title: '成功',
            message: '注册成功',
            type: 'success',
            offset:50
          })
          this.login();
        } else {
          this.$notify.error({
            title: '错误',
            message: res.data.msg,
            offset:50
          })
        }
      })
    },

    login(){
        const formData = new FormData();
        formData.set('username', this.ruleForm.username);
        formData.set('password', this.ruleForm.pass);
        axios.post('/api/user/login', formData, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }
        ).then(res => {
          var currentuser={
              username: res.data.data.username,
              userid: res.data.data.id,
              token: res.data.data.token,
          }
            localStorage.currentuser=JSON.stringify(currentuser);
            this.$notify({
                title: '注册成功，已为您自动登陆',
                message: '请完善您的个人信息，开启河马生活',
                type: 'success',
                offset:50
            })
            this.$router.push('/mymodifyinformation')
        })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    getvercode () {
      if (this.ruleForm.e_mail === '') {
        this.$notify.error({
          title: '错误',
          message: '请先填写邮箱哦',
          offset:50
        })
      } 
      else {
        axios.post('/api/verification/get', {
          email: this.ruleForm.e_mail
        }).then(res => {
            console.log('fuck');
          this.$notify.success({
            title: '成功',
            message: res.data.msg,
            offset:50
          })
            this.ruleForm.vercodeId = res.data.data.verCodeId
            const TIME_COUNT = 60;
            if (!this.timer) {
                this.count = TIME_COUNT;
                this.show = false;
                this.timer = setInterval(() => {
                if (this.count > 0 && this.count <= TIME_COUNT) {
                    this.count--;
                } else {
                    this.show = true;
                    clearInterval(this.timer);
                    this.timer = null;
                }
                }, 1000);
            }
        })
      }
    }
  }
}
</script>

<style scoped>
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
.regist_container{
    margin-top: 10px;
}
.验证码 #验证码栏{
    width: 40% !important;
    float:left;
}
.验证码 #获取验证码{
    width: 30%;
    float:right;
}
html, body, #app{
    height: 100%;
    width: 100%;
}
.regist_box {
    /* margin-top: 100px; */
    width: 30%;
    height: auto;
    margin: 0 auto;
    text-align: center;
    background: #00000060;
    padding: 20px 50px;
    border-radius: 10px;
}
.el-form-item__error {
  padding-left: 120px;
}
</style>
