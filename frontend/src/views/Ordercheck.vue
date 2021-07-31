<template>
<div>
<el-backtop></el-backtop>
    <div id="订单表">

        <div class="标题">
            <h3>地址</h3>
        </div>
        <div class='hr'></div>
        <div class="收货地址">
            <el-card shadow="hover" class="收货地址卡片" v-for="(item, index) in 收货地址" :key="index" @click.native="selectaddress(index)">
                <el-radio v-model="address" :label="index">
                    <span style="font-size:16px">收货地址{{index+1}}</span> <br>
                    <br>
                    <div class="文字框">
                        收货人: {{item.name}} <br>
                        收货人电话: {{item.phone}} <br>
                        收货地址: {{item.address}} <br>
                    </div>
                </el-radio>
            </el-card>
        </div>


        <div class="标题">
            <h3>支付方式</h3>
        </div>
        <div class='hr'></div>
        <div class="支付方式">
            <el-card shadow="hover" class="支付方式卡片">
                <div>
                    <img src="https://gw.alipayobjects.com/mdn/rms_7e962d/afts/img/A*yCL8TrkjIx8AAAAAAAAAAAAAARQnAQ" style="width:80%">
                </div>
            </el-card>
        </div>


        <div class="标题">
            <h3>订单内容</h3>
        </div>
        <div class='hr'></div>
        <div class="订单内容">
            <el-table :data="订单内容" show-summary :summary-method="getSummaries">
                <el-table-column label="商品图片" header-align=center align="center" width="150">
                    <template slot-scope="scope">
                        <img :src="scope.row.pictureUrl" alt="商品图片" style="width: 80px; height: 80px">
                    </template>
                </el-table-column>
                <el-table-column label="商品名称" header-align=center align="center" prop="name"></el-table-column>
                <el-table-column label="商品单价" header-align=center align="center">
                    <template slot-scope="scope">
                        <strong style="color:red ">{{(scope.row.price).toFixed(2)}}</strong>
                    </template>
                </el-table-column>
                <el-table-column label="商品数量" header-align=center align="center" prop="quantity"></el-table-column>
                <el-table-column label="商品总价" header-align=center align="center">
                    <template slot-scope="scope">
                        <strong style="color:red ">{{scope.row.sum=(scope.row.price*scope.row.quantity).toFixed(2)}}</strong>
                    </template>
            </el-table-column>
            </el-table>
      </div>

      <div class="提交">
        <el-button type="success" @click.native="submitForm()">提交订单</el-button>
      </div>
    </div>
  </div>
</template>

<script>
const axios = require('axios');
export default {
  data: function () {
        return {
            ordId: 0,
            address: 0,
            fucksum: 0,
            收货地址: [],
            订单内容: []
        }
  },
  methods: {
    selectaddress(id){
        this.address=id;
    },
    selectpayway(id){
        this.payway=id;
    },
    submitForm() {
        let receiverId=this.收货地址[this.address].id;
        axios.post("/api/alipay/topay",{
            "orderId":this.ordId,
            "receiverId":receiverId,
            "PaymentType":"支付宝"
            }
        ).then(res=>{
            document.querySelector('body').innerHTML = res.data;
            document.forms[0].submit();
        })
    },
    getsum() {
        var fuck = this.订单内容;
        var sum = 0
        for (var i = 0; i < fuck.length; i++) {
            sum += fuck[i].price * fuck[i].quantity;
        }
        this.fucksum = sum;
        return sum.toFixed(2);
    },
    getSummaries(param) {
        //此处打印param可以看到有两项，一项是columns，一项是data，最后一列可以通过columns.length获取到。
        const { columns, data } = param
        const len = columns.length
        const sums = []
        columns.forEach((column, index) => {
            //如果是第一列，则最后一行展示为“总计”两个字
            if (index === 0) {
            sums[index] = '总计'
            //如果是最后一列，索引为列数-1，则显示计算总和
            } else if (index === len - 1) {
            sums[index] = this.getsum()
            //如果是除了第一列和最后一列的其他列，则显示为空
            } else {
            sums[index] = ''
            }
        })
            return sums
        }
    },
  created() {
      if(localStorage.hasOwnProperty("waitorderid")){
          let orderid=localStorage.waitorderid;
        //   localStorage.removeItem("waitorderid");
          axios.defaults.headers.common['Authorization']=JSON.parse(localStorage.currentuser).token;
          axios.post('http://47.106.193.0:8080/api/order/information',{"orderId":orderid}).then(resp=>{
              console.log(resp.data.data)
              this.订单内容=resp.data.data.orderItem;
              this.收货地址=resp.data.data.receiver;
              this.ordId=resp.data.data.orderId;
          })
      }
      else{
          alert('订单信息传递失败');
          this.$router.push('/myordercenter')
      }
    /*
    axios.get("http://localhost:8181/order/orderinformation").then((myresp) => {
    //   this.收货地址 = myresp.data.address;
    //   this.支付方式 = myresp.data.payway;
      this.订单内容 = myresp.data.cart;
    })
    */
  },
}

</script>


<style scoped>
/* 本地样式 */
.el-radio{
    white-space:normal;
}
.文字框{
    padding-left: 20px;
    line-height: 20px;
}
.收货地址{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    padding-top: 20px;
}
.收货地址卡片{
    margin: 5px;
    width: 300px;
}
.支付方式{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    padding-top: 20px;
}

.支付方式卡片{
    margin: 5px;
    width: 300px;
    height: 110px;
}

* {
  text-align: left;
}
.标题 {
  height: 60px;
}
.hr {
  height: 1px;
  background-color: #ddd;
}
#订单表 {
  width: 90%;
  border: 1px solid #ddd;
  /* height: 100px; */
  /* overflow: scroll; */
  padding: 20px;
  line-height: 60px;
  margin: 0 auto;
}

#订单表 .订单内容 img {
  background-position: center;
  height: 60px;
  width: 60px;
}
#订单表 .提交 {
  margin: auto;
  text-align: center;
}
</style>
