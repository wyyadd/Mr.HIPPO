<template>
    <div class="右侧页面">
        <el-card class="页面内容">
            <div class="头像和用户名">
                <div class="头像">
                    <el-upload
                    action="#"
                    :auto-upload='false'
                    :on-change="handleChange"
                    :show-file-list="false"
                    >
                    <el-avatar :size="100" :src="用户信息.avatar" @error="true">
                        <img src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
                    </el-avatar>
                    </el-upload>
                </div>
                <div class="用户名">点击头像框更新头像</div>
            </div>
            <div class="表单">
                <div class="性别和年龄">
                    <div class="性别 sb">
                        <span class="内容标题">性别：</span>
                            <div class="输入框1">
                                <el-radio v-model="用户信息.gender" label="男">
                                    <span class="标题对应内容">男</span>
                                </el-radio>
                                <el-radio v-model="用户信息.gender" label="女">
                                    <span class="标题对应内容">女</span>
                                </el-radio>
                            </div>
                    </div>
                    <div class="年龄 sb">
                        <div class="内容标题">年龄：</div>
                        <div class="年龄输入框">
                            <el-input v-model="用户信息.age" placeholder="请输入您的年龄"></el-input>
                        </div>
                    </div>
                    <div class="家乡 sb">
                        <span class="内容标题">家乡：</span>
                            <div class="家乡输入框">
                                <el-input v-model="用户信息.hometown" placeholder="请输入您的家乡"></el-input>
                            </div>
                    </div>
                </div>
                <div class="个性签名">
                    <div class="内容标题">个性签名：</div>
                    <el-input class="个性签名内容"
                    type="textarea"
                    :autosize="{ minRows: 4, maxRows: 5}"
                    placeholder="请输入您的个性签名"
                    v-model="用户信息.signature"
                    >
                    </el-input>
                </div>
                <div class="食物爱好 sb">
                    <span class="内容标题">食物爱好：</span>
                    <span class="标题对应内容">
                        <div class="食物爱好输入框">
                            <el-input v-model="用户信息.foodPreference" placeholder="请输入您喜好的食物"></el-input>
                        </div>
                    </span>
                </div>
                <div class="个人标签 sb">
                    <span class="内容标题">个人标签：</span>
                    <span class="标题对应内容">
                        <div class="个人标签输入框">
                            <el-input v-model="用户信息.label" placeholder="请输入您的河马标签"></el-input>
                        </div>
                    </span>
                </div>
                <div class="修改按钮">
                    <el-button type="success" icon="el-icon-edit-outline" @click="submit">提交信息</el-button>
                </div>
            </div>
        </el-card>
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
                        title: '个人信息修改成功',
                        message: '个人信息已更新',
                        offset: 70
                    });
                }
                else{
                    this.$notify.error({
                        title: '个人信息修改失败',
                        message: '请重新检查所填写项目',
                        offset: 70
                    });
                }
            })
        },
        handleChange(file,fileList){
            this.fileList=fileList
            this.用户信息.avatar=URL.createObjectURL(file.raw)
            this.uploadimg();
        },
        uploadimg(){
            const formData = new FormData();
            formData.set('file', this.fileList[this.fileList.length-1].raw);
            axios.defaults.headers.common['Authorization']=JSON.parse(localStorage.currentuser).token;
            axios.post("/api/user/upload",formData).then(rep=>{
                if (rep.data.code===200){
                    this.$notify.success({
                        title: '头像更新成功',
                        message: '个人头像已更新',
                        offset: 70
                    });
                }
            })
        },
    },
    data(){
        return{
            fileList:'',
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
            this.用户信息=resp.data.data.user;
        })
        // alert('fuck');
        
    }
}
</script>

<style scoped>
.sb{
    display: flex;
    height: 40px;
    line-height: 40px;
}
.年龄输入框{
    width: 150px;
}
.家乡输入框{
    width: 150px;
}
.食物爱好输入框,.个人标签输入框{
    width: 700px;
}
.修改按钮{
    height: 25px;
    text-align: center;
    width: 90%;
}
*{
    text-align: left;
}
.性别和年龄{
    display: flex;
}
.性别,.年龄{
    width: 300px;
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
    /* margin-left: 10px; */
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
    font-size: 30px;
    font-weight:bold;
    height: 100px;
    line-height: 100px;
}

</style>