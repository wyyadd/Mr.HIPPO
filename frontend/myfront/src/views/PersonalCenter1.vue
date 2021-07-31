<template>
        <div id="总体">
            <div id="头部"></div>
            <div id="上部">
                <div id="头像区">
                    <div id="头像">
                        <el-upload
                        class="avatar-uploader"
                         action="http://47.106.193.0:8080/upload"
                        :headers="{'Content-Type': 'multipart/form-data'}"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar" >
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                      </el-upload>
                    </div>
                    <div id="上传头像">
                      <a href='#' @click="submitavatar()">上传头像</a>
                    </div>
                    <div id="名字">
                        <p>{{username}}</p>
                    </div>
                </div>
                <div id="钱包区">
                    <div id="钱包区上部">
                        <p>我的钱包</p>
                    </div>
                    <div id="钱包区下部">
                        <div id="优惠券区">
                            <div id="数字"><h1>0</h1></div>
                            <div id="名称"><p>优惠券</p></div>
                        </div>
                        <div id="河马币区">
                            <div id="数字"><h1>0</h1></div>
                            <div id="名称"><p>河马币</p></div>
                        </div>
                        <div id="余额区">
                            <div id="数字"><h1>0.00</h1></div>
                            <div id="名称"><p>余额</p></div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="中部">
                <div id="常购清单">
                    <div id="常购清单上部">
                        <p>常购清单</p>
                    </div>
                    <div id="常购清单下部">
                        <img src='../assets/img/product1.jpg' width="165px" height="125px" alt=''>
                        <img src='../assets/img/product3.jpg' width="165px" height="125px" alt=''>
                    </div>
                </div>
                <div id="日历">
                    <div id="日">{{day}}</div>
                    <div id="星期">{{weekday[weekdaynum]}}</div>
                    <div id="年月">{{year}}&nbsp;{{month+1}}</div>
                </div>
                <div id="生活服务">
                    <div id="生活服务上部">
                        <p>生活服务</p>
                    </div>
                    <div id="生活服务下部">
                        <div id="生活服务块">
                            <img src='../assets/img/图标/game.png' width="60px">
                            <p>游戏</p>
                        </div>
                        <div id="生活服务块">
                            <img src='../assets/img/图标/train.png' width="60px">
                            <p>火车票</p></div>
                        <div id="生活服务块">
                            <img src='../assets/img/图标/oil.png' width="60px">
                            <p>加油</p>
                        </div>
                        <div id="生活服务块">
                            <img src='../assets/img/图标/movie.png' width="60px">
                            <p>电影票</p>
                        </div>
                        <div id="生活服务块">
                            <img src='../assets/img/图标/hotel.png' width="60px">
                            <p>酒店</p>
                        </div>
                        <div id="生活服务块">
                            <img src='../assets/img/图标/plane.png' width="60px">
                            <p>飞机票</p>
                        </div>
                    </div>
                </div>
            </div>
            <div id="下部">
                 <el-tabs tab-position='left' style="height: auto;">
                    <el-tab-pane label="账号信息">
                        <div id="标题">
                            <h2>账号信息</h2>
                        </div>
                        <div id="用户名区">
                            <div id="前缀">用户名 :</div>
                            <el-input id="用户名" placeholder="" v-model="username" readonly="readonly">
                            </el-input>
                        </div>
                        <div id="邮箱区">
                            <div id="前缀">邮箱 &nbsp;&nbsp; :</div>
                            <el-input id="邮箱" placeholder="" v-model="email" readonly="readonly">
                            </el-input>
                        </div>
                        <div id="按钮区">
                            <el-button id="修改信息" @click="changeinfo()">修改信息</el-button>
                            <el-button id="提交修改" type="primary" @click="submitchangeinfo()">提交修改</el-button>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="我的订单">
                        <div id="标题">
                            <h2>订单信息</h2>
                        </div>
                    </el-tab-pane>
                     <el-tab-pane label="我的评论">
                        <div id="标题">
                            <h2>我的评论</h2>
                        </div>
                        <div id="我的评论区">
                          <div v-for="(o, index) in comment" :key="index" id="单条评论">
                            <div id="商品图片">
                              <a href="javascript:void(0);" @click="toshop(comment[index].productId)">
                                <img :src="comment[index].productUrl" width="100px" height="100px">
                              </a>
                            </div>
                            <div id="评论信息">
                              <div id="评论星级">
                                 <el-rate
                                            v-model="comment[index].score"
                                            disabled
                                            show-score
                                            text-color="#ff9900"
                                            score-template="{value}">
                                        </el-rate>
                              </div>
                              <div id="评论内容">
                                 {{comment[index].comment}}
                              </div>
                              <div id="评论时间">
                                 {{comment[index].createTime}}
                              </div>
                            </div>
                          </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="我的地址">
                        <div id="标题">
                            <h2>地址信息</h2>
                        </div>
                        <div id="收货地址区">
                            <el-card v-for="o, index in receiver" :key="index" class="box-card">
                            <div slot="header" class="clearfix">
                                <span>{{'收货地址'+ (index+1)}}</span>
                                <el-button style="float: right; padding: 3px 0" type="text" @click="deleteaddress(index)">删除</el-button>
                                <p>{{'收件人：' +o.name}}</p>
                                <p>{{'收件人电话：' +o.phone}}</p>
                                <p>{{o.province+' '+ o.city +' '+o.district}}</p>
                                <p>{{'地址：' +o.address}}</p>
                            </div>
                        <!-- <div v-for="o in 4" :key="o" class="text item">
                            {{'列表内容 ' + o }}
                        </div> -->
                            </el-card>
                            <el-card id="添加地址容器" class="box-card">
                                <div slot="header" class="clearfix">
                                    <el-button  class="el-icon-plus" @click="dialogVisible = true"></el-button>
                                </div>
                            <!-- <div v-for="o in 4" :key="o" class="text item">
                                {{'列表内容 ' + o }}
                            </div> -->
                            </el-card>
                            <el-dialog
                                title="添加收货地址"
                                :visible.sync="dialogVisible"
                                width="30%"
                                >
                                <div>
                                    <div id="添加地址页容器">
                                        <div id="地址条目块">
                                        <p>收件人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p><el-input v-model="newreceiver.name" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>收件人电话:</p><el-input v-model="newreceiver.phone" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>省份&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p><el-input v-model="newreceiver.province" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>城市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p><el-input v-model="newreceiver.city" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>区/县&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p><el-input v-model="newreceiver.district" placeholder="请输入内容"></el-input>
                                        </div>
                                        <div id="地址条目块">
                                        <p>地址&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</p><el-input v-model="newreceiver.address" placeholder="请输入内容"></el-input>
                                        </div>
                                    </div>
                                </div>
                                <span slot="footer" class="dialog-footer">
                                    <el-button @click="dialogVisible = false">取 消</el-button>
                                    <el-button type="primary" @click="addnewreceiver()" >确 定</el-button>
                                </span>
                            </el-dialog>

                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="修改密码">
                        <div id="标题">
                            <h2>修改密码</h2>
                        </div>
                        <div id="修改密码表单">
 
                            <div id="修改密码页下部">
                                <el-button id="确认修改" @click="changepass()">确认修改</el-button>
                                <el-button id="重置" type="primary" @click="clearout()">重置</el-button>
                            </div>
                        </div>
                    </el-tab-pane>
                </el-tabs>
            </div>
            <Bottom ref="bottom_ins"></Bottom>
        </div>
</template>

<style scoped>
#标题,#按钮区,#地址条目块{
    text-align: left;
}
#上部{
    width: 1200px;
    height: 300px;
    background-color: white;
    display: flex;
    flex-direction: row;
    align-items: center;
}
#背景{
       z-index: -10;
       background-color: aliceblue;
 }
#总体{
    display: flex;
    flex-direction: column;
    align-items: center;
}
#头像{
    margin-top: 40px;
}
#头像区{
    height: 350px;
    width: 310px;
    background-color: aqua;
    box-shadow: 4px 4px 8px #e7e6e6;
}
.avatar-uploader{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
.avatar-uploader .el-upload {
    border: 10px dashed #7a7878;
    border-radius: 50%;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  #头像区 img{
    border-radius: 50%;
    border-width :8px;
    border-color:white ;
}
#名字 p{
    font-size: 35px;
    text-align: center;
}
#头像区{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
#钱包区上部 p{
    font-size: 30px;
    padding-left: 30px;
    padding-right: 30px;

}
#钱包区上部{
    border-bottom:1px solid #c9c7c7;

}
#钱包区下部{
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}
#钱包区{
    display: flex;
    flex-direction: column;
    width: 890px;
}
#钱包区下部 p{
    font-size: 25px;
    text-align: center;
}
#钱包区下部 h1{
    font-size: 50px;
    text-align: center;
}
#中部{
    width: 1200px;
    display: flex;
    flex-direction: row;
    margin-top: 100px;
    margin-bottom: 100px;
}
#常购清单{
    width: 360px;
    margin-right: 30px;
    height: 250px;
    background-color: white;
}
#日历{
    width: 360px;
    height: 250px;
    background-color: white;
    margin-right: 30px;
    margin-left: 30px;
}
#生活服务{
    width: 360px;
    margin-left: 30px;
    height: 250px;
    background-color: white;
}
#常购清单上部{
    border-bottom:1px solid #c9c7c7;
    font-size: 20px;
    padding-left: 20px;
}
#常购清单下部{
    margin-top: 30px;
}
#常购清单下部 img{
    margin-left: 7.5px;
    margin-right: 7.5px;
}
#日历{
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: rgb(61, 255, 190);
}
#日{
    font-size: 130px;
    color: white;
}
#星期{
    color: white;
    font-size: 20px;
}
#年月{
    color: white;
    font-size: 30px;
}
#生活服务上部{
    border-bottom:1px solid #c9c7c7;
    font-size: 20px;
    padding-left: 20px;
}
#生活服务下部{
    height:180px;
    width: 370px;
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
}
#生活服务块{
    height: 89px;
    width: 118px;
    border-style: solid;
    border-width: 1px;
    border-color: #c4c5c7;
}
#生活服务块 p{
    margin: 0px;
    color: #9b8066;
}
#生活服务块{
    display: flex;
    flex-direction: column;
    align-items: center;
}
#下部{
    width: 1200px;
    background-color: white;
    height: auto;
    margin-bottom: 100px;
}
.el-tabs__item{
    height: 80px!important;
    font-size: 25px!important;
}
.el-tabs--left .el-tabs__item.is-left {
    text-align: center!important;
    display: flex!important;
    flex-direction: column!important;
    justify-content: center!important;
    border-bottom:1px solid #42a8fc;
    width: 200px!important;
    background-color: #c4e6fa!important;
}
.el-tabs--left .el-tabs__active-bar.is-left{
    height: 81px!important;
}
.el-tab-pane{
    padding-left: 30px;
}
.el-input{
    width: 200px!important;
}
#用户名区{
    display: flex;
    flex-direction: row;
    margin-top: 20px;
}
#邮箱区{
    display: flex;
    flex-direction: row;
}
#前缀{
    width: 100px;
    height: 65px;
    font-size: 20px;
}
#标题{
    border-bottom:1px solid #c9c7c7;
}
.box-card {
    text-align: left;
    width: 290px;
    margin-left: 15px;
    margin-right: 15px;
    margin-bottom: 20px;
    height: 220px;
  }
  #添加地址容器{
      text-align: left;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
  }
  #收货地址区{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    width: 1010px;
    height: auto;
    padding-top: 20px;
}
#修改密码表单{
    text-align: left;
    margin-top: 30px;
    margin-left: 30px;
}
.el-tabs__content {
    margin-bottom: 50px!important;
}
#单条评论{
  width: 960px;
  display: flex;
  flex-direction: row;
}
#我的评论区 #商品图片{
  margin: 10px;
}
#我的评论区 #评论信息{
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin: 10px;
}
</style>

<script>
const axios = require('axios');
import Navbar from './Navbar.vue'
import Bottom from './Bottom.vue'
export default {
    components:{
        Navbar,
        Bottom
    },
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入现密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入现密码'))
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      imageUrl: '',
      username: '',
      email: '',
      receiver: ['', ''],
      avatar: '',
      dialogVisible: false,
      year: '',
      month: '',
      day: '',
      weekdaynum: '',
      weekday: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
      newreceiver: {
        name: '',
        phone: '',
        province: '',
        city: '',
        district: '',
        address: ''
      },
      ruleForm: {
        pass: '',
        checkPass: '',
        orgpass: ''
      },
      comment: [],
      rules: {
        orgpass: [{ required: true, message: '请输入原密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' },
          { pattern: /^[a-zA-Z0-9\u4e00-\u9fa5]+$/, message: '只能输入汉字、数字、字母', trigger: 'blur' }],
        pass: [
          { validator: validatePass, trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' },
          { pattern: /^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    var t = new Date()
    this.year = t.getFullYear()
    this.month = t.getMonth()
    this.day = t.getDate()
    this.weekdaynum = t.getDay()
    axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
    axios.post('http://47.106.193.0:8080/api/user/information', {
      header: {
        Authorization: JSON.parse(window.localStorage.getItem('currentuser')).token
      }
    }).then(res => {
      this.username = res.data.data.username
      this.email = res.data.data.email
      this.receiver = res.data.data.receiver
      this.imageUrl = res.data.data.avatar
      axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
      axios.post('http://47.106.193.0:8080/api/user/comment/', {
        header: {
          Authorization: JSON.parse(window.localStorage.getItem('currentuser')).token
        }
      }).then(res => {
        this.comment = res.data.data.reverse()
      })
    })
  },
  methods: {
    updataavatar (file) {
      const formData = new FormData()
      formData.append('file', file)
      console.log(formData.get('file'))
      axios.post('http://47.106.193.0:8080/upload', formData, { headers: { 'Content-Type': 'multipart/form-data' } }
      )
        .then(res => {
          alert(res.data.msg)
        })
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
      console.log(file)
      axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
      axios.post('http://47.106.193.0:8080/api/upload', { file: file }
      )
        .then(res => {
          alert(res.data.msg)
        })
      console.log(res)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    deleteaddress (o) {
      axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
      axios.post('http://47.106.193.0:8080/api/receiver/delete', {
        receiverId: this.receiver[o].id
      }).then(res => {
        alert(res.data.msg)
      })
      this.receiver.splice(o, 1)
    },
    clearout () {
      this.ruleForm.pass = ''
      this.ruleForm.checkPass = ''
      this.ruleForm.orgpass = ''
    },
    changepass () {
      axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
      axios.post('http://47.106.193.0:8080/api/user/password', {
        oldPassword: this.ruleForm.orgpass,
        newPassword: this.ruleForm.pass
      }).then(res => {
        alert(res.data.msg)
      })
    },
    addaddress () {
    },
    changeinfo () {
      document.getElementById('用户名').readOnly = 0
      document.getElementById('邮箱').readOnly = false
    },
    submitchangeinfo () {
      axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
      axios.post('http://47.106.193.0:8080/api/user/information/modify', {
        username: this.username,
        email: this.email
      }).then(res => {
        alert(res.data.msg)
      })
    },
    addnewreceiver () {
      if (this.newreceiver.name === '' || this.newreceiver.province === '' || this.newreceiver.city === '' ||
      this.newreceiver.phone === '' || this.newreceiver.district === '' || this.newreceiver.address === '') {
        this.$message.error('请填写完整哦')
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
          alert(res.data.msg)
        })
        this.dialogVisible = false
        this.receiver.push(this.newreceiver)
      }
    },
    submitavatar () {
      const formData = new FormData()
      formData.append('file', this.imageUrl)
      axios.post('http://47.106.193.0:8080/upload', formData, { headers: { 'Content-Type': 'multipart/form-data' } }
      )
        .then(res => {
          alert(res.data.msg)
        })
    },
    toshop (id) {
      this.$router.push({ path: '/singleproduct', query: { id: id } })
    }
  }
}
</script>
