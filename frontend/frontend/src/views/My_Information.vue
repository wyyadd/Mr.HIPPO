<template>
    <div class="右侧页面">
        <el-card class="页面内容">
            <div class="头像和用户名">
                <div class="头像">
                    <el-avatar :size="100" :src="用户信息.avatar" @error="true">
                        <img src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
                    </el-avatar>
                </div>
                <div class="用户名">{{用户信息.username}}</div>
            </div>
            <div class="表单">
                <div class="个性签名">
                    <div class="内容标题">个性签名：</div>
                    <el-card class="个性签名内容" shadow="hover">
                        {{用户信息.signature}}
                    </el-card>
                </div>
                <div class="注册日期">
                    <span class="内容标题">注册日期：</span>
                    <span class="标题对应内容">{{用户信息.createTime}}</span>
                </div>
                <div class="注册邮箱">
                    <span class="内容标题">注册邮箱：</span>
                    <span class="标题对应内容">{{用户信息.email}}</span>
                </div>
                <div class="性别和年龄">
                    <div class="性别">
                        <span class="内容标题">性别：</span>
                        <span class="标题对应内容">{{用户信息.gender}}</span>
                    </div>
                    <div class="年龄">
                        <span class="内容标题">年龄：</span>
                        <span class="标题对应内容">{{用户信息.age}}</span>
                    </div>
                    <div class="家乡">
                        <span class="内容标题">家乡：</span>
                        <span class="标题对应内容">{{用户信息.hometown}}</span>
                    </div>
                </div>
                <div class="手机号">
                    <span class="内容标题">手机号：</span>
                    <span class="标题对应内容">{{用户信息.phone}}</span>
                </div>
                <div class="食物爱好">
                    <span class="内容标题">食物爱好：</span>
                    <span class="标题对应内容">{{用户信息.foodPreference}}</span>
                </div>
                <div class="个人标签">
                    <span class="内容标题">个人标签：</span>
                    <span class="标题对应内容">{{用户信息.label}}</span>
                </div>
                <div class="修改按钮">
                    <el-button icon="el-icon-chat-line-round" v-show="show" type="warning" @click="mou()">哞一下</el-button>
                    <el-button icon="el-icon-chat-line-round" disabled v-show="!show" type="warning" >哞一下 {{count}}</el-button>
                    <el-button type="success" icon="el-icon-edit-outline" @click="editinformation">修改信息</el-button>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script>
const axios = require('axios');
export default {
    methods:{
        editinformation(){
            this.$router.push('/mymodifyinformation')
        },
        mou(){
            this.$notify({
            title: '哞一下成功',
            message: '但此时没有与您一同哞的小河马，请稍后再试',
            type: 'warning',
            offset:50
          })
            const TIME_COUNT = 30;
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
        }
    },
    data(){
        return{
            count:'',
            timer: null,
            show: true,
            用户信息: {
                // "id": 30,
                // "username": "河马先生",
                // "email": "12456789003@123.com",
                // "phone": "18293635616",
                // "avatar": "http://47.106.193.0:8080/upload/2021/07/23/c66d1d2172c740f2bfcf0d762348da03.png",
                // "gender": "男",
                // "age": 19,
                // "foodPreference": "新鲜水果",
                // "signature": "我爱吃！",
                // "hometown": "甘肃省",
                // "residentArea": "东南大学九龙湖校区",
                // "label": "喜欢清淡食物",
                // "createTime": "2021-07-02 12:05:24"
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
.修改按钮{
    height: 25px;
    text-align: center;
}
*{
    text-align: left;
}
.性别和年龄{
    display: flex;
}
.性别,.年龄{
    width: 200px;
}
.表单>div{
    margin-top: 18px;
}
.表单{
    margin-left: 50px;
}
.标题对应内容{
    font-size: 20px;
    margin-bottom: 10px;
    margin-left: 10px;
}
.内容标题{
    font-size: 20px;
    font-weight: bold;
    margin-bottom: 10px;
}
.个性签名{
    width: 85%;
}
.个性签名内容{
    min-height: 100px;
    /* height:100px; */
    /* overflow:scroll; */
}
.页面内容{
    border-radius: 30px;
    padding:15px
}
.头像和用户名{
    margin-left: 20px;
    margin-top: 10px;
    margin-bottom: 30px;
    height: 100px;
    display: flex;
}
.头像{
    width: 100px;
    height: 100px;
}
.用户名{
    margin-left: 30px;
    font-size: 40px;
    font-weight:bold;
    height: 100px;
    line-height: 100px;
}

</style>