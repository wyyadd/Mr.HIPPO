<template>
    <div class="主页">
        <div class="导航栏">
            <el-menu
            :default-active="this.$route.path"  
            router>
                <el-submenu index="1" >
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>个人信息</span>
                    </template>
                    <el-menu-item index="/myinformation">我的资料</el-menu-item>
                    <el-menu-item index="/mymodifyinformation">信息修改</el-menu-item>
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>账户设置</span>
                    </template>
                    <el-menu-item index="/mymodifyaccount">账户修改</el-menu-item>
                    <el-menu-item index="/mymodifypassword">密码修改</el-menu-item>
                </el-submenu>
                <el-submenu index="3">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>订单设置</span>
                    </template>
                    <el-menu-item index="/myaddress">地址设置</el-menu-item>
                </el-submenu>
                <el-menu-item index="/myhippo">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>我的河马</span>
                    </template>
                </el-menu-item>
            </el-menu>
        </div>  
        <router-view class='menu-right'  />
    </div>
</template>

<script>
export default {
}
</script>

<style scoped>
    .主页{
        width: 95%;
        margin: auto;
    }
    .导航栏{
        width: 20%;
        float:left;
        display:inline;
    }
    .menu-right{
        margin-left:300px;
    }
    .左侧菜单{
        /* float:left; */
        width: 15%;
        display:inline;
    }

</style>