<template>
    <div class="右侧页面">
        <el-card class="页面内容" v-for="(item, index) in comment" :key="index" shadow="hover">
            <div class="头像和用户名">
                <div class="头像">
                    <el-image 
                        style="width: 120px; height: 120px"
                        :src="item.productUrl"
                        @click.native="toshop(item.id)">
                    </el-image>
                </div>
                <div class="用户名">
                    <div class="title">
                        {{item.productName}}
                    </div>
                    <div>
                        <!-- <el-rate :v-model="1"></el-rate> -->
                        <el-rate
                        disabled
                        show-score
                        v-model="item.star"
                        :icon-classes="iconClasses"
                        void-icon-class="icon-rate-face-off"
                        :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
                        </el-rate>
                    </div>
                    <div class="评论">
                        {{item.username}} : “{{item.comment}}”
                    </div>
                </div>
            </div>
            <div class="内容标题">
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
        },
        toshop (id) {
            this.$router.push({ path: '/singleproduct', query: { id: id } })
        }
    },
    data(){
        return{
            comment:[],
            fuck:null,
            iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3']
        } 
    },
    created(){
        axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
        axios.post("/api/user/comment").then(resp=>{
            this.comment=resp.data.data
            console.log(this.comment)
        })
    }
}
</script>

<style scoped>
*{
    text-align: left;
}
.评论{
    font-size: 15px;
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
    display: flex;
}
.头像{
    width: 70px;
    height: 70px;
    margin-bottom: 20px;
}
.title{
    font-weight:bold;
}
.用户名{
    margin-left: 120px;
    /* font-size: 15px; */
    /* font-weight:bold; */
    height: 70px;
    line-height: 40px
}

</style>