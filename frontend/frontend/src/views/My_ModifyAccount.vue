<template>
    <div>
        <div class='background'>
            <div class="主体">
                <el-card class="卡片">
                    <img src="../img/user_icon.png" width="35%" style="margin:30px">
                    <div class="表单">
                        <div class="sb">
                            <div class="内容标题">用户名称：</div>
                            <div class="标题对应内容">{{用户信息.username}}</div>
                        </div>
                        <div class="sb">
                            <span class="内容标题">手机号码：</span>
                            <div class="输入框">
                                <el-input v-model="用户信息.phone" placeholder="请输入您的手机号码"></el-input>
                            </div>
                        </div>
                        <div class="sb">
                            <span class="内容标题">电子邮箱：</span>
                            <div class="输入框">
                                <el-input v-model="用户信息.email" placeholder="请输入电子邮箱"></el-input>
                            </div>
                        </div>
                        <el-button class="按钮" type="success" @click="submit">提交修改</el-button>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>
const axios = require('axios');
export default {
    methods:{
        submit(){
            axios.post('/api/user/information/modify',this.用户信息).then(resp=>{
                // console.log(resp.data.code);
                if (resp.data.code===200){
                    this.$notify.success({
                        title: '账户信息修改成功',
                        message: '账户信息已更新',
                        offset: 70
                    });
                }
                else{
                    this.$notify.error({
                        title: '账户信息修改失败',
                        message: '请重新检查所填写项目',
                        offset: 70
                    });
                }
            })
        }
    },
    data(){
        return{
            用户信息: {
                // "id": 30,//
                // "username": "河马先生",//
                // "email": "12456789003@123.com",
                // "phone": "18293635616",
                // "avatar": "http://47.106.193.0:8080/upload/2021/07/23/c66d1d2172c740f2bfcf0d762348da03.png",//
                // "gender": "男",
                // "age": 19,
                // "foodPreference": "新鲜水果",
                // "signature": "我爱吃！",
                // "hometown": "甘肃省",
                // "residentArea": "东南大学九龙湖校区",
                // "label": "喜欢清淡食物",
                // "createTime": "2021-07-02 12:05:24"//
            }
        }
    },
        created(){
        
        axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
        axios.post('http://47.106.193.0:8080/api/user/information').then(resp=>{
            console.log(resp);
            this.用户信息=resp.data.data.user;
        })
        // alert('fuck');
        
    }
}
</script>

<style scoped>
.输入框{
    margin-left: 0px;
    width: 200px;
}
.表单{
    width: 70%;
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
    background: linear-gradient(rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.8)), url('../img/userback.jpg') repeat center/ 10%;
}
</style>