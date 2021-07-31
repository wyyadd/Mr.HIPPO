<template>
    <div>
            <el-upload
            action="#"
            :auto-upload='false'
            :on-change="handleChange"
            :show-file-list="false"
            :limit="1"
            >
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-button @click="uploadimg">
                点击上传
            </el-button>
    </div>

<!-- <div id="收货地址区">
    <el-card v-for="o, index in receiver" :key="index" class="box-card">
    <div slot="header" class="clearfix">
        <span>{{'收货地址'+ (index+1)}}</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="deleteaddress(index)">删除</el-button>
        <p>{{'收件人：' +o.name}}</p>
        <p>{{'收件人电话：' +o.phone}}</p>
        <p>{{o.province+' '+ o.city +' '+o.district}}</p>
        <p>{{'地址：' +o.address}}</p>
    </div>
    </el-card>
    <el-card id="添加地址容器" class="box-card">
        <div slot="header" class="clearfix">
            <el-button  class="el-icon-plus" @click="dialogVisible = true"></el-button>
        </div>
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
</div> -->

</template>
<script>
import axios from 'axios';
  export default {
    data() {
      return {
            fileList:[],
            imageUrl: '',
            dialogVisible:false,
            receiver:['',''],
            searchName: '',
            newreceiver: {
                name:'',
                phone:'',
                province:'',
                city:'',
                district:'',
                address:''
            },
            restaurants: [],
            state1: '',
            state2: '',
            fucktest:[
                {value:"苹果1"},
                {alue:"苹果2"},
                {name:"苹果富士康"},
                {name:"富士康苹果"}
                ]
      };
    },
    methods: {
        handleChange(file,fileList){
            this.fileList=fileList
            this.imageUrl=URL.createObjectURL(file.raw)
        },
        uploadimg(){
            const formData = new FormData();
            formData.set('file', this.fileList[0].raw);
            axios.defaults.headers.common['Authorization']=JSON.parse(localStorage.currentuser).token;
            axios.post("/api/user/upload",formData).then(rep=>{
                console.log(rep.data)
            })
        },
        addnewreceiver () {
      if (this.newreceiver.name === '' || this.newreceiver.province === '' || this.newreceiver.city === '' ||
      this.newreceiver.phone === '' || this.newreceiver.district === '' || this.newreceiver.address === '') {
        this.$message.error('请填写完整哦')
      } else {
        this.$axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
        this.$axios.post('http://47.106.193.0:8080/api/receiver/add', {
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
        deleteaddress (o) {
      this.$axios.defaults.headers.common.Authorization = JSON.parse(localStorage.userdata).token
      this.$axios.post('http://47.106.193.0:8080/api/receiver/delete', {
        receiverId: this.receiver[o].id
      }).then(res => {
        alert(res.data.msg)
      })
      this.receiver.splice(o, 1)
        },
        querySearchAsync(queryString, cb) {
            let params = { name: queryString }
            cb(this.fucktest);
        },
        handleSelect(item) {
            console.log(this.searchName, item);
        },
    },
  }
</script>
<style scoped>
  #添加地址容器{
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
</style>