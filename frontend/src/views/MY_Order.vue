<template>
    <div>
        <el-row class="tac">
            <div class="右侧正文">
                <div class="订单页面">
                    <!-- <div class="标题"><h3>我的订单</h3></div> -->
                    <div class='hr'></div>
                    <div class="订单列表内容">
                        <el-table :data="订单列表" :header-cell-style="{background:'#eef1f6',color:'#606266'}"
                        stripe
                        border
                        height="700">
                            <el-table-column label="订单号" prop="orderId" header-align=center align="center" width="100px">

                            </el-table-column>
                            <el-table-column label="订单详情" header-align=center align="center" prop="订单列表" width="650px">
                                <template slot-scope="scope">
                                    <el-table :data="scope.row.orderItem" :header-cell-style="{background:'#eef1f6',color:'#606266'}"
                                    :border='false'>
                                        
                                        <el-table-column label="商品图片" header-align=center align="center" width="100px">
                                            <template slot-scope="scope1">
                                                <img :src="scope1.row.pictureUrl" style="width: 50px; height: 50px" />
                                            </template>
                                        </el-table-column>
                                        <el-table-column label="商品名称" header-align=center prop="name" align="center" width="300px">
                                            <template slot-scope="scope1">
                                                <div class="商品名称">
                                                    {{scope1.row.name}}
                                                </div>
                                            </template>
                                        </el-table-column>
                                        <el-table-column label="单价*数量" header-align=center align="center" width="100px">
                                            <template slot-scope="scope1">
                                                <strong style="color:red">{{scope1.row.price.toFixed(2)}}</strong>
                                                *
                                                <strong style="color:black">{{scope1.row.quantity}}</strong>
                                            </template>
                                        </el-table-column>
                                        <el-table-column label="小计" header-align=center align="center">
                                            <template slot-scope="scope1">
                                                    {{scope1.row.sum=(scope1.row.price*scope1.row.quantity).toFixed(2)}}
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </template>
                            </el-table-column>
                            <el-table-column label="总金额" header-align=center align="center">
                                <template slot-scope="scope">
                                    <strong style="color:red">{{scope.row.orderPaymentMoney.toFixed(2)}}</strong>
                                </template>
                            </el-table-column>
                            <el-table-column label="订单状态" header-align=center align="center">
                                <template slot-scope="scope">
                                    {{scope.row.status=scope.row.orderStatus|orderType}}
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" header-align=center align="center" prop="quantity">
                                <template slot-scope="scope">
                                        <div class="订单按钮">
                                            <el-button v-if="scope.row.orderStatus==1" type="success" @click="payorder(scope.$index)">
                                                支付订单
                                            </el-button>
                                        </div>
                                        <div class="订单按钮">
                                            <el-button v-if="scope.row.orderStatus!=0" type="danger" @click="deleteorder(scope.$index)">
                                                删除订单
                                            </el-button>
                                        </div>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </div>
            </div>
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
        height: 300px;
    }
    .hr {
        height: 1px;
        background-color: #ddd;
    }
    .右侧正文{
        margin-right: 20px;
        float:left;
        width: 100%;
        display:inline
    }
    .订单列表{
        background-color: blue;
    }
</style>