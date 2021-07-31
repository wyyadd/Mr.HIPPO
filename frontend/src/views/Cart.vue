<template>
    <div>
        <Navbar ref="nav_ins"></Navbar>
        <div id="购物车表">
            <div class="标题">
                <h3>购物车</h3>
            </div>
            <div class='hr'></div>
            <!-- 表头 -->
            <el-table :data="购物车列表" @selection-change="handleSelectionChange">
                <el-table-column type="selection"></el-table-column>
                <el-table-column type="index" label="序号" header-align=center ></el-table-column>
            
            <!-- 表内容 -->
                <el-table-column label="商品图片" header-align=center align="center" width="150">
                    <template slot-scope="scope">
                        <img :src="scope.row.productPicture"  style="width: 80px; height: 80px" />
                    </template>
                </el-table-column>
                <el-table-column prop="productName" label="商品名称" header-align=center align="center"></el-table-column>
                <el-table-column label="商品价格" header-align=center align="center">
                    <template slot-scope="scope">
                        {{scope.row.productPrice.toFixed(2)}}
                    </template>
                </el-table-column>
                <el-table-column label="购买数量" header-align=center align="center">
                    <template slot-scope="scope">
                        <div class="div_number" style="text-align:center">
                            <el-input-number :min="1" @change="updateCart" size="mini" v-model="scope.row.quantity" placeholder="0" :value="0"></el-input-number>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="操作" header-align=center align="center">
                    <template slot-scope="scope">
                        <el-button @click.native.prevent="deleteRow(scope.$index, 购物车列表, 购物车列表[scope.$index].id)" type="danger" size="mini"> 删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            
            <el-row>
                <el-col :span="8">
                    <span style="font-size:20px; float:left; margin:20px">共 {{购物车列表.length}} 件商品，已选择 {{total_num}} 件</span>
                </el-col>
                <el-col :span="16">
                    <span style="font-size:20px; float:right; margin:20px">合计 : {{total_price}}元</span>
                </el-col>
            </el-row>
            <div class="提交">
                <el-button type="success" @click="GenOrder">生成订单</el-button>
            </div>

        </div>
    </div>
</template>

<script>
import Navbar from './Navbar.vue'
const axios = require('axios');
export default {
    components:{
        Navbar,
    },
    data:function(){
        return {
            购物车列表:[],
            check_list:[]
          }
    },
    methods:{
        GenOrder(){
            if (this.check_list.length<=0){
                this.$notify.error({
                    title: '订单生成错误',
                    message: "您还没有选择任何商品～",
                    offset:50
                })
                return;
            }
            var subform = {
                "products":[]
            }
            var subarray=subform.products;

            for (var i=0;i<this.check_list.length;i++){
                subarray[i]={};
                subarray[i].id=this.check_list[i].id;
                subarray[i].quantity=this.check_list[i].quantity;
            }
            
            axios.defaults.headers.common['Authorization']=JSON.parse(localStorage.currentuser).token;
            axios.post('http://47.106.193.0:8080/api/order/generation',subform).then(res => {
                if (res.data.msg==="订单创建成功"){
                    
                    var ids=[];
                    for (var i=0;i<this.check_list.length;i++)
                        ids[i]=this.check_list[i].id;
                    console.log(ids);
                    for (var i=0;i<ids.length;i++){
                        for (var j=0;j<this.购物车列表.length;j++){
                            if (this.购物车列表[j].id===ids[i]){
                                this.购物车列表.splice(j,1);//从index开始删除1项
                                localStorage.tempcart=JSON.stringify(this.购物车列表);//更新本次缓存
                            }
                        }
                    }
                    this.$router.push('/myorder');
                }
            })
        },
        updateCart(){
            console.log('购物车缓存更新');
            localStorage.tempcart=JSON.stringify(this.购物车列表);//更新本次缓存
        },
        deleteRow(index, rows, productid) {
            this.$notify.success({
                title: '操作成功',
                message: "该商品已经从您的购物车删除",
                offset:50
            })
            this.购物车列表.splice(index,1);//从index开始删除1项
            localStorage.tempcart=JSON.stringify(this.购物车列表);//更新本次缓存
            this.$refs.nav_ins.getlocalcart();//更新导航栏购物车
        },
        handleSelectionChange (val) {
            this.check_list = val;
        },
    },
    computed:{
        total_num : function () {
            let num = 0;
            this.check_list.forEach((item)=>{
                    num += (item.number!=0)
                });
                return num
            },
        total_price : function () {
            let sum_price = 0;
            this.check_list.forEach((item)=>{
                sum_price += item.quantity*item.productPrice
            });
            return sum_price.toFixed(2)
            }
        },
    created(){
        this.购物车列表=JSON.parse(localStorage.tempcart);
    },
}

</script>


<style scoped>
/* 本地样式 */
    * {
        text-align: left;
    }
    .hr {
        height: 1px;
        background-color: #ddd;
    }
    .标题 {
        height: 60px;
    }
    #购物车表 {
        width: 90%;
        border: 1px solid #ddd;
        /* height: 100px; */
        /* overflow: scroll; */
        padding: 20px;
        line-height: 60px;
        margin: 0 auto;
    }
    .提交 {
        margin: auto;
        text-align: right;
    }

</style>
