<template>
    <div class="顶部集合">
        <div class="导航栏">
            <div class="logo">
                <img src="../img/logo.png" alt="Logo">
            </div>
            <div class="search">
                <div style="margin-top: 15px">
                    <el-autocomplete v-model="searchName" :fetch-suggestions="querySearchAsync" placeholder="请输入内容" @select="handleSelect">
                        <el-select v-model="select" slot="prepend" placeholder="商品类别(可选)">
                            <el-option label="新鲜水果" value="1"></el-option>
                            <el-option label="海鲜水产" value="2"></el-option>
                            <el-option label="精选肉类" value="3"></el-option>
                            <el-option label="冷冻即食" value="4"></el-option>
                            <el-option label="蔬菜蛋品" value="4"></el-option>
                        </el-select>
                        <el-button slot="append" icon="el-icon-search" type="success" @click="searchitem"></el-button>
                    </el-autocomplete>
                </div>
            </div>
            <div class="购物车">
                <div class="购物车按钮">
                    <el-button icon="el-icon-goods">我的购物车</el-button>
                    <p class="redtip" v-if="cartnum">{{cartnum}}</p>
                    <el-card class="购物车展开列表">
                        <div class="购物车标题" style="padding-bottom:10px">
                            <strong>最近添加的商品</strong>
                        </div>
                        <div class="购物车列表内容">
                            <el-table :data="购物车列表">
                                <el-table-column label="商品图片" header-align=center align="center" width="90px">
                                    <template slot-scope="scope">
                                        <img :src="scope.row.productPicture" alt="商品图片" style="width: 100%">
                                    </template>
                                </el-table-column>
                                <el-table-column label="名称" header-align=center prop="productName" width="200px">
                                </el-table-column>
                                <el-table-column label="价格" header-align=center align="center" width="80px">
                                    <template slot-scope="scope">
                                        {{scope.row.productPrice}}
                                    </template>
                                </el-table-column>
                                <el-table-column label="数量" header-align=center align="center" prop="quantity" width="80px">
                                </el-table-column>
                            </el-table>
                        </div>
                        <div class="结算按钮栏">
                            <el-button type="success" @click.native="jumpcart">去购物车结算</el-button>
                        </div>
                    </el-card>
                </div>
            </div>
        </div> 
    </div>
</template>

<script>
const axios = require('axios');
export default{
  methods:{
        searchitem(){ 
            this.$router.push({ path: '/goodslist', query: { productname: this.searchName} })
            location.reload();
        },
        querySearchAsync(queryString, cb) {
                axios.post("/api/product/suggestion", {'search': queryString }).then(res => {
                    console.log(res.data);
                    if (res.data.code == 200) {
                        var names=[];
                        console.log(res.data.data.length);
                        for (var i=0;i<res.data.data.length;i++){
                            names[i]={};
                            names[i].value=res.data.data[i];
                        }
                        console.log(names)
                        cb(names);
                    }
                })
        },
        handleSelect(item) {
            console.log(this.searchName, item);
            this.searchitem();
            // alert(this.searchName);
        },
        jumpcart(){
            this.$router.push({path:'/cart'})
        },
        navtest(){
            alert('Nav is called')
        },
        getlocalcart(){
            var tempcart=JSON.parse(localStorage.getItem('tempcart'));
            this.购物车列表=tempcart;
            this.cartnum=tempcart.length;
        }
  },
  data: function (){
      return {
          searchName: '',
          cartnum:0,
          购物车列表:[],
          select: ''
      }
  },
  created(){
    this.getlocalcart();//获取本地购物车数据
  }
}
</script>
<style scoped>
    .顶部集合{
        width: 1300px;
        margin: auto;
    }
    .redtip {
        position: absolute;
        top: 2px;
        right: -8px;
        min-width: 8px;
        height: 20px;
        line-height: 20px;
        margin-top: -11px;
        padding: 0 6px;
        font-weight: normal;
        color: white;
        text-align: center;
        text-shadow: 0 1px rgba(0, 0, 0, 0.2);
        background: #e23442;
        border: 1px solid #911f28;
        border-radius: 11px;
        background-image: -webkit-linear-gradient(top, #e8616c, #dd202f);
        background-image: -moz-linear-gradient(top, #e8616c, #dd202f);
        background-image: -o-linear-gradient(top, #e8616c, #dd202f);
        background-image: linear-gradient(to bottom, #e8616c, #dd202f);
        -webkit-box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.1), 0 1px rgba(0, 0, 0, 0.12);
        box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.1), 0 1px rgba(0, 0, 0, 0.12);
    }


    .导航栏 .el-autocomplete {
        width: 80%;
    }

    .el-select{
        width: 150px
    }

    .导航栏 {
        height: 80px;
    }

    .logo {
        width: 20%;
        float: left;
    }

    .logo img {
        height: 60px
    }

    .search {
        float: left;
        width: 60%;
        height: 100%;
    }

    .购物车 {
        float: left;
        width: 200px;
        height: 100%;
    }

    .购物车按钮{
        position: relative;
        top:12%;
        float :right;
        text-align:right;
        z-index: 1000;
    }
    .购物车按钮 .购物车展开列表{
        display: none;
        text-align: left;
    }
    .购物车按钮:hover .购物车展开列表{
        display: block;
    }
    .购物车列表内容{
        margin-bottom: 10px;
        width: 450px;
        max-height: 300px;
        overflow: scroll;
    }
    .结算按钮栏{
        text-align: right;
    }
</style>