<template>
    <div>
        <Navbar ref="nav_ins" style="text-align:center"></Navbar>
        <el-row class="tac">
            <div class="正文">
                <div class="左侧菜单">
                    <el-menu 
                    :default-active="this.$route.path"
                    router>
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>订单中心</span>
                            </template>
                            <el-menu-item index="/myorder">我的订单</el-menu-item>
                            <el-menu-item index="/mycomment">我的评价</el-menu-item>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>关注中心</span>
                            </template>
                            <el-menu-item index="2-1">我的收藏</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </div>
            </div>
            <router-view class='menu-right'  />
        </el-row>
    </div>
</template>

<script>
import axios from 'axios'
import Navbar from './Navbar.vue'
export default {
    methods: {
        payorder(index){
            localStorage.waitorderid=this.订单列表[index].orderId;
            this.$router.push('/ordercheck')
        },
        deleteorder(index){
            axios.defaults.headers.common['Authorization']=JSON.parse(localStorage.currentuser).token;
            axios.post('/api/order/delete',{"orderId": this.订单列表[index].orderId}).then(resp=>{
                this.$notify.success({
                    title: '订单删除成功',
                    message: "该订单已从您的账户内删除～",
                    offset:50
                })
                this.updateorder();
            })
            // this.订单列表.splice(index,1);
            // alert('订单删除成功')
        },
        updateorder(){
            axios.defaults.headers.common['Authorization']=JSON.parse(localStorage.currentuser).token;
            axios.post("/api/order/all/information").then(resp=>{
                // console.log(resp.data.data.orders);
                this.订单列表=resp.data.data.orders;
            })
        }
    },
    data:function(){
        return {
            订单列表:[]
          }
    },
    components:{
        Navbar
    },
    filters:{
        orderType (num) {
        if (num == 0) {
            return "已删除"
        } else if (num == 1) {
            return "未支付"
        } else if (num == 2) {
            return "已支付"
        }
        },
    },
    created(){
        this.updateorder();
    }
}
</script>

<style scoped>
    .menu-right{
        margin-left:260px;
    }
    .商品名称{
        font-size:10px
    }
    * {
        text-align: left;
    }
    .订单按钮{
        text-align:center;
        margin: 10px;
    }
    .正文{
        margin: auto;
        width: 95%;
    }
    .el-menu{
        height: 600px;
    }
    .hr {
        height: 1px;
        background-color: #ddd;
    }
    .左侧菜单{
        float:left;
        width: 15%;
        display:inline;
    }
    .右侧正文{
        margin-left: 20px;
        margin-right: 20px;
        float:left;
        width: 80%;
        display:inline
    }
    .订单列表{
        background-color: blue;
    }
</style>