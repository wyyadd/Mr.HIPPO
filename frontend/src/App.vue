<template>
  <div id="app">
    <Header></Header>
    <router-view/>
    <!-- <div id="nav">
      <router-link to="/hippoforum">HippoForum</router-link> |
      <router-link to="/aaaatest">AAAAtest</router-link> |
      <router-link to="/finalhome">Finalhome</router-link> |
      <router-link to="/goodslist">GoodsList</router-link> |
      <router-link to="/myordercenter">MyOrderCenter</router-link> |
      <router-link to="/login">Login</router-link> |
      <router-link to="/regist">(Regist)</router-link> |
      <router-link to="/testhome">TestHome</router-link> |
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/ordercheck">Ordercheck</router-link> |
      <router-link to="/cart">Cart</router-link> |
      <router-link to="/singleproduct">SingleProduct</router-link> |
      <router-link to="/personalcenter">PersonalCenter</router-link>
    </div> -->
  </div>
</template>

<script>
import Navbar from './views/Navbar.vue'
import Header from './views/Header.vue'
export default {
    components:{
        Navbar,
        Header
    },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
