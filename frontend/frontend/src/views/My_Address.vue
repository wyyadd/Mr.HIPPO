<template>
    <div>
         <div id="标题">
            <h2>地址信息</h2>
                        </div>
                        <div id="收货地址区">
                            <div id="地址卡片" v-for="o, index in receiver" :key="index">
                                <el-card  class="box-card">
                                    <div slot="header" class="clearfix">
                                        <span>{{receiver[index].name + ' ' + receiver[index].province}}</span>
                                        <el-button style="float: right; padding: 3px 0" type="text" @click="deleteaddress(index)">删除</el-button>
                                    </div>
                                    <div id="地址卡片内容">
                                        <p>{{'&nbsp;&nbsp;&nbsp;&nbsp;收件人：' + receiver[index].name}}</p>
                                        <p>{{'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：' + receiver[index].phone}}</p>
                                        <p>{{'所在地区：'+receiver[index].province+ ' ' + receiver[index].city + ' ' + receiver[index].district}}</p>
                                        <span>{{'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：' +receiver[index].address}}</span>
                                        <el-button style="float: right; padding: 3px 0" type="text" @click="editaddressdialogVisible = true;editindex = index;edit(index)">编辑</el-button>
                                    </div>
                                </el-card>
                            </div>
                            <div id="地址卡片">
                                <el-card id="添加地址容器" class="box-card">
                                    <div slot="header" class="clearfix 加法按钮">
                                        <el-button  class="el-icon-plus" @click="addaddressdialogVisible = true"></el-button>
                                    </div>
                                <!-- <div v-for="o in 4" :key="o" class="text item">
                                    {{'列表内容 ' + o }}
                                </div> -->
                                </el-card>
                            </div>
                            <el-dialog
                                title="添加收货地址"
                                :visible.sync="addaddressdialogVisible"
                                width="30%"
                                class="收货地址位置"
                                >
                                <div>
                                    <div id="添加地址页容器">
                                        <div id="地址条目块">
                                        <p>收件人 :</p><el-input v-model="newreceiver.name" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>收件人电话:</p><el-input v-model="newreceiver.phone" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>省份 :</p><el-input v-model="newreceiver.province" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>城市 :</p><el-input v-model="newreceiver.city" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>区/县 :</p><el-input v-model="newreceiver.district" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>地址 :</p><el-input v-model="newreceiver.address" placeholder="请输入内容"></el-input>
                                        </div>
                                    </div>
                                </div>
                                <span slot="footer" class="dialog-footer">
                                    <el-button @click="addaddressdialogVisible = false">取 消</el-button>
                                    <el-button type="primary" @click="addnewreceiver()" >确 定</el-button>
                                </span>
                            </el-dialog>
                            <el-dialog title="编辑收货地址" :visible.sync="editaddressdialogVisible" width="30%">
                            <div>
                                <div id="编辑地址页容器">
                                <div id="地址条目块">
                                    <p>收件人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p>
                                    <el-input  v-model="editreceiver.name"></el-input>
                                </div>
                                <div id="地址条目块">
                                    <p>收件人电话:</p>
                                    <el-input   v-model="editreceiver.phone"></el-input>
                                </div>
                                <div id="地址条目块">
                                    <p>省份&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p>
                                    <el-input   v-model="editreceiver.province"></el-input>
                                </div>
                                <div id="地址条目块">
                                    <p>城市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p>
                                    <el-input  v-model="editreceiver.city"></el-input>
                                </div>
                                <div id="地址条目块">
                                    <p>区/县&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p>
                                    <el-input  v-model="editreceiver.district"></el-input>
                                </div>
                                <div id="地址条目块">
                                    <p>地址&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p>
                                    <el-input  v-model="editreceiver.address"></el-input>
                                </div>
                                </div>
                            </div>
                            <span slot="footer" class="dialog-footer">
                                <el-button @click="editaddressdialogVisible = false">取 消</el-button>
                                <el-button type="primary" @click="editaddress(editindex)" >确 定</el-button>
                            </span>
                            </el-dialog>
                        </div>
    </div>
</template>

<style scoped>
.收货地址位置{
    position:absolute;
    top:-60px;
    height: 2000px;
}
*{
    text-align: left;
}
.加法按钮{
    text-align: center;
}
/* .添加地址容器{
    text-align: center;
} */
#收货地址区 .clearfix span{
    font-size: 20px;
}
#地址卡片{
    margin: 20px;
}
#地址卡片内容{
    font-size: 15px;
}
</style>

<script>
const axios = require('axios');
export default {
  data () {
    return {
      receiver: [
        {
          name: ' ',
          phone: '',
          province: '',
          city: '',
          district: '',
          address: ''
        }
      ],
      addaddressdialogVisible: false,
      editaddressdialogVisible: false,
      editindex: 0,
      newreceiver: {
        name: '',
        phone: '',
        province: '',
        city: '',
        district: '',
        address: ''
      },
      editreceiver: {
        name: '',
        phone: '',
        province: '',
        city: '',
        district: '',
        address: ''
      }
    }
  },
  created () {
    axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
    axios.post('http://47.106.193.0:8080/api/user/information', {
      header: {
        Authorization: JSON.parse(window.localStorage.getItem('currentuser')).token
      }
    }).then(res => {
      this.receiver = res.data.data.receiver
    })
  },
  methods: {
    deleteaddress (o) {
      axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
      axios.post('http://47.106.193.0:8080/api/receiver/delete', {
        receiverId: this.receiver[o].id
      }).then(res => {
        if (res.data.code === 200) {
          this.$notify({
            title: '成功',
            message: res.data.msg,
            type: 'success',
            offset: 50
    
          })
        } else {
          this.$notify.error({
            title: '错误',
            message: res.data.msg,
            offset: 50
          })
        }
      })
      this.receiver.splice(o, 1)
    },
    addnewreceiver () {
      if (this.newreceiver.name === '' || this.newreceiver.province === '' || this.newreceiver.city === '' ||
      this.newreceiver.phone === '' || this.newreceiver.district === '' || this.newreceiver.address === '') {
        this.$notify.error({
          title: '错误',
          message: '请填写完整',
          offset: 50
        })
      } else {
        axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
        axios.post('http://47.106.193.0:8080/api/receiver/add', {
          name: this.newreceiver.name,
          phone: this.newreceiver.phone,
          province: this.newreceiver.province,
          city: this.newreceiver.city,
          district: this.newreceiver.district,
          address: this.newreceiver.address
        }).then(res => {
          if (res.data.code === 200) {
            this.$notify({
              title: '成功',
              message: res.data.msg,
              type: 'success',
              offset: 50
            })
          } else {
            this.$notify.error({
              title: '错误',
              message: res.data.msg,
              offset: 50
            })
          }
        })
        this.addaddressdialogVisible = false
        this.receiver.push(this.newreceiver)
      }
    },
    editaddress (o) {
      if (this.editreceiver.name === '' || this.editreceiver.province === '' || this.editreceiver.city === '' ||
      this.editreceiver.phone === '' || this.editreceiver.district === '' || this.editreceiver.address === '') {
        this.$notify.error({
          title: '错误',
          message: '请填写完整',
          offset: 50
        })
      } else {
        axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
        axios.post('http://47.106.193.0:8080/api/receiver/delete', {
          receiverId: this.receiver[o].id
        }).then(res => {
        })
        this.receiver.splice(o, 1)
        axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
        axios.post('http://47.106.193.0:8080/api/receiver/add', {
          name: this.editreceiver.name,
          phone: this.editreceiver.phone,
          province: this.editreceiver.province,
          city: this.editreceiver.city,
          district: this.editreceiver.district,
          address: this.editreceiver.address
        }).then(res => {
          if (res.data.code === 200) {
            this.$notify({
              title: '成功',
              message: '修改成功',
              type: 'success',
              offset: 50
            })
          } else {
            this.$notify.error({
              title: '错误',
              message: '修改失败',
              offset: 50
            })
          }
        })
        this.editaddressdialogVisible = false
        this.receiver.push(this.editreceiver)
      }
    },
    edit (o) {
      this.editreceiver = this.receiver[o]
    }
  }
}
</script>
