import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import '../theme/index.css'
import vcharts from 'v-charts'
import '@/assets/fonts/style.css'
Vue.use(vcharts)


Vue.use(ElementUI);
document.write ('<body style="margin-left: -10px">');
axios.interceptors.response.use(response => {
    if (response) {
        console.log(response.data.code);
      switch (response.data.code) {
        case 401: //与后台约定登录失效的返回码,根据实际情况处理
        ElementUI.Notification({
            title: '登陆警告',
            message: '登陆失效,请重新登陆',
            type: 'warning',
            offset:50
        });
        localStorage.removeItem('currentuser');
        // 如果超时就处理 ，指定要跳转的页面(比如登陆页)
        router.push('/login')
      }
    }
    return response;
  }, error => {
    return Promise.reject(error.response.data) //返回接口返回的错误信息
  })

Vue.prototype.$scrollTo = (x = 0, y = 0, type = 'smooth') => {
window.scrollTo({
    top: x,
    left: y,
    behavior: type // 滚动行为：smooth平滑滚动，instant瞬间滚动，默认值auto，等同于instant
})
}


import global_ from './Global.vue'
Vue.prototype.GLOBAL = global_;
axios.defaults.baseURL=global_.BASE_URL;
Vue.prototype.$ajax = axios;


new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
router.beforeEach((to, from, next) => {
    /* 路由发生变化修改页面title */
    //   document.title = to.meta.title
      if (to.path === '/login' || to.meta.token_require===false) {
        document.title = to.meta.title
        next();
      } 
      else {
        if (!localStorage.hasOwnProperty('currentuser')){
            ElementUI.Notification({
                title: '警告',
                message: '请先登陆河马生鲜',
                type: 'warning',
                offset:50
            });
            next('/login')
        }
        else{
            next();
            // let token = localStorage.getItem('currentuser')
            // if (token === 'null' || token === '') {
            //     // alert('fuck');
            //     next('/login');
            // } else {
            //     next();
            // }
        }
    }
    next()
  })


import  VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
Vue.use(VueQuillEditor)
