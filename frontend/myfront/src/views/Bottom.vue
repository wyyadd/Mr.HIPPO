<template>
    <div id="底部">
        <div id="透明背景"></div>
        <div id="衔接处">
            <img src="../assets/img/aboutus/curve下.png" width="100%">
        </div>
        <div id="展示区下侧">
            <div id="下侧展示块">
                <div id="展示块图片"><img src="../assets/img/white/leafs-transparent.png" width="90px"></div>
                <div id="展示块文字"><p id="展示块文字上">鲜活天然</p><p id="展示块文字下">绿色生态 河马精选</p></div>
            </div>
            <div id="下侧展示块">
                <div id="展示块图片"><img src="../assets/img/white/leafs-transparent.png" width="90px"></div>
                <div id="展示块文字"><p id="展示块文字上">鲜活天然</p><p id="展示块文字下">绿色生态 河马精选</p></div>
            </div>
            <div id="下侧展示块">
                <div id="展示块图片"><img src="../assets/img/white/leafs-transparent.png" width="90px"></div>
                <div id="展示块文字"><p id="展示块文字上">鲜活天然</p><p id="展示块文字下">绿色生态 河马精选</p></div>
            </div>
            <div id="下侧展示块">
                <div id="展示块图片"><img src="../assets/img/white/leafs-transparent.png" width="90px"></div>
                <div id="展示块文字"><p id="展示块文字上">鲜活天然</p><p id="展示块文字下">绿色生态 河马精选</p></div>
            </div>
        </div>
        <div id="二维码区">
            <img src="../assets/img/二维码/1.jpg" width="140px">
            <img src="../assets/img/二维码/2.jpg" width="140px">
            <img src="../assets/img/二维码/3.jpg" width="140px">
        </div>
    </div>

</template>

<script>
export default {

}
</script>

<style scoped>
#底部{
    width: 100%;
}
    #下侧展示块{
      display: flex;
      flex-direction: row;
      margin-right: 20px;
  }
   #展示区下侧
  {
      margin-top: 30px;
      display: flex;
      flex-direction: row;
  }
  #下侧展示块{
      display: flex;
      flex-direction: row;
      margin-right: 20px;
  }
  #展示块图片
  {
      margin-right: 10px;
  }
  #展示块文字上{
      color: #ffffff;
      font-size: 25px;
      padding-bottom: 5px;
      margin-bottom: 0px;
  }
  #展示块文字下{
    color: #ffffff;
    font-size: 15px;
    margin-top: 0px;
  }

#二维码区 img{
    margin: 20px;
}
#二维码区{
    position: relative;
}

#插图3{
    position: absolute;
    left:-500px;
    top:-1000px;
}
#插图7{
    position: absolute;
    left:-500px;
    top:-2000px;
}
#插图5{
    position: absolute;
    left:800px;
    top:-2500px;
}
#插图4{
    position: absolute;
    left:100px;
    top:4500px;
}
#插图6{
    position: absolute;
    left:800px;
    top:-500px;
}
#插图8{
    position: absolute;
    left:-600px;
    top:-2300px;
}
#底部{
    margin-top: 0px;
    display: flex;
    flex-direction: column;
    align-items: center;
    background: url('../assets/img/aboutus/vegetables-pattern.png');
    background-color: #058203;
}

</style>