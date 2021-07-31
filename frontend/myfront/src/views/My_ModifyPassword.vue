<template>
    <div>
        <div class='background'>
            <div class="主体">
                <el-card class="卡片">
                    <img src="../img/lock_icon.png" width="35%" style="margin:30px">
                    <div class="表单">
                        <!-- <div class="sb">
                            <div class="内容标题">账户原密码：</div>
                            <div class="输入框">
                                <el-input v-model="origin_password" placeholder="请输入您的年龄"></el-input>
                            </div>
                        </div>
                        <div class="sb">
                            <span class="内容标题">账户新密码：</span>
                            <div class="输入框">
                                <el-input v-model="first_password" placeholder="请输入您的年龄"></el-input>
                            </div>
                        </div>
                        <div class="sb">
                            <span class="内容标题">确认新密码：</span>
                            <div class="输入框">
                                <el-input v-model="second_password" placeholder="请输入电子邮箱"></el-input>
                            </div>
                        </div> -->

                            <!-- <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
                                <el-form-item prop="orgpass">
                                    <el-input></el-input>
                                </el-form-item>
                                <el-form-item id="现密码" prop="pass">
                                    <el-input  type="password" v-model="ruleForm.checkpass" autocomplete="off"  show-password placeholder="请输入新密码"></el-input>
                                </el-form-item>
                                <el-form-item id="确认现密码" prop="checkPass">
                                    <el-input  type="password" v-model="ruleForm.checkPass" autocomplete="off"  show-password placeholder="请确认新密码"></el-input>
                                </el-form-item>
                            </el-form> -->

                            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
                            <el-form-item  prop="oripass">
                                <el-input v-model="ruleForm.oripass" show-password placeholder="请输入原密码"></el-input>
                            </el-form-item>
                            <el-form-item prop="pass">
                                <el-input type="password" v-model="ruleForm.pass" show-password placeholder="请输入新密码"></el-input>
                            </el-form-item>
                            <el-form-item  prop="checkPass">
                                <el-input type="password" v-model="ruleForm.checkPass" show-password placeholder="请确认新密码"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                                <el-button @click="resetForm('ruleForm')">重置</el-button>
                            </el-form-item>
                            </el-form>
                        <el-button class="按钮" type="success" @click="submitForm">提交修改</el-button>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    methods:{
        submitForm(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axios.defaults.headers.common['Authorization']=JSON.parse(localStorage.currentuser).token;
                    axios.post('/api/user/password',{
                        "oldPassword":this.ruleForm.oripass,
                        "newPassword":this.ruleForm.pass
                    }).then(resp=>{
                        if (resp.data.code==200){
                            this.$notify.success({
                                title: '密码修改成功',
                                message: '账户密码已更新,请重新登陆',
                                offset: 70
                            });
                            this.resetForm(formName);
                            localStorage.removeItem('currentuser');
                            this.$router.push('/login');
                        }
                        else{
                            this.$notify.error({
                                title: '密码修改失败',
                                message: '请重新检查所填写项目',
                                offset: 70
                            });
                        }
                    })
                }
                else {
                    this.$notify.error({
                        title: '密码修改失败',
                        message: '请重新检查所填写项目',
                        offset: 70
                    });
                }
            });
        },
        resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
    data(){
        var validatePass1 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入原密码'));
            } else {
                axios.defaults.headers.common['Authorization']=JSON.parse(localStorage.currentuser).token;
                axios.post('/api/user/password',{
                    "oldPassword":value,
                    "newPassword":value
                }).then(resp=>{
                    console.log(resp.data)
                    if (resp.data.code!=200){
                        callback(new Error('原密码输入错误'))
                    }
                    else{
                        callback();
                    }
                })
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
            callback(new Error('请输入密码'));
            } else {
            if (this.ruleForm.checkPass !== '') {
                this.$refs.ruleForm.validateField('checkPass');
            }
            callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
            callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.pass) {
            callback(new Error('两次输入密码不一致!'));
            } else {
            callback();
            }
        };
        return{
            origin_password:'',
            ruleForm: {
                oripass: '',
                pass: '',
                checkPass: '',
            },
            rules: {
                oripass: [
                    { validator: validatePass1, trigger: 'blur' }
                ],
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ]
            },
            用户信息: {
                "id": 30,//
                "username": "河马先生",//
                "email": "12456789003@123.com",
                "phone": "18293635616",
                "avatar": "http://47.106.193.0:8080/upload/2021/07/23/c66d1d2172c740f2bfcf0d762348da03.png",//
                "gender": "男",
                "age": 19,
                "foodPreference": "新鲜水果",
                "signature": "我爱吃！",
                "hometown": "甘肃省",
                "residentArea": "东南大学九龙湖校区",
                "label": "喜欢清淡食物",
                "createTime": "2021-07-02 12:05:24"//
            }
        }
    }
}
</script>

<style scoped>
.输入框{
    margin-left: 10px;
    width: 200px;
}
.表单{
    width: 80%;
    margin: 0 auto;
}
.sb{
    display: flex;
    height: 40px;
    line-height: 40px;
}
.标题对应内容{
    font-size: 25px;
    margin-left: 20px;
    font-weight: bold;
}
.按钮{
    margin-top:30px;
}
.表单>div{
    margin-top: 20px;
    text-align: left;
    /* margin-left: 50px; */
}
.内容标题{
    font-size: 25px;
    font-weight: bold;
}
.主体{
    display: table-cell;
    vertical-align: middle;
}
.卡片{
    width: 50%;
    height: 77%;
    border-radius: 35px;
    margin: 0 auto;
}
.background{
    display: table;
    height: 700px;
    width: 100%;
    /* min-height: 700px; */
    background: linear-gradient(rgba(255, 255, 255, 0.9), rgba(255, 255, 255, 0.9)), url('../img/lockback1.png') repeat center/ 10%;
}
</style>