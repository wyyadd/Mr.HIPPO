<template>
    <div class="右侧页面">
        <el-card class="页面内容" v-for="(item, index) in forum" :key="index" @click.native="tocontent(item.id)" shadow="hover">
            <div class="头像和用户名">
                <div class="头像">
                    <el-avatar :size="70" :src="item.userAvatar" @error="true">
                        <img src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
                    </el-avatar>
                </div>
                <div class="用户名">{{item.username}}</div>
            </div>
            <div class="内容标题">
                标题:{{item.title}}
            </div>
        </el-card>
    </div>
</template>

<script>
const axios = require('axios');
export default {
    methods:{
        tocontent(id){
            console.log(id);
            this.$router.push({ path: '/forumcontent', query: { id: id } })
        },
        editinformation(){
            this.$router.push('/mymodifyinformation')
        }
    },
    data(){
        return{
            forum:[],
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
        
        // axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
        axios.post('http://47.106.193.0:8080/api/forum/findall',{
                "pageIndex":0,
                "pageSize":5
        }).then(resp=>{
            console.log(resp);
            this.forum=resp.data.data.forum;
        })
        // alert('fuck');
        
    }
}
</script>

<style scoped>
*{
    text-align: left;
}
.内容标题{
    margin-left: 20px;
    font-size: 30px;
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
    padding:15px;
    margin-bottom: 10px;
}
.头像和用户名{
    margin-left: 20px;
    margin-top: 10px;
    margin-bottom: 30px;
    height: 70px;
    display: flex;
}
.头像{
    width: 70px;
    height: 70px;
}
.用户名{
    margin-left: 30px;
    font-size: 20px;
    font-weight:bold;
    height: 70px;
    line-height: 70px;
}

</style>