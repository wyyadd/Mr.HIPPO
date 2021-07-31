import Vue from 'vue'
import VueRouter from 'vue-router'
import Ordercheck from '../views/Ordercheck.vue'
import Cart from '../views/Cart.vue'
import Login from '../views/login.vue'
import Regist from '../views/regist.vue'
import GoodsList from '../views/GoodsList.vue'
import MyOrderCenter from '../views/MyOrderCenter.vue'

import HippoForum from '../views/HippoForum.vue'
import ForumHome from '../views/ForumHome.vue'
import PostForum from '../views/PostForum.vue'
import ForumContent from '../views/ForumContent.vue'

import PersonalCenter from '../views/PersonalCenter.vue'
import FinalHome from '../views/FinalHome.vue'
import SingleProcuct from '../views/SingleProduct.vue'

Vue.use(VueRouter)

const routes = [
    {
        path:'/hippoforum',
        name:'HippoForum',
        component :HippoForum,
        meta:{
            title: '河马广场',
            token_require: true
        },
        children:[
            {
                path:'/forumcontent',
                name:'ForumContent',
                component :ForumContent,
                meta:{
                    title: '帖子内容',
                    token_require:false
                }
            },
            {
                path:'/forumhome',
                name:'ForumHome',
                component :ForumHome,
                meta:{
                    title: '论坛首页',
                    token_require:false
                }
            },
            {
                path:'/recommendforum',
                name:'RecommendForum',
                component :ForumHome,
                meta:{
                    title: '每日好贴推荐',
                    token_require:false
                }
            },
            {
                path:'/goodsforum',
                name:'GoodsForum',
                component :ForumHome,
                meta:{
                    title: '每日好物推荐',
                    token_require:false
                }
            },
            {
                path:'/postforum',
                name:'PostForum',
                component : PostForum,
                meta:{
                    title: '晒出我的河马生活～',
                    token_require:true
                }
            },
        ]
    },
    {
        path:'/singleproduct',
        name:'SingleProduct',
        component :SingleProcuct,
        meta:{
            title: '商品详情',
            token_require:false
        }
    },
    {
        path: '/personalcenter',
        name :'PersonalCenter',
        component: PersonalCenter,
        meta:{
            title: '我的个人中心',
            token_require:true
        },
        children:[
            {
                path: '/myaddress',
                name: 'MyAddress',
                component: () => import('../views/My_Address.vue')
            }, 
            {
                path: '/myinformation',
                name: 'MyInformation',
                component: () => import('../views/My_Information.vue')
            }, 
            {
                path: '/mymodifyaccount',
                name: 'MyModifyAccount',
                component: () => import('../views/My_ModifyAccount.vue')
            }, 
            {
                path: '/mymodifyinformation',
                name: 'MyModifyInformation',
                component: () => import('../views/My_ModifyInformation.vue')
            },
            {
                path: '/mymodifypassword',
                name: 'MyModifyPassWord',
                component: () => import('../views/My_ModifyPassword.vue')
            },  
            {
                path: '/myhippo',
                name: 'MyHippo',
                component: () => import('../views/My_Hippo.vue')
            }
        ]
    },
    {
        path: '/aaaatest',
        name: 'AAAAtest',
        component: () => import('../views/AAAAtest.vue')
    },  
    {
        path: '/',
        name: 'Home',
        component: FinalHome,
        meta:{
            title: '商场首页',
            token_require:false
        }
    },
    {
        path: '/about',
        name: 'About',
        component: () => import( /* webpackChunkName: "about" */ '../views/About.vue'),
        meta:{
            title: '关于我们'
        }
    },
    {
        path: "/ordercheck",
        name: 'Ordercheck',
        component: Ordercheck,
        meta:{
            title: '订单结算',
            token_require:true
        }
    },
    {
        path: "/cart",
        name: 'Cart',
        component: Cart,
        meta:{
            title: '我的购物车',
            token_require:true,
        }
    },
    {
        path: "/login",
        name: 'Login',
        component: Login,
        meta:{
            title: '登陆',
            token_require:false
        }
    },
    {
        path: "/regist",
        name : 'Regist',
        component: Regist,
        meta:{
            title: '账户注册',
            token_require:false
        }
    },
    {
        path: "/finalhome",
        name : 'FinalHome',
        component: FinalHome,
        meta:{
            title: '首页',
            token_require:false
        }
    },
    {
        path: "/goodslist",
        name : 'GoodsList',
        component: GoodsList,
        meta:{
            title: '商品搜索结果',
            token_require:false
        }
    },
    {
        path : "/myordercenter",
        name : 'MyOrderCenter',
        component : MyOrderCenter,
        meta:{
            title: '我的订单中心',
            token_require:true
        },
        children:[
            {
                path : "/myorder",
                name : 'MyOrder',
                component: () => import( /* webpackChunkName: "about" */ '../views/MY_Order.vue'),
                meta:{
                    title: '我的订单'
                },
            },
            {
                path : "/mycomment",
                name : 'MyComment',
                component: () => import( /* webpackChunkName: "about" */ '../views/MY_Comment.vue'),
                meta:{
                    title: '我的评论'
                },
            }
        ]
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
    scrollBehavior (to, from, savedPosition) {
        if (savedPosition) {
          return savedPosition
        } else {
          return { x: 0, y: 0 }
        }
      }
})

export default router