<template>
<div class="页面">
    <Navbar ref="nav_ins"></Navbar>
    <div id="商品详情页">
        <div id="商品信息">
            <div id="商品抬头"><h1>{{category_first}}</h1></div>
        </div>
        <div id="商品详情">
            <div id="商品详情容器">
                <div id="图片展示区">
                    <div id="大图展示区">
                        <li id="大图1"><img :src="pictureOne"  width="500px" height="500px" ></li>
                        <li id="大图2"><img :src="pictureTwo" width="500px" height="500px"></li>
                        <li id="大图3"><img :src="pictureThree" width="500px" height="500px"></li>
                        <li id="大图4"><img :src="pictureFour" width="500px" height="500px"></li>
                    </div>
                    <div id="缩略图片区">
                        <a href="javascript:void(0)" @click="change1" id="缩略图片1"><img :src="pictureOne" width="120px" height="120px"></a>
                        <a href="javascript:void(0)" @click="change2" id="缩略图片2"><img :src="pictureTwo" width="120px" height="120px"></a>
                        <a href="javascript:void(0)" @click="change3" id="缩略图片3"><img :src="pictureThree" width="120px" height="120px"></a>
                        <a href="javascript:void(0)" @click="change4" id="缩略图片4"><img :src="pictureFour" width="120px" height="120px"></a>
                    </div>
                </div>
                <div id="商品详情栏" class="商品详情栏">
                        <div id="商品介绍">
                        <div>{{name}}</div>
                        </div>
                        <div class="疫情相关">
                            【本产品已通过核酸检测】河马生鲜，保证您的食品安全，为您的健康保驾护航。
                        </div>
                    <div id="商品其他信息">
                        <el-card shadow="never">
                            <div class="内容标题">
                                <div id="商品价格"><p>河马优价: ￥{{price}}</p></div>
                                <div>商品状态 ：【在售】</div>
                                <div>商品总库存 ：【{{stock}}】件</div>
                                <div>商品总销量 ：【{{saleamount}}】件</div>
                            </div>
                        </el-card>
                    </div>
                        <el-card id="商品详情栏下部容器" class="box-card" shadow="never">
                            <div id="商品详情栏下部">
                                <div id="购买数量"><p>购买数量:</p></div>
                                <div class="计数器">
                                    <el-input-number :min="1" v-model="productnum" placeholder="1"></el-input-number>
                                </div>
                                <div id="加入购物车">
                                    <el-button type="success" @click="addToCart">加入购物车</el-button>
                                </div>
                            </div>
                        </el-card>
                    <el-card class="三图" shadow="never">
                        <div id="三图标">
                            <div id="块">
                                <div id="图标"><img src="../assets/img/orange/card-payment.png"></div>
                                <div id="文字">多种支付方式</div>
                            </div>
                            <div id="块">
                                <div id="图标"><img src="../assets/img/orange/shipped.png"></div>
                                <div id="文字">超快物流保障</div>
                            </div>
                            <div id="块">
                                <div id="图标"><img src="../assets/img/orange/shopping-basket.png"></div>
                                <div id="文字">购物不二选择</div>
                            </div>
                        </div>
                    </el-card>
                </div>
            </div>
        </div>
        <div id="其他信息容器">
         <div id="其他信息">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="商品描述" name="first"><p>{{this.name}}</p></el-tab-pane>
                    <el-tab-pane label="其他信息" name="second">
                        <p>商品总库存 ：{{stock}}<br>
                        商品销量 ：{{saleamount}}<br>
                        </p>
                    </el-tab-pane>
                    <el-tab-pane label="评论" name="third">
                        <div id="评论区">
                            <div :v-if="comment.length!=0" v-for="(o, index) in comment" :key="index" id="单条评论">
                                <div id="评论用户" :v-if="comment.length!=0">
                                    <div id="评论用户头像">
                                        <img :src="comment[index].userAvatar" width="120px" height="120px">
                                    </div>
                                    <div id="评论用户名">
                                        <p>{{comment[index].username}}</p>
                                    </div>
                                </div>
                                <div id="评论内容" :v-if="comment.length!=0">
                                    <div id="评论星数">
                                        <el-rate
                                        v-model="comment[index].star"
                                        disabled
                                        :icon-classes="iconClasses"
                                        void-icon-class="icon-rate-face-off"
                                        :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
                                        </el-rate>
                                    </div>
                                    <div id="评论文字" class='fuck'>
                                        {{comment[index].comment}}
                                    </div>
                                    <div id="评论时间" class='fuck'>
                                        {{comment[index].createTime}}
                                    </div>
                                </div>
                            </div>
                            <div id="写评论">
                                <div id="写评论用户">
                                    <div id="写评论用户头像">
                                        <img v-if="nowuser.avatar" :src="nowuser.avatar" width="120px" height="120px">
                                    </div>
                                    <div id="写评论用户名">
                                        <p>{{nowuser.name}}</p>
                                    </div>
                                </div>
                                <div id="写评论内容">
                                    <div id="写评论星数">
                                        <el-rate
                                            v-model="nowuser.star"
                                            :icon-classes="iconClasses"
                                            void-icon-class="icon-rate-face-off"
                                            :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
                                        </el-rate>
                                    </div>
                                    <div id="写评论文字">
                                       <el-input
                                            type="textarea"
                                            :autosize="{ minRows: 3, maxRows: 3}"
                                            placeholder="请输入评论内容"
                                            v-model="nowuser.comment">
                                        </el-input>
                                    </div>
                                    <div id="发表评论">
                                        <el-button type="primary" @click="submitclick()">发表评论</el-button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </div>
        <div id="相关商品">
            <div id="相关商品标题">
                <div id="标题栏">
                    <p style="font-size:30px"> 相 关 商 品 </p>
                </div>
            </div>
            <div id="相关商品展示区">
                 <div id="单个商品块" v-for="(o,  index ) in products" :key="index">
                    <div id="单个商品图片">
                        <a href="javascript:void(0);" @click="toshop(o.id)">
                            <img :src="o.pictureUrl"  width="150px" height="150px">
                        </a>
                    </div>
                    <div id="单个商品名称">
                        <a href="javascript:void(0);" @click="toshop(o.id)">
                            <p>{{o.name}}</p>
                        </a>
                    </div>
                    <div id="单个商品价格">
                        <a href="javascript:void(0);" @click="toshop(o.id)">
                            <p>
                                ￥{{o.price}}
                            </p>
                        </a>
                        <div id="销量">
                        <p >销量 :{{o.sales_amount}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Bottom ref="bottom_ins"></Bottom>
    </div>
</div>
</template>

<style scoped>
#评论内容 > div{
    font-weight: bold;
    margin: 20px;
}
#写评论内容 > div{
    font-weight: bold;
    margin-top: 20px;
}
.三图{
    margin-top: 50px;
}
.疫情相关{
    color: red;
    margin-top: 10px;
    margin-bottom: 30px;
    font-size: 16px;
    text-align: left;
}
#商品介绍{
    text-align: left;
    font-weight: bold;
}
.内容标题{
    margin-left: 20px;
    font-size: 20px;
    font-weight: bold;
    margin-bottom: 10px;
    text-align: left;
    margin-left: 30px;
}
a{
    text-decoration:none;
    color: black;
}
.计数器{
    margin-top: 10px;
}
.页面{
    margin-left: -10px;
}
/* -----------------------评论区------------- */
#评论区{
}
#单条评论, #写评论{
    display: flex;
    flex-direction: row;
    width: 960px;
    border-bottom-style: solid ;
    margin-top: 30px;
    border-bottom-color: rgb(214, 212, 212);
    border-bottom-width: 1px;
    border-collapse:collapse
}
#评论用户 ,#写评论用户{
    width: 200px;
    height: 180px;
}
#评论用户头像, #写评论用户头像{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
#写评论用户名 p ,#评论用户名 p{
    font-size: 25px;
    text-align: center;
}
#写评论 .el-textarea
{
    width: 760px!important;
}
#评论内容{
    text-align: left;
}
#写评论内容{
    text-align: left;
    height: 250px;
}
#商品抬头{
    height: 180px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: url("../assets/img/inner-bg.jpg");
    background-size: cover;
    background-attachment: fixed;
}
#商品抬头 h1{
    color: white;
    font-size: 40px;
}
#头部{
    display: flex;
    flex-direction: column;
    align-items: center;
}
#商品详情{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
#商品详情容器{
    width: 1200px;
    display: flex;
    flex-direction: row;
    padding-top: 70px;

}
#图片展示区{
    height: 625px;
    width: 500px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}
#商品详情栏{
    width: 700px;
    height: 625px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding-left: 20px;
}
#商品名称{
    width: 290px;
    height: 100px;
    font-size: 30px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
#商品名称 p{
    margin-top: 0px;
    margin-bottom: 0px;
    margin-left: 15px;
}
#商品价格{
    width: 290px;
    height: 70px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
#商品价格 p{

    text-align: left;
    color: red;
    font-weight: bold;
    font-size: 30px;
}
.fuck{
    margin-left: 50px;
}
#商品介绍{
    height: 100px;
}
#商品详情栏下部容器{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: 700px;
}
#购买数量{
    margin-left: 30px;
    font-size: 20px;
    font-weight: bold;
}
#计数器{
    width: 150px;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding-left: 10px;
}
#加入购物车{
    width: 120px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.el-card__body{
    padding: 0px!important;
    display: flex;
    flex-direction: row;
}
#商品详情栏下部{
    width: 650px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
#缩略图片1, #缩略图片2, #缩略图片3, #缩略图片4{
    width: 142px;
    padding-left: 2px;
    padding-right: 2px;
    height: 105px;
}
#缩略图片区{
    display: flex;
    flex-direction: row;
    padding-top: 5px;
}
#大图1, #大图2, #大图3, #大图4{
    display: none;
}
#大图1 {
    display: block;
}

#缩略图片区 button{
    opacity: 0;
}

#其他信息
{
    width: 1200px;
    padding-top: 20px;
    border-style: solid;
    border-color: rgb(223, 220, 220);
    margin-top: 70px;
    border-width: 1px;
}
#其他信息容器{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
#标题栏{
    background: url('https://static.360buyimg.com/mtd/pc/fresh_v3/1.0.0/home/images/fresh_mod_head_bg.png');
      height:200px;
      background-repeat: no-repeat;
      background-position:50% 50% ;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
      width: 1200px;
}
#标题栏 p{
    margin-bottom: 30px;
}
#相关商品展示区{
    height: 280px;
    width: 1200px;
    border-style: solid;
    margin-bottom: 100px;
    display: flex;
    flex-direction: row;
}
#相关商品{
    display: flex;
    flex-direction: column;
    align-items: center;
}
#块{
    margin:20px;
    display: flex;
    flex-direction: row;
}
#块 #文字{
    display: flex;
    flex-direction: column;
    justify-content: center;
    color: gray;
    padding-left: 5px;
}
#三图标{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
#其他信息{
    padding-left: 30px;
}
img{
    object-fit: cover;
}
#图片展示区 img{
     border-style:solid;
    border-width: 2px;
    border-color: rgb(223, 220, 220);
}
#商品介绍 div{
    font-size: 24px;
}

#单个商品块{
    width: 200px;
    height: 280px;
    display: flex;
    flex-direction: column;
}
#单个商品图片{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 12px;
}
#单个商品名称 p{
    padding-left: 20px;
    padding-right: 20px;
    height: 50px;
    overflow: hidden;
    line-height: 25px;
    font-size: 15px;
    margin-bottom: 0px;
}
#单个商品价格 p{
    font-size: 20px;
    color: orange;
    padding-left: 20px;
    padding-right: 20px;
}
#单个商品价格{
    display: flex;
    flex-direction: row;
    align-items: center;
}
#销量 p{
    color: #5e5f61!important;
    font-size: 13px;
}
</style>

<script>
const axios = require('axios');
import Navbar from './Navbar.vue'
import Bottom from './Bottom.vue'
export default {
  data () {
    return {
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'],  
      num: 1,
      productnum:1,
      activeName: 'first',
      id: 3,
      name: '',
      price: '',
      picture: ['', '', '', ''],
      status: '',
      stock: '',
      saleamount: '',
      details: '',
      originplace: '',
      category_first: '',
      pictureOne: '',
      pictureTwo: '',
      pictureThree: '',
      pictureFour: '',
      products: [],
      comment: [],
      购物车列表: [],
      nowuser: {
        name: '',
        avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
        comment: '',
        star: null
      },
      colors: ['#99A9BF', '#F7BA2A', '#FF9900']
    }
  },
  components: {
      Bottom,
      Navbar
  },
  created () {
      this.PageSearch=this.$route.query.productname;
        if (!localStorage.hasOwnProperty('tempcart')){
            console.log('没有本地购物车缓存,将创建Vue缓存');
            localStorage.tempcart=JSON.stringify(this.购物车列表);
        }
        else{
            this.购物车列表=JSON.parse(localStorage.tempcart);
            // alert('已经找到本地购物车缓存,将同步缓存');
        }
    this.id = Number(this.$route.query.id)
    if (localStorage.getItem('userAvatar')!==null){
        this.nowuser.name=JSON.parse(localStorage.getItem('userAvatar')).user.username
        this.nowuser.avatar=JSON.parse(localStorage.getItem('userAvatar')).user.avatar
    }
    if (localStorage.getItem('currentuser') !== null) {
      this.nowuser.name = JSON.parse(localStorage.getItem('currentuser')).username
    }
    axios.post('http://47.106.193.0:8080/api/product/getone', {
      id: this.id
    }).then(res => {
      this.name = res.data.data.name
      this.price = res.data.data.price
      this.saleamount = res.data.data.sales_amount
      this.stock = res.data.data.stock
      this.details = res.data.data.detail
      this.pictureOne = res.data.data.pictureUrlOne
      this.pictureTwo = res.data.data.pictureUrlTwo
      this.pictureThree = res.data.data.pictureUrlThree
      this.pictureFour = res.data.data.pictureUrlFour
      this.category_first = res.data.data.categoryFirst
      this.saleamount=res.data.data.salesAmount
      axios.post('http://47.106.193.0:8080/api/product', {
        pageNum: 6,
        categoryFirst: this.category_first
      }).then(res => {
        this.products = res.data.data
      })
    })
    // this.originplace = res.data.data.originPlace
    axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
    axios.post('http://47.106.193.0:8080/api/product/comment', {
      productId: this.id
    }).then(res => {
      console.log(res.data)
      if (Array.isArray(res.data.data))
        this.comment = res.data.data;
      else{
          this.comment=[];
      }
    })
  },
  methods: {
    handleClick (tab, event) {
      console.log(tab, event)
    },
    change1 () {
      var pic = document.getElementById('大图1')
      pic.style.display = 'block'
      pic = document.getElementById('大图2')
      pic.style.display = 'none'
      pic = document.getElementById('大图3')
      pic.style.display = 'none'
      pic = document.getElementById('大图4')
      pic.style.display = 'none'
    },
    change2 () {
      var pic = document.getElementById('大图2')
      pic.style.display = 'block'
      pic = document.getElementById('大图1')
      pic.style.display = 'none'
      pic = document.getElementById('大图3')
      pic.style.display = 'none'
      pic = document.getElementById('大图4')
      pic.style.display = 'none'
    },
    change3 () {
      var pic = document.getElementById('大图3')
      pic.style.display = 'block'
      pic = document.getElementById('大图2')
      pic.style.display = 'none'
      pic = document.getElementById('大图1')
      pic.style.display = 'none'
      pic = document.getElementById('大图4')
      pic.style.display = 'none'
    },
    change4 () {
      var pic = document.getElementById('大图4')
      pic.style.display = 'block'
      pic = document.getElementById('大图2')
      pic.style.display = 'none'
      pic = document.getElementById('大图3')
      pic.style.display = 'none'
      pic = document.getElementById('大图1')
      pic.style.display = 'none'
    },
    toshop (id) {
      this.$router.push({ path: '/singleproduct', query: { id: id } })
      location.reload()
    },
    submitclick () {
      if (this.nowuser.name === '') {
        this.$notify.error({
          title: '错误',
          message: '请先登录',
          offset:50,
        })
      } else {
        if (this.nowuser.comment === '' || this.nowuser.star === '') {
          this.$notify.error({
            title: '错误',
            message: '请填写完整哦',
            offset:50
          })
        } else {
          axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
          axios.post('http://47.106.193.0:8080/api/user/makecomment', {
            comment: this.nowuser.comment,
            productId: this.id,
            star: this.nowuser.star
          }).then(res => {
            console.log(res.data)
            this.$notify({
              title: '成功',
              message: res.data.msg,
              type: 'success',
              offset:50
            })
            location.reload()
          })
        }
      }
    },
    addToCart(){
        this.$notify({
            title: '添加成功',
            message: '已将宝贝添加至购物车',
            type: 'success',
            position: 'top-left',
            offset: 60
        })
        var finditem=0;
        for (var i=0;i<this.购物车列表.length;i++){//购物车的第几行
            if (this.购物车列表[i].id===this.id){
                this.购物车列表[i].quantity+=this.productnum;
                finditem=1;
                break;
            }
        }
        if (!finditem){
            var pushitem={
                "id": this.id,
                "productPicture" :this.pictureOne,
                "productPrice": this.price,
                "productName": this.name,
                "quantity": this.productnum
            }
            this.购物车列表.push(pushitem);
        }
        localStorage.tempcart=JSON.stringify(this.购物车列表);//更新本次缓存
        this.$refs.nav_ins.getlocalcart();//更新导航栏购物车
    },
  },
  function: {
  }
}

</script>
