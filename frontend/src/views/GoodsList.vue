<template>
    <div>
        <Navbar ref="nav_ins"></Navbar>
        <div class="主页" style="zoom: 90%">
            <div class="左侧菜单">
                <div class="价格区间">
                    <div class="标题">
                        <h3>价格区间</h3>
                    </div>
                    <div class='hr'></div>
                    <div class="价格区间按钮">
                        <el-slider v-model="PriceRange" range :max="maxbar"></el-slider>
                        <el-input v-model="PriceRange[0]" placeholder="请输入内容" class="range1"></el-input>
                        <el-input v-model="PriceRange[1]" placeholder="请输入内容" class="range2"></el-input>
                    </div>
                </div>
                <div class="商品Tag">
                    <div class="标题">
                        <h3>商品 Tag</h3>
                        <div class='hr'></div>
                        <div class="Tags">
                            <el-tag :key="tag" v-for="tag in dynamicTags" 
                                closable :disable-transitions="false" 
                                @close="handleClose(tag)">
                                {{tag}}
                            </el-tag>
                            <el-input class="input-new-tag" v-if="inputVisible" 
                                v-model="inputValue" ref="saveTagInput" size="small" 
                                @keyup.enter.native="handleInputConfirm" 
                                @blur="handleInputConfirm">
                            </el-input>
                            <el-button v-else class="button-new-tag" size="small" @click="showInput">+新标签</el-button>
                        </div>
                    </div>
                </div>
                <div class="商品类别">
                    <div class="标题">
                        <h3>商品类别</h3>
                        <div class='hr'></div>
                        <el-checkbox-group v-model="checklist" class="checkbox">
                            <p><el-checkbox label="新鲜水果"></el-checkbox></p>
                            <p><el-checkbox label="海鲜水产"></el-checkbox></p>
                            <p><el-checkbox label="精选肉类"></el-checkbox></p>
                            <p><el-checkbox label="冷冻即食"></el-checkbox></p>
                            <p><el-checkbox label="蔬菜蛋品"></el-checkbox></p>
                        </el-checkbox-group>
                    </div>
                </div>
                <div class="筛选搜索">
                    <el-button type="success" round @click="updateproducts(0,0,1)">商品筛选</el-button>
                </div>
                <div class="推荐商品">
                    <div class="标题">
                        <h3>猜你喜欢</h3>
                    </div>
                    <div class='hr'></div>
                    <el-card shadow="hover" class="商品格" v-for="(value,index) in 商品推荐列表" :key="index">
                        <div class="商品图片格">
                            <img :src="value.pictureUrl" width="92%">
                        </div>
                        <div class="商品价格">¥{{value.price.toFixed(2)}}</div>
                        <div class="商品标题">
                            {{value.name}}
                        </div>
                    </el-card>
                </div>
            </div>
            <div class="右侧商品栏">
                <div class="顶部排序栏">
                    <el-button icon="el-icon-bottom" type="success" plain @click="updateproducts(0,getorderrank(0),1)">综合</el-button>
                    <el-button icon="el-icon-sort" type="success" plain @click="updateproducts(1,getorderrank(1),1)">销量</el-button>
                    <el-button icon="el-icon-sort" type="success" plain @click="updateproducts(2,getorderrank(2),1)">价格</el-button>
                    <el-button icon="el-icon-sort" type="success" plain @click="updateproducts(3,getorderrank(3),1)">评分</el-button>
                    <span class="top-page">
                        <el-button type="success" v-if="currentPage!=1" icon="el-icon-arrow-left" @click="tolastpage">上一页</el-button>
                        <el-button type="success" v-if="currentPage*PageNum<searchHits" @click="tonextpage">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
                    </span>
                </div>
                <div class="商品栏">
                <el-card shadow="hover" class="商品格" v-for="(value,index) in 商品列表" :key="index" v-loading="cardloading">
                    <div class="商品图片格" @click="toshop(value.id)">
                        <img :src="value.pictureUrl" width="92%">
                    </div>
                    <div class="销量和价格">
                        <div class="商品价格">¥{{value.price.toFixed(2)}}</div>
                        <div class="商品销量">销量:{{value.salesAmount}}</div>
                    </div>
                    <div class="商品标题">
                        {{value.name}}
                    </div>
                    <div class="计数器">
                        <el-input-number :min="1" size="mini" v-model="productnum[value.id]" placeholder="1"></el-input-number>
                    </div>
                    <div class="按钮栏">
                        <el-button type="warning" @click="addToCart(value.id)">收藏此宝贝</el-button>
                        <el-button type="success" @click="addToCart(value.id)">加入购物车</el-button>
                    </div>
                </el-card>
           
                 </div>
                 <div class="分页栏">
                     <el-pagination
                        background
                        layout="prev, pager, next, jumper"
                        :page-size="PageNum"
                        :current-page.sync="currentPage"
                        :total="searchHits"
                        @current-change="pagechange">
                    </el-pagination>
                 </div>
            </div>
        </div>
        <Bottom ref="bottom_ins"></Bottom>
    </div>
</template>

<script>
const axios = require('axios');
import Navbar from './Navbar.vue'
import Bottom from './Bottom.vue'
export default {
    data :function(){
        return {
            cardloading:true,
            searchHits:0,
            currentorder:0,
            currentPage:1,
            orderrank:[0,0,1,0],
            maxbar:0,
            PageSearch:'',
            PageNum:12,
            TotalPage:10,
            商品列表:[],
            productnum:[],//计数器里的数字
            购物车列表:[],
            商品推荐列表:[],
            PriceRange:[0,86.9],
            dynamicTags: ['新鲜水果', '当季热销','夏日美味','日推','生鲜周周推荐','天天美味','精品上新'],
            inputVisible: false,
            inputValue: '',
            checklist:['新鲜水果','海鲜水产','精选肉类','蔬菜蛋品']
        }
    },
    components:{
        Navbar,
        Bottom
    },
    methods:{
            toshop (id) {
                this.$router.push({ path: '/singleproduct', query: { id: id } })
            },
            tolastpage(){
                this.currentPage--;
                this.pagechange()
            },
            tonextpage(){
                this.currentPage++;
                this.pagechange()
            },
            pagechange(){
                this.updateproducts(this.currentorder,this.orderrank[this.currentorder],this.currentPage);
            },
            getorderrank(id){
                this.orderrank[id]=!this.orderrank[id];
                return this.orderrank[id]+1;
            },
            handleClose(tag) {
                this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
            },

            showInput() {
                this.inputVisible = true;
                this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
                });
            },

            handleInputConfirm() {
                let inputValue = this.inputValue;
                if (inputValue) {
                this.dynamicTags.push(inputValue);
                }
                this.inputVisible = false;
                this.inputValue = '';
            },
        updateproducts(sort,order,page){
            this.cardloading=true
            this.currentorder=sort;
            // alert(this.PageSearch);
            var goodslist=[];
            axios.post("/api/product/search", 
            {
                'product-name': this.PageSearch, 
                'page-num': this.PageNum,
                'sort': sort,
                'order': order,
                'page': page-1,
                'lower-bound' : this.PriceRange[0],
                'upper-bound' : this.PriceRange[1],
            }
            ).then(res => {
                console.log(res.data.data[0]);
                this.商品列表=res.data.data[0];
                for (var i=0;i<this.商品列表.length;i++){
                    this.productnum[this.商品列表[i].id]=1;
                }
                this.maxbar=res.data.data[1].maxPrice;
                this.PriceRange[1]=res.data.data[1].maxPrice;
                this.searchHits=res.data.data[1].searchHit;

                this.$scrollTo()
                this.cardloading=false;
            })
        },
        updaterecommend(){
            var goodslist=[];
            axios.post("/api/product/recommend", {'product-name': this.PageSearch, 'page-num': 4}).then(res => {
                console.log(res.data.data[0]);
                this.商品推荐列表=res.data.data[0];
            })
        },

        parenttest(){
            alert("this is parent")
        },
        navtest(){
            this.$refs.nav_ins.navtest();
        },
        addToCart(productid){
            this.$notify({
                title: '添加成功',
                message: '已将宝贝添加至购物车',
                type: 'success',
                position: 'top-left',
                offset: 60
            })
            var finditem=0;
            for (var i=0;i<this.购物车列表.length;i++){//购物车的第几行
                if (this.购物车列表[i].id===productid){
                    this.productnum[productid];//计数器里的数字
                    this.购物车列表[i].quantity+=this.productnum[productid];
                    finditem=1;
                    break;
                }
            }
            if (!finditem){
                for (var i=0;i<this.商品列表.length;i++){
                    if (this.商品列表[i].id===productid){
                        var pushitem={
                            "id": this.商品列表[i].id,
                            "productPicture" :this.商品列表[i].pictureUrl,
                            "productPrice": this.商品列表[i].price,
                            "productName": this.商品列表[i].name,
                            "quantity": this.productnum[productid]
                        }
                        this.购物车列表.push(pushitem);
                        break;
                    }
                }
            }
            localStorage.tempcart=JSON.stringify(this.购物车列表);//更新本次缓存
            this.$refs.nav_ins.getlocalcart();//更新导航栏购物车
        },
    },
    created(){
        this.PageSearch=this.$route.query.productname;
        if (!localStorage.hasOwnProperty('tempcart')){
            console.log('没有本地购物车缓存,将创建Vue缓存');
            localStorage.tempcart=JSON.stringify(this.购物车列表);
        }
        else{
            this.购物车列表=JSON.parse(localStorage.tempcart);
            // alert('已经找到本地购物车缓存,将同步缓存');
        }
    },
    mounted(){
        this.$refs.nav_ins.searchName=this.PageSearch;
        this.updateproducts(0,0,1);
        this.updaterecommend();
    }
}
</script>

<style scoped>
    .销量和价格{
        width: 100%;
    }
    .top-page{
        float: right;
        /* margin: 5px; */
    }
    .分页栏{
        padding: 30px;
    }
    .价格区间按钮{
        margin: auto;
        width: 87%;
    }
    .筛选搜索{
        width: 80%;
        text-align: right;
    }
    .message-override {
        z-index: 9999999;
    }
    .顶部排序栏{
        padding: 10px;
        text-align: left;
        line-height: 50px;
        background-color: #f1f1f1;
        height: 50px;
        width: 95%;
    }
    .checkbox p{
        height: 20px;
        font-size: 20px;
    }
    .Tags{
        margin-top: 10px;
    }
    .el-tag {
        margin: 5px;
    }
    .button-new-tag {
        margin: 5px;
        height: 32px;
        line-height: 30px;
        padding-top: 0;
        padding-bottom: 0;
    }
    .input-new-tag {
        width: 90px;
        margin: 5px;
        vertical-align: bottom;
    }
    .价格区间 {
        height: 150px;
    }
    .价格区间 .range1{
        width: 30%;
        float:left;
    }
    .价格区间 .range2{
        width: 30%;
        float:right;
    }
    .el-slider{
        margin-top: 10px;
    }
    .推荐商品{
        margin-top: 70px;
    }
    .价格区间,.商品类别,.推荐商品{
        width: 80%;
    }
    .商品Tag{
        width: 90%;
    }
    .标题{
        text-align: left;
    }
    .hr {
        height: 1px;
        background-color: #ddd;
        width: 270px;
    }
    .左侧菜单{
        
        margin-left: 5%;
        float:left;
        width: 22%;
        display:inline;
    }
    .右侧商品栏{
        margin-top: 20px;
        float:left;
        width: 67%;
        display:inline
    }
    .商品栏{
        border: 10px solid #f9f9f9;
        /* height: 1000px; */
        width: 95%;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        /* display: flex;
        flex-direction: column;
        align-items: center; */
    }
    .商品栏 .商品格{
        margin: 15px;
        height: 500px;
        width: 300px;
    }
    .商品栏 .商品格 .商品价格{
        float: left;
        width: 30%;
        margin-top: 15px;
        line-height: 30px;
        font-size: 20px;
        color: red;
        text-indent: 10px;
        text-align: left;
        height: 30px;
    }
    .商品栏 .商品格 .商品销量{
        width: 20%;
        float: right;
        line-height: 30px;
        margin-top: 15px;
        font-size: 15px;
        color: #5f6062;
        text-align: left;
        height: 30px;
    }
    .商品栏 .商品格 .商品标题{
        width: 100%;
        height: 50px;
        overflow-y: scroll;
        display: block;
        padding: 10px;
        margin-bottom: 15px;
        font-size: 15px;
        text-align: left;
    }
    .商品栏 .商品格 .商品图片格{
        display :flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        height: 250px;
        border: 2px solid #f5f5f5;
    }
    .商品栏 .商品格 .计数器{
        height: 45px;
    }

    .商品栏 .商品格 .按钮栏{
        text-align: center;
    }
</style>