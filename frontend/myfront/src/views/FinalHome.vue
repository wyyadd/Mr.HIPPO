<template>
    <div class="页面">
        <div class="头部">
            <Navbar ref="nav_ins"></Navbar>
        </div>
        <div class="正文" style="zoom: 90%">
            <div id="展示区" >
                <div id="走马灯展示区">
                    <div id="左侧分类栏">
                        <div id="分类栏头">全部商品分类</div>
                        <div id="分类栏表">
                            <ul>
                                <li id="li1">
                                    <img src="../assets/img/分类小图标/sprite_fresh1.png" width="30px" height="30px">
                                    <a href="#" style="color:black">新鲜水果</a><i>></i>
                                    <div id="右侧拉出栏1">
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a  href="/goodslist?productname=水果">热销水果 ></a></div>
                                            <!-- <a href="#"> 苹果 |</a>
                                            <a href="#"> 葡萄 |</a>
                                            <a href="#"> 提子 |</a>
                                            <a href="#"> 奇异果 |</a>
                                            <a href="#"> 猕猴桃 |</a>
                                            <a href="#"> 橙子 |</a>
                                            <a href="#"> 梨 |</a>
                                            <a href="#"> 蓝莓 |</a>
                                            <a href="#"> 柠檬 |</a>
                                            <a href="#"> 百香果 |</a>
                                            <a href="#"> 火龙果 |</a>
                                            <a href="#"> 椰青 |</a>
                                            <a href="#"> 西柚 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=时令水果">时令水果 ></a></div>
                                            <!-- <a href="#"> 柚子 |</a>
                                            <a href="#"> 冬枣 |</a>
                                            <a href="#"> 车厘子 |</a>
                                            <a href="#"> 奇异果 |</a>
                                            <a href="#"> 樱桃 |</a>
                                            <a href="#"> 水蜜桃 |</a>
                                            <a href="#"> 杏 |</a>
                                            <a href="#"> 李子 |</a>
                                            <a href="#"> 香瓜 |</a>
                                            <a href="#"> 哈密瓜 |</a>
                                            <a href="#"> 西瓜 |</a>
                                            <a href="#"> 枇杷 |</a>
                                            <a href="#"> 杨梅 |</a>
                                            <a href="#"> 草莓 |</a>
                                            <a href="#"> 鲜槟榔 |</a>
                                            <a href="#"> 金桔 |</a>
                                            <a href="#"> 甘蔗 |</a>
                                            <a href="#"> 柿子 |</a>
                                            <a href="#"> 无花果 |</a>
                                            <a href="#"> 石榴 |</a>
                                            <a href="#"> 橘柑 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=热带水果">热带鲜果 ></a></div>
                                            <!-- <a href="#"> 芒果 |</a>
                                            <a href="#"> 菠萝 |</a>
                                            <a href="#"> 凤梨 |</a>
                                            <a href="#"> 榴莲 |</a>
                                            <a href="#"> 香蕉 |</a>
                                            <a href="#"> 百香果 |</a>
                                            <a href="#"> 山竹 |</a>
                                            <a href="#"> 木瓜 |</a>
                                            <a href="#"> 释迦 |</a>
                                            <a href="#"> 龙眼 |</a>
                                            <a href="#"> 牛油果 |</a>
                                            <a href="#"> 菠萝蜜 |</a>
                                            <a href="#"> 椰子 |</a>
                                            <a href="#"> 火龙果  |</a>
                                            <a href="#"> 莲雾 |</a>
                                            <a href="#"> 番石榴 |</a>
                                            <a href="#"> 芭乐 |</a>
                                            <a href="#"> 龙宫果 |</a>
                                            <a href="#"> 火参果 |</a>
                                            <a href="#"> 杨桃 |</a>
                                            <a href="#"> 红毛丹</a> -->
                                        </div>
                                    </div>
                                </li>
                                <li id="li2">
                                    <img src="../assets/img/分类小图标/sprite_fresh2.png" width="30px" height="30px">
                                    <a href="#" style="color:black">海鲜水产</a><i>></i>
                                    <div id="右侧拉出栏2">
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a  href="/goodslist?productname=鱼类">鱼类 ></a></div>
                                           <!--  <a href="#"> 三文鱼 |</a>
                                            <a href="#"> 鳕鱼 |</a>
                                            <a href="#"> 提子 |</a>
                                            <a href="#"> 带鱼 |</a>
                                            <a href="#"> 黄鱼 |</a>
                                            <a href="#"> 黄花鱼 |</a>
                                            <a href="#"> 金枪鱼 |</a>
                                            <a href="#"> 巴沙鱼 |</a>
                                            <a href="#"> 鲳鱼 |</a>
                                            <a href="#"> 鲈鱼 |</a>
                                            <a href="#"> 鳗鱼 |</a>
                                            <a href="#"> 鲽鱼 |</a>
                                            <a href="#"> 胖头鱼 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=虾类">虾类 ></a></div>
                                            <!-- <a href="#"> 红虾 |</a>
                                            <a href="#"> 白虾 |</a>
                                            <a href="#"> 虾仁 |</a>
                                            <a href="#"> 北极甜虾 |</a>
                                            <a href="#"> 黑虎虾 |</a>
                                            <a href="#"> 大龙虾 |</a>
                                            <a href="#"> 小龙虾 |</a>
                                            <a href="#"> 竹节虾 |</a>
                                            <a href="#"> 皮皮虾 |</a>
                                            <a href="#"> 基围虾 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=蟹类"> 蟹类 ></a></div>
                                           <!--  <a href="#"> 大闸蟹 |</a>
                                            <a href="#"> 帝王蟹 |</a>
                                            <a href="#"> 梭子蟹 |</a>
                                            <a href="#"> 面包蟹 |</a>
                                            <a href="#"> 黄金蟹 |</a>
                                            <a href="#"> 青蟹 |</a>
                                            <a href="#"> 雪蟹 |</a>
                                            <a href="#"> 珍宝蟹 |</a>
                                            <a href="#"> 红毛蟹 |</a>
                                            <a href="#"> 醉蟹 |</a>
                                            <a href="#"> 香辣蟹 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=贝类"> 贝类 ></a></div>
                                            <!-- <a href="#"> 生蚝 |</a>
                                            <a href="#"> 鲍鱼 |</a>
                                            <a href="#"> 扇贝 |</a>
                                            <a href="#"> 蛤 |</a>
                                            <a href="#"> 蚬子 |</a>
                                            <a href="#"> 螺 |</a>
                                            <a href="#"> 蛏子 |</a>
                                            <a href="#"> 北极贝 |</a>
                                            <a href="#"> 蚌 |</a>
                                            <a href="#"> 贝柱 </a> -->
                                        </div>
                                    </div>
                                </li>
                                <li id="li3">
                                    <img src="../assets/img/分类小图标/sprite_fresh3.png" width="30px" height="30px">
                                    <a href="#" style="color:black">精选肉类</a><i>></i>
                                    <div id="右侧拉出栏3">
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a  href="/goodslist?productname=猪肉">猪肉 ></a></div>
                                            <!-- <a href="#"> 肋排 |</a>
                                            <a href="#"> 猪蹄 |</a>
                                            <a href="#"> 提子 |</a>
                                            <a href="#"> 五花肉 |</a>
                                            <a href="#"> 汤骨 |</a>
                                            <a href="#"> 棒骨 |</a>
                                            <a href="#"> 猪肘 |</a>
                                            <a href="#"> 梅花肉片 |</a>
                                            <a href="#"> 猪排 |</a>
                                            <a href="#"> 猪扒 |</a>
                                            <a href="#"> 里脊 |</a>
                                            <a href="#"> 猪肉馅 |</a>
                                            <a href="#"> 脊骨 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=牛肉">牛肉 ></a></div>
                                           <!--  <a href="#"> 牛排 |</a>
                                            <a href="#"> 牛腩 |</a>
                                            <a href="#"> 牛腱子 |</a>
                                            <a href="#"> 牛里脊 |</a>
                                            <a href="#"> 牛肉片 |</a>
                                            <a href="#"> 卷牛尾 |</a>
                                            <a href="#"> 牛肋条 |</a>
                                            <a href="#"> 牛蹄筋 |</a>
                                            <a href="#"> 牛肉串 |</a>
                                            <a href="#"> 牛骨 |</a>
                                            <a href="#"> 筋头巴脑 |</a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=羊肉">羊肉 ></a></div>
                                            <!-- <a href="#"> 羊肋排 |</a>
                                            <a href="#"> 羊肉片 |</a>
                                            <a href="#"> 卷羊腿 |</a>
                                            <a href="#"> 羊蝎子 |</a>
                                            <a href="#"> 羊肉串 |</a>
                                            <a href="#"> 羊肉块 |</a>
                                            <a href="#"> 整羊 |</a>
                                            <a href="#"> 羊杂 |</a>
                                            <a href="#"> 西式羊排 |</a>
                                            <a href="#"> 羊肉馅 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=鸡肉">鸡肉 ></a></div>
                                          <!--   <a href="#"> 鸡翅 |</a>
                                            <a href="#"> 整鸡 |</a>
                                            <a href="#"> 鸡胸肉 |</a>
                                            <a href="#"> 鸡腿 |</a>
                                            <a href="#"> 鸡爪 |</a>
                                            <a href="#"> 鸡肉块 |</a>
                                            <a href="#"> 鸡胗 |</a>
                                            <a href="#"> 鸡肫 |</a>
                                            <a href="#"> 鸡柳 |</a>
                                            <a href="#"> 鸡排 |</a>
                                            <a href="#"> 柴鸡 |</a>
                                            <a href="#"> 老母鸡 |</a>
                                            <a href="#"> 大公鸡 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=鸭肉">鸭肉 ></a></div>
                                           <!--  <a href="#"> 鸭腿 |</a>
                                            <a href="#"> 鸭胸 |</a>
                                            <a href="#"> 鸭肫 |</a>
                                            <a href="#"> 鸭胗 |</a>
                                            <a href="#"> 鸭掌 |</a>
                                            <a href="#"> 鸭脖 |</a>
                                            <a href="#"> 鸭舌 |</a>
                                            <a href="#"> 鸭翅 |</a>
                                            <a href="#"> 老鸭 </a> -->
                                        </div>
                                    </div>
                                </li>
                                <li id="li4">
                                    <img src="../assets/img/分类小图标/sprite_fresh4.png" width="30px" height="30px">
                                    <a href="#" style="color:black">冷饮冻食</a><i>></i>
                                    <div id="右侧拉出栏4">
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a  href="/goodslist?productname=面点">面点 ></a></div>
                                          <!--   <a href="#"> 手抓饼 |</a>
                                            <a href="#"> 水饺 |</a>
                                            <a href="#"> 云吞 |</a>
                                            <a href="#"> 汤圆 |</a>
                                            <a href="#"> 元宵 |</a>
                                            <a href="#"> 包子 |</a>
                                            <a href="#"> 馒头 |</a>
                                            <a href="#"> 面条 |</a>
                                            <a href="#"> 年糕 |</a>
                                            <a href="#"> 月饼 |</a>
                                            <a href="#"> 烧麦 |</a>
                                            <a href="#"> 肠粉 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=方便速食">方便速食 ></a></div>
                                          <!--   <a href="#"> 方便菜 |</a>
                                            <a href="#"> 方便面 |</a>
                                            <a href="#"> 方便米饭 |</a>
                                            <a href="#"> 培根 |</a>
                                            <a href="#"> 火腿 |</a>
                                            <a href="#"> 薯条 |</a>
                                            <a href="#"> 鸡米花 |</a>
                                            <a href="#"> 鸡肉块 |</a>
                                            <a href="#"> 鸡柳 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=火锅丸料"> 火锅丸料 ></a></div>
                                            <!-- <a href="#"> 牛肉丸 |</a>
                                            <a href="#"> 鱼豆腐 |</a>
                                            <a href="#"> 鱼丸 |</a>
                                            <a href="#"> 火锅肠 |</a>
                                            <a href="#"> 墨鱼丸 |</a>
                                            <a href="#"> 火锅饺 |</a>
                                            <a href="#"> 包心丸 |</a>
                                            <a href="#"> 火锅料 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=烘焙食材"> 烘焙食材 ></a></div>
                                            <!-- <a href="#"> 奶酪 |</a>
                                            <a href="#"> 黄油 |</a>
                                            <a href="#"> 芝士 |</a>
                                            <a href="#"> 淡奶油 |</a>
                                            <a href="#"> 蛋挞 </a> -->
                                        </div>
                                    </div>
                                </li>
                                <li id="li5">
                                    <img src="../assets/img/分类小图标/sprite_fresh5.png" width="30px" height="30px">
                                    <a href="#" style="color:black">蔬菜蛋品</a><i>></i>
                                    <div id="右侧拉出栏5">
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a  href="/goodslist?productname=叶菜类">叶菜类 ></a></div>
                                           <!--  <a href="#"> 生菜 |</a>
                                            <a href="#"> 菜心 |</a>
                                            <a href="#"> 西兰花 |</a>
                                            <a href="#"> 菠菜 |</a>
                                            <a href="#"> 上海青 |</a>
                                            <a href="#"> 油菜 |</a>
                                            <a href="#"> 芥蓝 |</a>
                                            <a href="#"> 白菜 |</a>
                                            <a href="#"> 甘蓝 |</a>
                                            <a href="#"> 卷心菜 |</a>
                                            <a href="#"> 芹菜 |</a>
                                            <a href="#"> 韭菜 |</a>
                                            <a href="#"> 茼蒿 |</a>
                                            <a href="#"> 香菜 |</a>
                                            <a href="#"> 茴香 |</a>
                                            <a href="#"> 芝麻菜 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=根茎类">根茎类 ></a></div>
                                            <!-- <a href="#"> 山药 |</a>
                                            <a href="#"> 萝卜 |</a>
                                            <a href="#"> 地瓜 |</a>
                                            <a href="#"> 莲藕 |</a>
                                            <a href="#"> 土豆 |</a>
                                            <a href="#"> 芋头 |</a>
                                            <a href="#"> 百合 |</a>
                                            <a href="#"> 芦笋 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=菌菇"> 鲜菌菇 ></a></div>
                                           <!--  <a href="#"> 香菇 |</a>
                                            <a href="#"> 松茸 |</a>
                                            <a href="#"> 鸡枞菌 |</a>
                                            <a href="#"> 牛肝菌 |</a>
                                            <a href="#"> 杏鲍菇 |</a>
                                            <a href="#"> 木耳 |</a>
                                            <a href="#"> 银耳 |</a>
                                            <a href="#"> 黑松露菌 |</a>
                                            <a href="#"> 平菇 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=茄果瓜类"> 茄果瓜类 ></a></div>
                                           <!--  <a href="#"> 玉米 |</a>
                                            <a href="#"> 黄瓜 |</a>
                                            <a href="#"> 西红柿 |</a>
                                            <a href="#"> 南瓜 |</a>
                                            <a href="#"> 茄子 |</a>
                                            <a href="#"> 四季豆 |</a>
                                            <a href="#"> 苦瓜 |</a>
                                            <a href="#"> 豇豆 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=葱姜蒜椒">葱姜蒜椒 ></a></div>
                                            <!-- <a href="#"> 葱 |</a>
                                            <a href="#"> 姜 |</a>
                                            <a href="#"> 大蒜 |</a>
                                            <a href="#"> 花椒 |</a>
                                            <a href="#"> 红辣椒 |</a>
                                            <a href="#"> 青椒 </a> -->
                                        </div>
                                        <div id="右拉菜单">
                                            <div id="小抬头"><a id="小抬头" href="/goodslist?productname=半加工">半加工菜 ></a></div>
                                           <!--  <a href="#"> 泡菜 |</a>
                                            <a href="#"> 沙拉菜 |</a>
                                            <a href="#"> 酸菜 </a> -->
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div id="插图1"><img src="../assets/img/fruits-plate.png" width="250px"></div>
                    </div>
                    <div id="中部走马灯">
                            <el-carousel height="400px">
                            <el-carousel-item v-for="item in pictures" :key="item">
                                <h3 class="small"><img :src=item></h3>
                            </el-carousel-item>
                            </el-carousel>
                    </div>
                    <div id="右侧头条广告">
                        <div id="右侧头条">
                            <div id="头条标题"><h3 align="center">—河马头条—</h3></div>
                            <div id="头条内容">
                                <p align="center"><a href="https://top100.jd.com/">精挑细选100分营养美味</a></p>
                                <p align="center"><a href="https://top100.jd.com/">0元试吃 全新上线 抓紧尝鲜</a></p>
                                <p align="center"><a href="https://top100.jd.com/">一顿立享 抓紧做顿好饭</a></p>
                            </div>
                        </div>
                        <div id="右侧广告">
                            <img src="https://m.360buyimg.com/babel/jfs/t1/196248/22/12553/67690/60ebacecEd9f907d2/f3993b3e216c7676.png" width="242x">
                        </div>
                    </div>
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
            </div>
            <div id="秒杀区">
                <div id="标题栏">
                    <p style="font-size:30px"> 河 / 马 / 秒 / 杀 </p>
                    <p>FLASH SALE</p>
                </div>
                <div id="秒杀区商品容器">
                    <div id="秒杀单个商品" v-for="o, index in discountproduct" :key="index">
                        <div id="秒杀单个商品图片">
                            <a href="javascript:void(0);" @click="toshop(discountproduct[index].id)">
                                <img :src="discountproduct[index].pictureUrl" width="200px">
                            </a>
                        </div>
                        <div id="秒杀单个商品名称">
                            <a href="javascript:void(0);" @click="toshop(discountproduct[index].id)">
                                <p>{{discountproduct[index].name}}</p>
                            </a>
                        </div>
                        <div id="秒杀单个商品价格区">
                            <div id="秒杀单个商品价格">
                                <div id="秒杀最低价">
                                    <a href="javascript:void(0);" @click="toshop(discountproduct[index].id)">
                                        <p>近{{discountproduct[index].originPrice.toFixed(0)}}天最低价</p>
                                    </a>
                                </div>
                                <div id="秒杀现价">
                                    <a href="javascript:void(0);" @click="toshop(discountproduct[index].id)">
                                        <p>￥{{discountproduct[index].currentPrice.toFixed(2)}}</p>
                                    </a>
                                </div>
                                <div id="秒杀余量">
                                    <p>已售</p>
                                    <div id="秒杀进度">
                                        <el-progress :text-inside="true" :stroke-width="30" :percentage="discountproduct[index].percentage" status="warning"></el-progress>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="推荐区">
                <div id="标题栏">
                    <p style="font-size:30px"> 身 / 临 / 食 / 感 </p>
                    <p>INSPIRATION FRESH</p>
                </div>
                <div id="推荐区导航">
                    <el-tabs id="推荐区导航栏" v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane id ="推荐区块" label="精选礼盒" name="first">
                            <div id="推荐区文字">
                                <p style="font-size:25px">精选礼盒</p>
                                <p>送礼送健康，精选生鲜礼盒</p>
                            </div>
                            <div id="推荐商品区块">
                                <div id="推荐商品区">
                                    <div id="单个商品块" v-for="o, index in products2" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products2[index].id)">
                                                <img :src="products2[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products2[index].id)"><p>{{products2[index].name}}</p></a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products2[index].id)">
                                                <p>
                                                ￥{{products2[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                                <p >销量 :{{products2[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <img id ="插图2" src="https://img14.360buyimg.com/cms/jfs/t7465/58/1681827025/166530/5a524b9e/599e7853N7bbe1ce0.png" alt=''>
                        </el-tab-pane>
                        <el-tab-pane id ="推荐区块" label="火锅诱惑" name="second">
                            <div id="推荐区文字">
                                <p style="font-size:25px">火锅诱惑</p>
                                <p>送礼送健康，精选生鲜礼盒</p>
                            </div>
                            <div id="推荐商品区块">
                                <div id="推荐商品区" >
                                    <div id="单个商品块" v-for="o, index in products1" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products1[index].id)">
                                                <img :src="products1[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products1[index].id)">
                                                <p>{{products1[index].name}}</p>
                                            </a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products1[index].id)">
                                                <p>
                                                    ￥{{products1[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                            <p >销量 :{{products1[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <img id ="插图2" src="https://img12.360buyimg.com/cms/jfs/t7537/125/1647183785/185951/b5235b8b/599e781dN93a73aad.png" alt=''>
                        </el-tab-pane>
                        <el-tab-pane id ="推荐区块" label="深夜食堂" name="third">
                            <div id="推荐区文字">
                                <p style="font-size:25px">深夜食堂</p>
                                <p>送礼送健康，精选生鲜礼盒</p>
                            </div>
                            <div id="推荐商品区块">
                                <div id="推荐商品区">
                                    <div id="单个商品块" v-for="o, index in products3" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products3[index].id)">
                                                <img :src="products3[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products3[index].id)">
                                                <p>{{products3[index].name}}</p>
                                            </a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products3[index].id)">
                                                <p>
                                                    ￥{{products3[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                                <p >销量 :{{products3[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <img id ="插图2" src="https://img12.360buyimg.com/cms/jfs/t7501/309/1654880459/149132/f98a2726/599e7890Nb5117daa.png" alt=''>
                        </el-tab-pane>
                        <el-tab-pane id ="推荐区块" label="进口美食" name="fourth">
                            <div id="推荐区文字">
                                <p style="font-size:25px">进口美食</p>
                                <p>送礼送健康，精选生鲜礼盒</p>
                            </div>
                            <div id="推荐商品区块">
                                <div id="推荐商品区">
                                    <div id="单个商品块" v-for="o, index in products4" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products4[index].id)">
                                                <img :src="products4[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products4[index].id)">
                                                <p>{{products4[index].name}}</p>
                                            </a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products3[index].id)">
                                                <p>
                                                    ￥{{products4[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                                <p >销量 :{{products4[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <img id ="插图2" src="https://img10.360buyimg.com/cms/jfs/t13846/115/2346218030/176079/dfd8ece7/5a4094d3Nc8168196.png" alt=''>
                        </el-tab-pane>
                    </el-tabs>
                </div>
                <div id="推荐展示区"></div>
            </div>
            <div id="分类展示区">
                <div id="标题栏">
                    <p style="font-size:30px"> 河 / 马 / 生 / 鲜 </p>
                    <p>HIPPO FRESH</p>
                </div>
                <div id="新鲜水果展区">
                    <div id="展示模块">
                        <div id="展示模块上部">
                            <div id="展示模块上部块1">
                                <div id="标题"><h4 style="color:white; margin-block-end: 8px">新鲜水果</h4></div>
                                <div id="简述">应时而采，新鲜特供</div>
                                <div id="选项">
                                    <div id="装字盒"><a href="#"> 车厘子 </a></div>
                                    <div id="装字盒"><a href="#"> 猕猴桃 </a></div>
                                    <div id="装字盒"><a href="#"> 进口水果 </a></div>
                                    <div id="装字盒"><a href="#"> 芒果 </a></div>
                                    <div id="装字盒"><a href="#"> 苹果 </a></div>
                                    <div id="装字盒"><a href="#"> 橙子 </a></div>
                                </div>
                                <div id="更多">
                                    <div id="装字盒"><a href="#"> 更多水果  &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  > </a></div>
                                </div>
                            </div>
                            <div id="展示模块上部块2"></div>
                            <div id="展示模块上部块3"></div>
                        </div>
                        <div id="展示模块下部">
                            <div id="展示模块下部块">
                                    <div id="单个商品块" v-for="o, index in products5" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products5[index].id)">
                                                <img :src="products5[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products5[index].id)">
                                                <p>{{products5[index].name}}</p>
                                            </a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products5[index].id)">
                                                <p>
                                                    ￥{{products5[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                                <p >销量 :{{products5[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="海鲜水产展区">
                    <div id="展示模块">
                        <div id="展示模块上部">
                            <div id="展示模块上部块1">
                                <div id="标题"><h4 style="color:white; margin-block-end: 8px">海鲜水产</h4></div>
                                <div id="简述">新鲜海货，即时享受</div>
                                <div id="选项">
                                    <div id="装字盒"><a href="#"> 海鲜礼盒 </a></div>
                                    <div id="装字盒"><a href="#"> 虾类 </a></div>
                                    <div id="装字盒"><a href="#"> 软足类 </a></div>
                                    <div id="装字盒"><a href="#"> 活鲜 </a></div>
                                    <div id="装字盒"><a href="#"> 海参 </a></div>
                                    <div id="装字盒"><a href="#"> 扇贝 </a></div>
                                </div>
                                <div id="更多">
                                    <div id="装字盒"><a href="#"> 更多海鲜  &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  > </a></div>
                                </div>
                            </div>
                            <div id="展示模块上部块2"></div>
                            <div id="展示模块上部块3"></div>
                        </div>
                        <div id="展示模块下部">
                            <div id="展示模块下部块">
                                <div id="单个商品块" v-for="o, index in products6" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products6[index].id)">
                                                <img :src="products6[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products6[index].id)">
                                                <p>{{products6[index].name}}</p>
                                            </a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products6[index].id)">
                                                <p>
                                                    ￥{{products6[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                                <p >销量 :{{products6[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="精选肉类展区">
                    <div id="展示模块">
                        <div id="展示模块上部">
                            <div id="展示模块上部块1">
                                <div id="标题"><h4 style="color:white; margin-block-end: 8px">精选肉类</h4></div>
                                <div id="简述">肉香四溢，大快朵颐</div>
                                <div id="选项">
                                    <div id="装字盒"><a href="#"> 猪肉 </a></div>
                                    <div id="装字盒"><a href="#"> 羊肉 </a></div>
                                    <div id="装字盒"><a href="#"> 牛肉 </a></div>
                                    <div id="装字盒"><a href="#"> 牛排 </a></div>
                                    <div id="装字盒"><a href="#"> 鸡翅 </a></div>
                                    <div id="装字盒"><a href="#"> 冷鲜肉 </a></div>
                                </div>
                                <div id="更多">
                                    <div id="装字盒"><a href="#"> 更多肉类  &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  > </a></div>
                                </div>
                            </div>
                            <div id="展示模块上部块2"></div>
                            <div id="展示模块上部块3"></div>
                        </div>
                        <div id="展示模块下部">
                            <div id="展示模块下部块">
                                <div id="单个商品块" v-for="o, index in products7" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products7[index].id)">
                                                <img :src="products7[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products7[index].id)">
                                                <p>{{products7[index].name}}</p>
                                            </a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products7[index].id)">
                                                <p>
                                                    ￥{{products7[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                                <p >销量 :{{products7[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="饮品和蔬菜">
                    <div id="冷冻饮品展区">
                        <div id="展示模块2">
                            <div id="展示模块2上部">
                                <div id="展示模块2上部块1">
                                    <div id="标题"><h4 style="color:white; margin-block-end: 8px">冷冻饮品</h4></div>
                                    <div id="简述">速冻美味，乐享生活</div>
                                    <div id="选项">
                                        <div id="装字盒"><a href="#"> 水饺 </a></div>
                                        <div id="装字盒"><a href="#"> 方便面 </a></div>
                                        <div id="装字盒"><a href="#"> 酸奶 </a></div>
                                        <div id="装字盒"><a href="#"> 蛋糕 </a></div>
                                        <div id="装字盒"><a href="#"> 冰淇淋 </a></div>
                                        <div id="装字盒"><a href="#"> 手抓饼 </a></div>
                                    </div>
                                    <div id="更多">
                                        <div id="装字盒"><a href="#"> 更多冷冻食品   > </a></div>
                                    </div>
                                </div>
                                <div id="展示模块2上部块2"></div>
                            </div>
                            <div id="展示模块2下部">
                                <div id="展示模块2下部块">
                                    <div id="单个商品块" v-for="o, index in products8" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products8[index].id)">
                                                <img :src="products8[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products8[index].id)">
                                                <p>{{products8[index].name}}</p>
                                            </a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products8[index].id)">
                                                <p>
                                                    ￥{{products8[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                            <p >销量 :{{products8[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="新鲜蔬菜展区">
                        <div id="展示模块2">
                            <div id="展示模块2上部">
                                <div id="展示模块2上部块1">
                                    <div id="标题"><h4 style="color:white; margin-block-end: 8px">新鲜蔬菜</h4></div>
                                    <div id="简述">来自田间，清香犹存</div>
                                    <div id="选项">
                                        <div id="装字盒"><a href="#"> 青菜 </a></div>
                                        <div id="装字盒"><a href="#"> 地瓜 </a></div>
                                        <div id="装字盒"><a href="#"> 玉米 </a></div>
                                        <div id="装字盒"><a href="#"> 山药 </a></div>
                                        <div id="装字盒"><a href="#"> 南瓜 </a></div>
                                        <div id="装字盒"><a href="#"> 红薯 </a></div>
                                    </div>
                                    <div id="更多">
                                        <div id="装字盒"><a href="#"> 更多蔬菜   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  > </a></div>
                                    </div>
                                </div>
                                <div id="展示模块2上部块2"></div>
                            </div>
                            <div id="展示模块2下部">
                                <div id="展示模块2下部块">
                                    <div id="单个商品块" v-for="o, index in products9" :key="index">
                                        <div id="单个商品图片">
                                            <a href="javascript:void(0);" @click="toshop(products9[index].id)">
                                                <img :src="products9[index].pictureUrl"  width="150px" height="150px">
                                            </a>
                                        </div>
                                        <div id="单个商品名称">
                                            <a href="javascript:void(0);" @click="toshop(products9[index].id)">
                                                <p>{{products9[index].name}}</p>
                                            </a>
                                        </div>
                                        <div id="单个商品价格">
                                            <a href="javascript:void(0);" @click="toshop(products9[index].id)">
                                                <p>
                                                    ￥{{products9[index].price}}
                                                </p>
                                            </a>
                                            <div id="销量">
                                                <p >销量 :{{products9[index].sales_amount}}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        <el-dialog v-if="discountdialogVisible" title="降价提醒" :visible.sync="discountdialogVisible" width="30%">
            <div id="降价提醒弹窗">
                <p>{{this.messages.message}}</p>
                <img :src="this.messages.productPicture" width="200px" >
                <div id="降价商品名称"><p>{{this.messages.productname}}</p></div>
                <div id="降价提醒价格区">
                    <span id="现价">￥{{this.messages.currentPrice}}</span>
                    <s id="原价">￥{{this.messages.oldPrice}}</s>
                    <span id="折扣"><i class="el-icon-bottom"></i>{{this.messages.percentage}}%</span>
                </div>
                <div id="降价立即购买">
                <el-button type="success" round @click="toshop2()">立即购买</el-button>
                </div>
            </div>
        </el-dialog>
            </div>
            <Bottom ref="bottom_ins"></Bottom>
            <el-backtop>
                <div>
                    <i class="el-icon-top"></i>
                </div>
            </el-backtop>
        </div>
    </div>
</template>

<script>
const axios = require('axios');
/* import App from '../App.vue' */
import Navbar from './Navbar.vue'
import Header from './Header.vue'
import Bottom from './Bottom.vue'
export default {
  components: {
    Navbar,
    Header,
    Bottom
  },
  created () {
      if (localStorage.hasOwnProperty('currentuser')){
            axios.defaults.headers.common.Authorization = JSON.parse(localStorage.currentuser).token
            axios.post('http://47.106.193.0:8080/api/user/message/find', {
            }).then(res => {
                console.log(res.data)
            if (res.data.code === 200) {
                console.log(res.data.data)
                this.messages.messageId = res.data.data.messageId
                this.messages.productId = res.data.data.productId
                this.messages.oldPrice = res.data.data.oldPrice
                this.messages.percentage = res.data.data.percentage
                this.messages.productPicture = res.data.data.productPicture
                this.messages.currentPrice = res.data.data.currentPrice
                this.messages.message = res.data.data.message
                console.log(res.data.data.productName)
                this.messages.productname = res.data.data.productName
                this.messages.id = res.data.data.productId
                this.messages.days = res.data.data.days
                this.messages.percentage = res.data.data.percentage
                this.discountdialogVisible = true
            }
        })
      }
    axios.post('http://47.106.193.0:8080/api/product/kill', {
    }).then(res => {
      this.discountproduct = res.data.data
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 4,
      categoryFirst: '精选肉类'
    }).then(res => {
      this.products1 = res.data.data
      console.log(this.products1)
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 4,
      categoryFirst: '新鲜水果'
    }).then(res => {
      this.products2 = res.data.data
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 4,
      categoryFirst: '冷冻即食'
    }).then(res => {
      this.products3 = res.data.data
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 4,
      categoryFirst: '海鲜水产'
    }).then(res => {
      this.products4 = res.data.data
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 6,
      categoryFirst: '新鲜水果'
    }).then(res => {
      this.products5 = res.data.data
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 6,
      categoryFirst: '海鲜水产'
    }).then(res => {
      this.products6 = res.data.data
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 6,
      categoryFirst: '精选肉类'
    }).then(res => {
      this.products7 = res.data.data
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 3,
      categoryFirst: '冷冻即食'
    }).then(res => {
      this.products8 = res.data.data
    })
    axios.post('http://47.106.193.0:8080/api/product', {
      pageNum: 3,
      categoryFirst: '蔬菜蛋品'
    }).then(res => {
      this.products9 = res.data.data
    })


  },
  data () {
    return {
        messages: {
        messageId: ' ',
        productId: ' ',
        oldPrice: ' ',
        percentage: ' ',
        productPicture: ' ',
        currentPrice: ' ',
        message: ' ',
        productname: ' ',
        id: ' '
      },
      discountdialogVisible: false,
        discountproduct: [],
        notifyPromise:Promise.resolve(),
        messages:[],
      input: '',
      img_url1: 'https://img10.360buyimg.com/cms/jfs/t13846/115/2346218030/176079/dfd8ece7/5a4094d3Nc8168196.png',
      activeName: 'second',
      pictures: ['https://m.360buyimg.com/babel/jfs/t1/182746/10/13219/467054/60e6e0b1E0faed2cf/3bdf1461fc7264cf.jpg!q80',
        'https://m.360buyimg.com/babel/jfs/t1/14084/5/16904/144270/60e29c48E65f43da8/72b7d0a3f7d4a13c.png',
        // 'https://m.360buyimg.com/babel/jfs/t1/196359/2/12777/133729/60ec0f3cE49b3aa40/28b3a539daad2631.jpg',
        'https://m.360buyimg.com/babel/jfs/t1/175866/8/18943/106123/60e7efbeEfcc022c8/3ef92520090e99e5.jpg',
        'https://m.360buyimg.com/babel/jfs/t1/70483/25/16840/139216/60eeb266E2f4c8dd6/122d7b2d659945b6.jpg'],
      products1: [],
      products2: [],
      products3: [],
      products4: [],
      products5: [],
      products6: [],
      products7: [],
      products8: [],
      products9: []
    }
  },
  function: {
    change (myid, mode) {
      document.getElementById(myid).style.display = mode
      if (mode === 'block') { // 显示下拉菜单
        // 设置下拉菜单所在的div边框
        document.getElementById(myid).style.border = '1px solid #eee'
        document.getElementById(myid).style.borderTop = 'none'
        // 设置鼠标划过的li的边框以及背景颜色
        document.getElementById(myid).parentNode.style.backgroundColor = '#fff'
        document.getElementById(myid).parentNode.style.border = '1px solid #eee'
        document.getElementById(myid).parentNode.style.borderBottom = 'none'
      } else {
        // 当不显示下拉列表的时候 鼠标划过的li的边框以及背景颜色
        document.getElementById(myid).parentNode.style.backgroundColor = ''
        document.getElementById(myid).parentNode.style.border = ''
      }
    }
  },
  methods: {
    toshop2 (id) {
      this.$router.push({ path: '/singleproduct', query: { id: this.messages.id } })
    },
    notify(msg) {
        this.notifyPromise = this.notifyPromise.then(this.$nextTick).then(()=>{
        this.$notify({
            title: "提示",
            message: msg,
            dangerouslyUseHTMLString: true,
            duration: 0,
            offset:50
        });
        })
    },
    handleClick (tab, event) {
      console.log(tab, event)
    },
    toshop (id) {
      this.$router.push({ path: '/singleproduct', query: { id: id } })
    }
  }
}
</script>

<style scoped>
/* ======================秒杀区================================ */
  #秒杀区 #标题栏{
      background: url('https://static.360buyimg.com/mtd/pc/fresh_v3/1.0.0/home/images/fresh_mod_head_bg.png');
      height:200px;
      width :1200px;
      background-repeat: no-repeat;
      background-position:50% 50% ;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
      color: white;
      z-index: 2;
  }
  #秒杀区{
      background: url('https://static.360buyimg.com/mtd/pc/fresh_v3/1.0.0/home/images/fresh_unique_bg.jpg');
      background-repeat: no-repeat;
      display: flex;
      flex-direction: column;
      align-items: center;
  }
  #秒杀区商品容器{
      background: url('https://static.360buyimg.com/mtd/pc/fresh_v3/1.0.0/home/images/fresh_unique_top100_bg.jpg');
      width: 1200px;
      height: 400px;
      display: flex;
      flex-direction: row;
  }
  #秒杀单个商品{
      width: 250px;
      margin: 25px;
      height: 400px;
      display: flex;
      flex-direction: column;
      align-items: center;
  }
  #秒杀单个商品名称{
    height: 60px;
    overflow: hidden;
    width: 200px;
  }
  #秒杀现价{
      font-size: 25px;
      color: orange;
  }
  #秒杀现价 p{
      color: orange;
      margin: 0px;
  }
  #秒杀最低价 p{
      font-size: 15px;
      color: gray;
      margin: 0px;
  }
  #秒杀单个商品价格区{
      display: flex;
      flex-direction: row;
  }
  #秒杀单个商品价格{
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-right: 10px;
  }
  #秒杀余量{
      width: 250px;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
  }

  #秒杀进度{
      width: 200px;
  }
  /* =========================================== */
a:link{
    color: black;
}

.头部 {
    width: 100%;
    margin: auto;
}

#展示区
{
    background: url('https://img11.360buyimg.com/cms/jfs/t9016/320/246907648/570978/346ecda1/59a3d65eNd604759b.png') ;
    background-color: #f0f0f7;
    height: 600px;
    background-position:center center;
    display: flex;
    flex-direction: column;
    align-items: center;
}
#走马灯展示区{
    width: 1200px;
    height: 400px;
    display: flex;
    flex-direction: row;
}
ul{
    list-style: none;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    margin:0px;
    padding: 0px;
}
a{
    text-decoration:none
}
#左侧分类栏{
    width: 200px;
    background-color: rgb(247, 245, 242);
    position: relative;
}
#li1, #li2, #li3, #li4, #li5{
    height: 55px;
    line-height: 50px;
    width: 92.5%;
   /*  text-align: center; */
    list-style: none;
    margin: 0 auto;
    padding-right: 15px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    border-color: white;
    border-bottom-color: rgb(121, 124, 123) ;
    border-width: 1px;
    border-style: solid;
}
 #li1:hover, #li2:hover, #li3:hover, #li4:hover{
    background-color: rgb(255, 255, 255);
}
#li5:hover{
    background-color: rgb(255, 255, 255);
}
#li1:hover #右侧拉出栏1{
    z-index: 50;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
}
#li2:hover #右侧拉出栏2{
    z-index: 50;
}
#li3:hover #右侧拉出栏3{
    z-index: 50;
}

#li4:hover #右侧拉出栏4{
    z-index: 50;
}
#li5:hover #右侧拉出栏5{
    z-index: 50;
}
#分类栏头{
    height:40px;
    width: 100%;
    text-align: center;
    background-color: black;
    color:white;
    font-size: 18px;
    line-height: 40px;
}
#分类栏表{
    text-align: center;

}
#分类栏表 i{
    float: right;
    font-size: 20px;
    color: black;
}
#右侧拉出栏1{
    width: 150px;
    height: auto;
    position: absolute;
    left: 190px;
    top: 40px;
    background-color: white;
    z-index: -1;
    opacity: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
#右侧拉出栏2{
    width: 150px;
    height: auto;
    position: absolute;
    left: 190px;
    top: 97px;
    background-color: white;
    z-index: -1;
    opacity: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
#右侧拉出栏3{
    width: 150px;
    height: auto;
    position: absolute;
    left: 190px;
    top: 154px;
    background-color: white;
    z-index: -1;
    opacity: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
#右侧拉出栏4{
    width: 150px;
    height: auto;
    position: absolute;
    left: 190px;
    top: 211px;
    background-color: white;
    z-index: -1;
    opacity: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
#右侧拉出栏5{
    width: 150px;
    height: auto;
    position: absolute;
    left: 190px;
    top: 268px;
    background-color: white;
    z-index: -1;
    opacity: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.el-carousel__item h3 {
    font-size: 14px;
    opacity: 1;
    line-height: 150px;
    margin: 0;
  }
  .el-carousel__indicators {
      display: flex;
      flex-direction: row;
      justify-content: center;
  }
  #中部走马灯{
      width:800px;
  }
  #右侧头条广告
  {
      width: 200px;
      height: 396px;
      background-color: white;
      border-color: white;
      border-top-color: #57b25b ;
      border-top-width: 3px;
      border-style: solid;
  }
  #头条内容 p{
      font-size:10px;
      color: black;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
  #头条内容 a{
      color: black;
  }
  #右侧广告{
      position: relative;
      top:-4px;
  }

  /* ---------------------------------插图 */
  #插图1{
      position: absolute;
      left:-150px;
      top:300px;
  }
  #插图2{
    position: absolute;
    left:0px;
    top:250px;
    z-index: 99;
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
/* ----------------------------------------插图 */

  #展示块文字上{
      color: #5e5f61;
      font-size: 25px;
      padding-bottom: 5px;
      margin-bottom: 0px;
  }
  #展示块文字下{
    color: #b5b4b6;
    font-size: 15px;
    margin-top: 0px;
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
  #展示块图片
  {
      margin-right: 10px;
  }

  /*推荐区样式*/
  #推荐区{
      background: url('https://static.360buyimg.com/mtd/pc/fresh_v3/1.0.0/home/images/fresh_slim_body_bg.png') ;
      height: 800px;
      background-repeat: no-repeat;
      background-size: 1200px, 325px;
      background-position:50% 57% ;
  }
  #推荐区 #标题栏{
      background: url('https://static.360buyimg.com/mtd/pc/fresh_v3/1.0.0/home/images/fresh_mod_head_bg.png');
      height:200px;
      background-repeat: no-repeat;
      background-position:50% 50% ;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
  }
  #标题栏 p{
      margin-bottom: 0px;
  }
  #推荐区导航栏
{
    border-style: none;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.el-tabs__nav-wrap::after{
    height: 0px!important;
}
.el-tabs__nav-scroll{
    display: flex;
    justify-content: center;
}
.el-tabs__item{
    font-size: 20px!important;
}
#推荐区文字{
    height: 400px;
    color: white;
    width: 350px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    text-align: center;
}
#推荐区块{
    width: 1400px;
    height: 500px;
    display: flex;
    flex-direction: row;
    justify-content: center;
}
#推荐商品区块{
    width: 850px;
    height: 400px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
#推荐商品区
{
    width: 800px;
    height: 286px;
    background-color: white;
    border-bottom-right-radius: 50px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}
#分类展示区 #标题栏{
    background: url('https://static.360buyimg.com/mtd/pc/fresh_v3/1.0.0/home/images/fresh_mod_head_bg.png');
      height:200px;
      background-repeat: no-repeat;
      background-position:50% 50% ;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
}
#分类展示区{
    background-color: #f0f0f7;
}
#展示模块{
    height: 580px;
    width: 1190px;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: white;
}
#展示模块上部{
    height: 300px;
    width: 1190px;
    display: flex;
    flex-direction: row;
}
#展示模块上部块1{
    height: 300px;
    width: 160px;
    padding-left: 20px;
    padding-right: 20px;
}
#展示模块上部块2{
    height: 300px;
    width: 600px;
    padding: 0px;
    margin: 0px;
}
#展示模块上部块3{
    height: 300px;
    width: 400px;
}
#展示模块下部{
    width: 1200px;
    height: 280px;
}
#展示模块2下部块{
    display: flex;
    flex-direction: row;
}
#展示模块下部块{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}
#展示模块 #标题{
    font-size: 26px;
    margin-bottom: 5px;
}
#展示模块 #装字盒, #展示模块2 #装字盒{
    font-size: 14px;
    border-style: solid;
    border-radius: 15px;
    border-color: white;
    color: white;
    width: auto;
    border-width: 1px;
    height: 25px;
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-bottom: 9px;
    margin-right: 9px;
    flex-wrap: wrap;
}
#更多 #装字盒{
    font-size: 14px;
    border-style: solid;
    border-radius: 15px;
    border-color: white;
    color: white;
    width: auto;
    border-width: 1px;
    height: 30px;
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-bottom: 9px;
    margin-right: 9px;
    flex-wrap: wrap;
    background-color: white;
}
#新鲜水果展区 #更多 a{
    color: rgb(67, 231, 67);
    font-size: 16px;
}
#海鲜水产展区 #更多 a{
    color: rgb(26, 140, 247);
    font-size: 16px;
}
#精选肉类展区 #更多 a{
    color: rgb(253, 137, 43);
    font-size: 16px;
}
#冷冻饮品展区 #更多 a{
    color: rgb(141, 27, 248);
    font-size: 16px;
}
#新鲜蔬菜展区 #更多 a{
    color: rgb(235, 201, 8);
    font-size: 16px;
}
#装字盒 a{
    color:white;
    padding-left: 13px;
    padding-right: 13px;
}
#选项{
    width: 160px;
    height: 117px;
}
#展示模块 #简述, #展示模块2 #简述{
    font-size: 16px;
    color: white;
    margin-bottom: 20px;
    padding-bottom: 10px;
}
#展示模块 #简述::after, #展示模块2 #简述::after{
    content: "";
    width: 59px;
    height: 4px;
    background: #fff;
}
#展示模块 #选项, #展示模块2 #选项{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}
#新鲜水果展区 #展示模块上部块1{
    background: url('https://img11.360buyimg.com/cms/jfs/t6868/56/1584301821/12315/9fe217a6/5982d8c6N279ff1cf.png');
}
#新鲜水果展区 #展示模块上部块2{
    background: url('https://m.360buyimg.com/babel/jfs/t1/182866/39/12765/118834/60e272d6Eb4edc8a4/31c588f19a67ee72.jpg');
}
#新鲜水果展区 #展示模块上部块3{
    background: url('https://m.360buyimg.com/babel/jfs/t1/181275/23/12244/115702/60dd9f28Ef918a957/84367afca68de929.png');
}
#新鲜水果展区, #海鲜水产展区, #精选肉类展区, #冷冻饮品展区, #新鲜蔬菜展区, #饮品和蔬菜{
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-bottom: 50px;
    position: relative;
}
#冷冻饮品展区, #新鲜蔬菜展区{
    width: 600px;
}
#海鲜水产展区 #展示模块上部块1{
    background: url('https://img11.360buyimg.com/cms/jfs/t6811/139/2080819948/8091/eca88413/59886313N1622b0f9.png');
}
#海鲜水产展区 #展示模块上部块2{
    background: url('https://m.360buyimg.com/babel/jfs/t1/182595/39/13051/51906/60e57325E444f9604/f595376916cdb3f5.jpg');
}
#海鲜水产展区 #展示模块上部块3{
    background: url('https://m.360buyimg.com/babel/jfs/t1/175620/29/18537/87097/60e53d05Ef6d7cc4d/9c08f9f947f47ce5.png');
}
#精选肉类展区 #展示模块上部块1{
    background: url('https://img11.360buyimg.com/cms/jfs/t7129/338/1166404786/8830/81357242/59886595Nc9fc959b.png');
}
#精选肉类展区 #展示模块上部块2{
    background: url('https://m.360buyimg.com/babel/jfs/t1/172283/25/17504/26971/60dd4d8aE104aa022/200d6f292c9e8bc3.jpg');
}
#精选肉类展区 #展示模块上部块3{
    background: url('https://m.360buyimg.com/babel/jfs/t1/188023/7/11394/126464/60de8b2aE69e8a7c9/5d7fd6971347f9e2.png');
}
#冷冻饮品展区 #展示模块2上部块1
{
    background: url('https://img11.360buyimg.com/cms/jfs/t7180/223/664802511/8778/640240c3/5982e076N2f456b3e.png');
}
#冷冻饮品展区 #展示模块2上部块2
{
    background: url('https://m.360buyimg.com/babel/jfs/t1/173398/21/17753/96303/60dd9ebfEdf814698/d45d78ffe1a92469.png');
}
#新鲜蔬菜展区 #展示模块2上部块1
{
    background: url('https://img11.360buyimg.com/cms/jfs/t6043/48/7591568612/5203/8ac000f0/5982e190Ncd9d504d.png');
}
#新鲜蔬菜展区 #展示模块2上部块2
{
    background: url('https://m.360buyimg.com/babel/jfs/t1/192878/21/11748/110681/60e65983Ea5c2e5fd/a66bc44620b6ee5b.png');
}
#展示模块2{
    height: 580px;
    width: 590px;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: white;
}
#展示模块2上部{
    height: 300px;
    width: 590px;
    display: flex;
    flex-direction: row;
}
#展示模块2上部块1{
    height: 300px;
    width: 160px;
    padding-left: 20px;
    padding-right: 20px;
}
#展示模块2上部块2{
    height: 300px;
    width: 390px;
}
#饮品和蔬菜{
    display: flex;
    flex-direction: row;
    height: 580px;
    justify-content: center;
}
/* 电梯导航栏 -----------------------------------*/
#电梯导航栏{
    width: 40px;
    height: 214px;
    position: fixed;
    top: 150px;
    right: 50%;
    margin-right: -685px;
}
#电梯导航栏块{
    width: 40px;
    height: 50px;
    background-color: rgba(128, 128, 128, 0.5);
    margin-bottom: 5px;
    text-align: center;
    display:flex;
    align-items:center;/*所有子元素都垂直居中了*/
    justify-content: center;
    cursor: pointer;
}
#电梯导航栏上{
    font-size: 14px;
    color: white;
}
#电梯导航栏下{
    font-size: 25px;
    color: white;
}

#右拉菜单 a{
    color: rgb(99, 99, 99);
}

#小抬头 a{
    color: black;
    font-size: 20px;
}
#右拉菜单{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin-left: 20px;
    justify-content: flex-start;
}
/* ----------单个商品展示块------------------
                                    <div id="单个商品块">
                                        <div id="单个商品图片">
                                            <img>
                                        </div>
                                        <div id="单个商品名称">
                                            <p></p>
                                        </div>
                                        <div id="单个商品价格">
                                            <p></p>
                                        </div>
                                    </div> */
#单个商品块{
    width: 200px;
    height: 280px;
    display: flex;
    flex-direction: column;
}
#单个商品图片{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 12px;
}
#单个商品名称 p{
    padding-left: 20px;
    padding-right: 20px;
    height: 50px;
    overflow: hidden;
    line-height: 25px;
    font-size: 15px;
    margin-bottom: 0px;
}
#单个商品价格 p{
    font-size: 20px;
    color: orange;
    padding-left: 20px;
    padding-right: 20px;
}
#单个商品价格{
    display: flex;
    flex-direction: row;
    align-items: center;
}
#销量 p{
    color: #5e5f61!important;
    font-size: 13px;
}
</style>
