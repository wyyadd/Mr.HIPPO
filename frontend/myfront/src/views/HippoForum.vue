<template>
    <div class="主页">
        <div class="导航栏">
            <el-menu
            :default-active="this.$route.path"  
            router>
                <el-menu-item index="/forumhome">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>论坛首页</span>
                    </template>
                </el-menu-item>
                <el-menu-item index="/recommendforum">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>帖子推荐</span>
                    </template>
                </el-menu-item>
                <el-menu-item index="/goodsforum">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>好物推荐</span>
                    </template>
                </el-menu-item>
                <el-menu-item index="/postforum">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>晒出我的</span>
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