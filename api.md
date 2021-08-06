# 河马先生API接口文档

# 1、用户模块

## 1.1、用户注册接口

##### 简要描述

- 用户注册接口

##### 请求URL

- http://47.106.193.0:8080/api/user/register

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| username | 是 | string | 用户名 |
| --- | --- | --- | --- |
| password | 是 | string | 密码 |
| email | 是 | string | 邮箱 |
| verCode | 是 | string | 邮箱验证码 |
| verCodeId | 是 | int | 邮箱验证码id |

##### 后端接收JSON数据格式

| {&quot;username&quot;:&quot;zzlzzzxxxyy&quot;,&quot;password&quot;:&quot;z&quot;,&quot;email&quot;:&quot;z&quot;,&quot;verCode&quot;:&quot;300104&quot;,&quot;verCodeId&quot;:7} |
| --- |

##### 后端返回JSON数据格式

1.用户未点击获取验证码直接登录，verCodeId = 0

| {&quot;code&quot;: 401,&quot;msg&quot;: &quot;请获取验证码后再进行登录&quot;,&quot;data&quot;: {}} |
| --- |

2.用户未验证码过期（超过5分钟）

| {&quot;code&quot;: 402,&quot;msg&quot;: &quot;验证码过期，请重新获取验证码&quot;,&quot;data&quot;: {}} |
| --- |

3.用户输入验证码错误

| {&quot;code&quot;: 403,&quot;msg&quot;: &quot;验证码错误，请重新输入验证码&quot;,&quot;data&quot;: {}} |
| --- |

4.用户名已存在

| {&quot;code&quot;: 409,&quot;msg&quot;: &quot;用户名已存在&quot;,&quot;data&quot;: {}} |
| --- |

5.用户注册成功 { &quot;code&quot;: 200, &quot;msg&quot;: &quot;注册成功&quot;, &quot;data&quot;: {} }

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

## 1.2、用户登录接口

##### 简要描述

- 用户登录接口

##### 请求URL

- http://47.106.193.0:8080/api/user/login

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| username | 是 | string | 用户名 |
| --- | --- | --- | --- |
| password | 是 | string | 密码 |

##### 返回示例

| {&quot;code&quot;: 200,&quot;data&quot;: {&quot;id&quot;:&quot;123&quot;&quot;username&quot;: &quot;12154545&quot;,&quot;token&quot;: &quot;xxx.yyy.zzz&quot;},&quot;msg&quot;: &quot;用户登录成功&quot;} |
| --- |

| {&quot;code&quot;: 401,&quot;data&quot;: {&quot;username&quot;: &quot;12154545&quot;},&quot;msg&quot;: &quot;用户名或密码错误&quot;} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

| **data**  **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | long | 用户id |
| --- | --- | --- |
| usename | string | 用户名 |
| token | string | token令牌 |

## 1.3、用户主页接口

##### 简要描述

- 用户主页接口

##### 请求URL

- http://47.106.193.0:8080/api/user/information

##### 请求方式

- POST

##### 请求头（header）

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;用户信息返回成功&quot;,&quot;data&quot;: {&quot;receiver&quot;: [{&quot;id&quot;: 33,&quot;userId&quot;: 30,&quot;name&quot;: &quot;1&quot;,&quot;phone&quot;: &quot;1&quot;,&quot;province&quot;: &quot;1&quot;,&quot;city&quot;: &quot;1&quot;,&quot;district&quot;: &quot;1&quot;,&quot;address&quot;: &quot;1&quot;,&quot;createTime&quot;: &quot;2021-07-24 06:50:10&quot;,&quot;updateTime&quot;: &quot;2021-07-24 06:50:10&quot;},{&quot;id&quot;: 12,&quot;userId&quot;: 30,&quot;name&quot;: &quot;胡图图&quot;,&quot;phone&quot;: &quot;1234567&quot;,&quot;province&quot;: &quot;&quot;,&quot;city&quot;: &quot;菏泽&quot;,&quot;district&quot;: &quot;曹县&quot;,&quot;address&quot;: &quot;翻斗乐园&quot;,&quot;createTime&quot;: &quot;2021-07-19 13:51:20&quot;,&quot;updateTime&quot;: &quot;2021-07-19 13:51:20&quot;}],&quot;user&quot;: {&quot;id&quot;: 30,&quot;username&quot;: &quot;河马先生&quot;,&quot;email&quot;: &quot;12456789003@123.com&quot;,&quot;phone&quot;: &quot;18293635616&quot;,&quot;avatar&quot;: &quot;http://47.106.193.0:8080/upload/2021/07/23/c66d1d2172c740f2bfcf0d762348da03.png&quot;,&quot;gender&quot;: &quot;男&quot;,&quot;age&quot;: 19,&quot;foodPreference&quot;: &quot;新鲜水果&quot;,&quot;signature&quot;: &quot;我爱吃！&quot;,&quot;hometown&quot;: &quot;甘肃省&quot;,&quot;residentArea&quot;: &quot;东南大学九龙湖校区&quot;,&quot;label&quot;: &quot;喜欢清淡食物&quot;,&quot;createTime&quot;: &quot;2021-07-02 12:05:24&quot;}}} |
| --- |

##### 备注

收件人信息按照降序排序

| {&quot;code&quot;: 404,&quot;data&quot;: {&quot;username&quot;: &quot;12154545&quot;},&quot;msg&quot;: &quot;用户名不存在&quot;} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

| **data**  **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | Long | 用户id |
| --- | --- | --- |
| usename | string | 用户名 |
| email | string | 用户邮箱 |
| avatar | string | 用户头像 |
| receiver | Obiect | 收货人信息 |
|
 |
 |
 |

| **receiver**** 参数名 **|** 类型 **|** 说明** |
| --- | --- | --- |
| id | Long | 收件人信息id |
| --- | --- | --- |
| userId | Long | 用户id |
| name | string | 收货人姓名 |
| phone | string | 收货人电话 |
| province | string | 收货人省份 |
| city | string | 收货人城市 |
| district | string | 收货人县/区 |
| address | string | 收货人详细地址 |
| createTime | yyyy-MM-dd HH:mm:ss | 收件人信息创建时间 |
| updateTime | yyyy-MM-dd HH:mm:ss | 收件人信息更新时间 |

##### 备注

error\_code: 用户消息成功返回：200 用户消息返回失败：404

## 1.4、修改密码接口

##### 简要描述

- 修改密码接口

##### 请求URL

- http://47.106.193.0:8080/api/user/password

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |
| oldPassword | 是 | string | 用户原始密码 |
| newPassword | 是 | string | 用户新密码 |

##### 后端接收JSON格式数据示例

| {&quot;oldPassword&quot;:&quot;xxxxxx&quot;,&quot;newPassword&quot;:&quot;yyyyyy&quot;} |
| --- |

##### 后端返回JSON格式数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;用户密码修改成功&quot;,&quot;data&quot;: {}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;用户密码修改失败&quot;,&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

## 1.5、修改个人资料接口

##### 简要描述

- 修改个人资料接口

##### 请求URL

- http://47.106.193.0:8080/api/user/information/modify

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |
| username | 是 | string | 用户名 |
| email | 是 | string | 用户邮箱 |
| phone | 是 | string | 用户手机号 |

##### 后端接收JSON格式数据示例

| {&quot;email&quot;:&quot;459155106@qq.com&quot;,&quot;phone&quot;:&quot;13344445555&quot;,&quot;gender&quot;:&quot;男&quot;,&quot;age&quot;:20,&quot;foodPreference&quot;:&quot;热带水果&quot;,&quot;signature&quot;:&quot;我爱吃吃吃！！！&quot;,&quot;hometown&quot;:&quot;甘肃省&quot;,&quot;residentArea&quot;:&quot;江苏省南京市东南大学九龙湖校区&quot;,&quot;label&quot;:&quot;666&quot;
} |
| --- |

##### 后端返回JSON格式数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;用户个人信息修改成功&quot;,&quot;data&quot;: {}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;用户名已存在&quot;,&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

## 1.6、发送邮箱验证码接口

##### 简要描述

- 发送邮箱验证码接口

##### 请求URL

- http://47.106.193.0:8080/api/verification/get

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| email | 是 | string | 注册者邮箱 |
| --- | --- | --- | --- |

##### 后端接收JSON数据格式

| {&quot;email&quot;:&quot;459155106@qq.com&quot;} |
| --- |

##### 后端返回JSON数据格式

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;验证码发送成功&quot;,&quot;data&quot;: {&quot;verCodeId&quot;: 5}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

##### data类 数据属性说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| verCodeId | string | 验证码id |
| --- | --- | --- |

## 1.7、获取用户评论接口

##### 简要描述

- 商品列表显示接口

##### 请求URL

- `[http://47.106.193.0:8080/api/user/comment](http://47.106.193.0:8080/api/user/comment)

##### 请求方式

- POST

##### 后端返回JSON数据格式

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | int | 评论id |
| --- | --- | --- |
| comment | string | 评论 |
| userid | Long | 用户id |
| username | String | 用户名 |
| userAvatar | String | 用户头像 |
| productId | Long | 商品id |
| productName | String | 商品名 |
| productUrl | String | 商品图片url |
| score | double | 商品评分 |
| createTime | yyyy-MM-dd HH:mm:ss | 评论创建时间 |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;查找成功&quot;,&quot;data&quot;: [{&quot;id&quot;: 1,&quot;comment&quot;: &quot;good&quot;,&quot;userId&quot;: 1,&quot;username&quot;: &quot;123&quot;,&quot;userAvatar&quot;: &quot;666&quot;,&quot;productId&quot;: 1,&quot;productName&quot;: &quot;123&quot;,&quot;productUrl&quot;: &quot;123&quot;,&quot;score&quot;: 3.5,&quot;createTime&quot;: &quot;2021-07-21 07:38:48&quot;},{&quot;id&quot;: 3,&quot;comment&quot;: &quot;ccc&quot;,&quot;userId&quot;: 2,&quot;username&quot;: &quot;uname2&quot;,&quot;userAvatar&quot;: null,&quot;productId&quot;: 1,&quot;productName&quot;: &quot;name1&quot;,&quot;productUrl&quot;: &quot;url1&quot;,&quot;score&quot;: 3.5,&quot;createTime&quot;: &quot;2021-07-21 06:58:49&quot;},{&quot;id&quot;: 4,&quot;comment&quot;: &quot;good&quot;,&quot;userId&quot;: 1,&quot;username&quot;: &quot;123&quot;,&quot;userAvatar&quot;: &quot;666&quot;,&quot;productId&quot;: 1,&quot;productName&quot;: &quot;123&quot;,&quot;productUrl&quot;: &quot;123&quot;,&quot;score&quot;: 3.5,&quot;createTime&quot;: &quot;2021-07-21 07:54:03&quot;}]} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: 找不到相关评论&quot;data&quot;: null} |
| --- |

##### 备注

error\_code: 成功：200 找不到相关商品：404

# 2、商品模块

## 2.1、商品搜索接口

##### 简要描述

- 商品列表显示接口

##### 请求URL

- `[http://47.106.193.0:8080/api/product/search](http://47.106.193.0:8080/api/product/search)

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| page | 否 | int | 页数 |
| --- | --- | --- | --- |
| page-num | 否 | int | 每页商品个数 |
| product-name | 是 | string | 商品名 |
| category\_id | 否 | int | 商品类型 (默认全部) |
| sort | 否 | int | 排序依据 (默认销量) |
| order | 否 | int | 排序方式(默认按降序) |
| upper-bound | 否 | int | 价格上界 |
| lower-bound | 否 | int | 价格下界 |

备注： page： 默认为0 page-num： 默认为10 type: 0:全部（默认）, 1:蔬菜, 2:水果, 3:肉类 sort: 0.匹配权重排序（默认） 1:销量排序, 2:价格 3.评分 order: 1.降序（默认）， 2.升序 （上下界可以指定其一，也可以都指定）

##### json参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | int | 索引id |
| --- | --- | --- |
| name | string | 商品名 |
| picture | char（url） | 商品图片 |
| price | double | 商品价格 |
| sales\_amount | int | 商品销量 |
| pictureUrl | String | 商品图片url |
| score | double | 商品评分 |
| searchHit | int | 查找到的个数 |
| maxPrice | double | 最大价格 |
| minPrice | double | 最小价格 |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;查找成功&quot;,&quot;data&quot;: [[{&quot;id&quot;: &quot;27305&quot;,&quot;productId&quot;: 27305,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;【佳农贴近百姓的水果量贩】吃香蕉认准佳农品牌，香甜软糯，口感出色的进口香蕉【更多优惠，戳这里】&quot;,&quot;price&quot;: 29.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 77,&quot;pictureUrl&quot;: &quot;http://img14.360buyimg.com/n1/jfs/t1/6470/27/3945/118495/5bdac42aE04b61c95/5c6e65a37691c602.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;27350&quot;,&quot;productId&quot;: 27350,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;都乐Dole 菲律宾进口香蕉 超甜蕉1把装 净重700g 生鲜水果 健康轻食&quot;,&quot;price&quot;: 19.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 52,&quot;pictureUrl&quot;: &quot;http://img10.360buyimg.com/n1/jfs/t1/163845/3/808/92646/5ff29094Ee2667b38/dbf4ab18eb6b3bb6.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;27725&quot;,&quot;productId&quot;: 27725,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;都乐Dole 进口大把蕉 香蕉1kg装 生鲜水果 健康轻食&quot;,&quot;price&quot;: 19.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 65,&quot;pictureUrl&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/123701/17/1023/341439/5eb90fcdE3f23148f/510cf1b65e1f4f51.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;27850&quot;,&quot;productId&quot;: 27850,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;黄元帅苹果 5斤装 香蕉金帅粉面水果新鲜当季整箱香甜刮泥 黄元帅 5斤 精品大果 75-85mm&quot;,&quot;price&quot;: 32.99,&quot;detail&quot;: null,&quot;salesAmount&quot;: 23,&quot;pictureUrl&quot;: &quot;http://img10.360buyimg.com/n1/jfs/t1/143727/37/19025/55463/5fddb5fcE3d9301c1/3febf2c77d016b3e.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;27901&quot;,&quot;productId&quot;: 27901,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;小米蕉 香蕉 广西小米蕉自然熟糯米蕉香蕉新鲜5斤当季水果大芭蕉皇帝蕉新鲜水果&quot;,&quot;price&quot;: 13.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 92,&quot;pictureUrl&quot;: &quot;http://img13.360buyimg.com/n1/jfs/t1/182469/36/10824/73037/60d2b5dbE018c04bc/86adc3988a4b34bd.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;28214&quot;,&quot;productId&quot;: 28214,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;贵州安龙小米蕉 糯米蕉 西贡蕉 新鲜香蕉 坏果包赔 带箱3斤&quot;,&quot;price&quot;: 11.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 50,&quot;pictureUrl&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/157205/40/5859/82607/6016d1e0Eb31da396/28e5f6f88aa7ddd5.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;28355&quot;,&quot;productId&quot;: 28355,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;香蕉很小，8厘米左右长，生果发货，收到催熟！&quot;,&quot;price&quot;: 11.8,&quot;detail&quot;: null,&quot;salesAmount&quot;: 83,&quot;pictureUrl&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/113805/11/12169/74904/5f08b4f4E7dee55b4/62f6ebdf7ba2444e.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;28983&quot;,&quot;productId&quot;: 28983,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;自然熟糯米蕉香蕉新鲜5斤当季水果大芭蕉皇帝蕉整箱批发&quot;,&quot;price&quot;: 14.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 85,&quot;pictureUrl&quot;: &quot;http://img14.360buyimg.com/n1/jfs/t1/87109/24/16347/152463/5e79b4bfE37856883/2220b0f08d38777f.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;28991&quot;,&quot;productId&quot;: 28991,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;菓红蔬青 贵州安龙新鲜香蕉糯米蕉 整箱10斤装 净重约9斤&quot;,&quot;price&quot;: 57.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 100,&quot;pictureUrl&quot;: &quot;http://img13.360buyimg.com/n1/jfs/t1/159545/24/3547/82603/60028d4aEf9dd98d3/f174f6aa7c028434.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;29120&quot;,&quot;productId&quot;: 29120,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;淳果一木 小米蕉 10斤 香蕉苹果蕉芭蕉 自然熟糯米蕉芭蕉 青皮发货 新鲜水果 10斤带箱（净重9斤）&quot;,&quot;price&quot;: 17.8,&quot;detail&quot;: null,&quot;salesAmount&quot;: 30,&quot;pictureUrl&quot;: &quot;http://img14.360buyimg.com/n1/jfs/t1/185773/4/13065/109101/60e69d3aE0f686130/ba2bea20389dfdb5.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null}],{&quot;searchHit&quot;: 47,&quot;minPrice&quot;: 11.8,&quot;maxPrice&quot;: 489.0}]} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: 找不到相关商品&quot;data&quot;: null} |
| --- |

##### 备注

error\_code: 成功：200 找不到相关商品：404

## 2.2、单个商品显示接口

##### 简要描述

- 单个商品显示接口

##### 请求URL

- http://47.106.193.0:8080/api/product/getone

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| id | 是 | Long | 商品id |
| --- | --- | --- | --- |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;商品信息查询成功&quot;,&quot;data&quot;: {&quot;originPlace&quot;: null,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;pictureUrlOne&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/43985/2/9352/576355/5d2f00f4E720cec12/6f416d61d8e7e34c.jpg&quot;,&quot;pictureUrlTwo&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/122917/18/2522/178597/5ec64077Ea66bfd44/2b506099734da405.jpg&quot;,&quot;pictureUrlThree&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/64467/34/4838/130320/5d2f00f3Eb4692b8b/eea4d686355a4736.jpg&quot;,&quot;pictureUrlFour&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/62822/36/4726/126640/5d2f00f1Ef1a92421/a2a61f36076a2533.jpg&quot;,&quot;salesAmount&quot;: 40,&quot;price&quot;: 19.8,&quot;name&quot;: &quot;甜果源 新鲜桃子 净重5斤装 水蜜桃 脆甜毛桃子 新鲜水果 5斤转&quot;,&quot;id&quot;: 29351,&quot;detail&quot;: null,&quot;stock&quot;: 999,&quot;score&quot;: 3.5,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;status&quot;: 1}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;找不到相关商品&quot;,&quot;data&quot;: {&quot;id&quot;: 33}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |
| id | long | 查找商品id |

##### data参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | Long | 商品id |
| --- | --- | --- |
| name | string | 商品名 |
| pictureUrl | string | 商品图片地址 |
| price | double | 商品单价 |
| stock | Long | 商品库存 |
| detail | string | 商品描述 |
| originPlace | string | 商品产地 |
| status | Integer | 商品状态 |
| salesAmount | Long | 商品销量 |
| score | double | 商品评分 |

product status(商品状态): 1-在售 2-下架

##### 备注

error\_code: 成功：200 找不到相关商品：404

## 2.3、推荐商品列表显示接口

##### 简要描述

- 推荐商品列表显示接口

##### 请求URL

- http://47.106.193.0:8080/api/product/recommend

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| page | 否 | int | 页数 |
| --- | --- | --- | --- |
| page-num | 否 | int | 每页商品个数 |
| product-name | 是 | string | 主商品名 |

备注： page： 默认为0 page-num： 默认为10 type: 0:全部（默认）, 1:蔬菜, 2:水果, 3:肉类

##### json参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | int | 商品id |
| --- | --- | --- |
| name | string | 商品名 |
| picture | char(url) | 商品图片 |
| price | double | 商品价格 |
| sales\_amount | int | 商品销量 |
| pictureUrl | String | 商品图片url |
| score | double | 商品评分 |
| searchHit | int | 查找到的个数 |
| maxPrice | double | 最大价格 |
| minPrice | double | 最小价格 |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;查找成功&quot;,&quot;data&quot;: [[{&quot;id&quot;: &quot;27305&quot;,&quot;productId&quot;: 27305,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;【佳农贴近百姓的水果量贩】吃香蕉认准佳农品牌，香甜软糯，口感出色的进口香蕉【更多优惠，戳这里】&quot;,&quot;price&quot;: 29.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 77,&quot;pictureUrl&quot;: &quot;http://img14.360buyimg.com/n1/jfs/t1/6470/27/3945/118495/5bdac42aE04b61c95/5c6e65a37691c602.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;27350&quot;,&quot;productId&quot;: 27350,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;都乐Dole 菲律宾进口香蕉 超甜蕉1把装 净重700g 生鲜水果 健康轻食&quot;,&quot;price&quot;: 19.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 52,&quot;pictureUrl&quot;: &quot;http://img10.360buyimg.com/n1/jfs/t1/163845/3/808/92646/5ff29094Ee2667b38/dbf4ab18eb6b3bb6.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;27725&quot;,&quot;productId&quot;: 27725,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;都乐Dole 进口大把蕉 香蕉1kg装 生鲜水果 健康轻食&quot;,&quot;price&quot;: 19.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 65,&quot;pictureUrl&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/123701/17/1023/341439/5eb90fcdE3f23148f/510cf1b65e1f4f51.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;27850&quot;,&quot;productId&quot;: 27850,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;黄元帅苹果 5斤装 香蕉金帅粉面水果新鲜当季整箱香甜刮泥 黄元帅 5斤 精品大果 75-85mm&quot;,&quot;price&quot;: 32.99,&quot;detail&quot;: null,&quot;salesAmount&quot;: 23,&quot;pictureUrl&quot;: &quot;http://img10.360buyimg.com/n1/jfs/t1/143727/37/19025/55463/5fddb5fcE3d9301c1/3febf2c77d016b3e.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;27901&quot;,&quot;productId&quot;: 27901,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;小米蕉 香蕉 广西小米蕉自然熟糯米蕉香蕉新鲜5斤当季水果大芭蕉皇帝蕉新鲜水果&quot;,&quot;price&quot;: 13.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 92,&quot;pictureUrl&quot;: &quot;http://img13.360buyimg.com/n1/jfs/t1/182469/36/10824/73037/60d2b5dbE018c04bc/86adc3988a4b34bd.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;28214&quot;,&quot;productId&quot;: 28214,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;贵州安龙小米蕉 糯米蕉 西贡蕉 新鲜香蕉 坏果包赔 带箱3斤&quot;,&quot;price&quot;: 11.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 50,&quot;pictureUrl&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/157205/40/5859/82607/6016d1e0Eb31da396/28e5f6f88aa7ddd5.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;28355&quot;,&quot;productId&quot;: 28355,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;香蕉很小，8厘米左右长，生果发货，收到催熟！&quot;,&quot;price&quot;: 11.8,&quot;detail&quot;: null,&quot;salesAmount&quot;: 83,&quot;pictureUrl&quot;: &quot;http://img12.360buyimg.com/n1/jfs/t1/113805/11/12169/74904/5f08b4f4E7dee55b4/62f6ebdf7ba2444e.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;28983&quot;,&quot;productId&quot;: 28983,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;自然熟糯米蕉香蕉新鲜5斤当季水果大芭蕉皇帝蕉整箱批发&quot;,&quot;price&quot;: 14.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 85,&quot;pictureUrl&quot;: &quot;http://img14.360buyimg.com/n1/jfs/t1/87109/24/16347/152463/5e79b4bfE37856883/2220b0f08d38777f.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;28991&quot;,&quot;productId&quot;: 28991,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;菓红蔬青 贵州安龙新鲜香蕉糯米蕉 整箱10斤装 净重约9斤&quot;,&quot;price&quot;: 57.9,&quot;detail&quot;: null,&quot;salesAmount&quot;: 100,&quot;pictureUrl&quot;: &quot;http://img13.360buyimg.com/n1/jfs/t1/159545/24/3547/82603/60028d4aEf9dd98d3/f174f6aa7c028434.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null},{&quot;id&quot;: &quot;29120&quot;,&quot;productId&quot;: 29120,&quot;categoryFirst&quot;: &quot;新鲜水果&quot;,&quot;categorySecond&quot;: &quot;水果&quot;,&quot;name&quot;: &quot;淳果一木 小米蕉 10斤 香蕉苹果蕉芭蕉 自然熟糯米蕉芭蕉 青皮发货 新鲜水果 10斤带箱（净重9斤）&quot;,&quot;price&quot;: 17.8,&quot;detail&quot;: null,&quot;salesAmount&quot;: 30,&quot;pictureUrl&quot;: &quot;http://img14.360buyimg.com/n1/jfs/t1/185773/4/13065/109101/60e69d3aE0f686130/ba2bea20389dfdb5.jpg&quot;,&quot;status&quot;: 1,&quot;score&quot;: null}],{&quot;searchHit&quot;: 47,&quot;minPrice&quot;: 11.8,&quot;maxPrice&quot;: 489.0}]} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: 找不到相关商品} |
| --- |

## 2.4、根据商品id数组显示商品信息接口

##### 简要描述

- 根据商品id数组显示商品信息接口

##### 请求URL

- http://47.106.193.0:8080/api/product/getall

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| ids | 是 | List | 商品ids |
| --- | --- | --- | --- |

##### 后端接收JSON数据示例

| {&quot;ids&quot;:[1,2,3]} |
| --- |

##### 后端返回JSON数据示例

成功（ids列表商品均存在）

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;商品查找成功&quot;,&quot;data&quot;: {&quot;products&quot;: [{&quot;price&quot;: 20.0,&quot;pictureUrl&quot;: &quot;http://xxxx&quot;,&quot;name&quot;: &quot;茄子&quot;,&quot;score&quot;: 3.5,&quot;details&quot;: &quot;好吃&quot;},{&quot;price&quot;: 2.0,&quot;pictureUrl&quot;: &quot;http://xxxx&quot;,&quot;name&quot;: &quot;苹果&quot;,&quot;details&quot;: &quot;曹县大苹果，香啊！！！！！&quot;},{&quot;price&quot;: 2.5,&quot;pictureUrl&quot;: &quot;http://kkk&quot;,&quot;name&quot;: &quot;葡萄&quot;,&quot;score&quot;: 3.5,&quot;details&quot;: &quot;果大肉多的美味葡萄！！！&quot;}]}} |
| --- |

失败（ids列表商品不存在 或 ids列表为空）

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;商品查找失败&quot;,&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

##### data参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| name | string | 商品名 |
| --- | --- | --- |
| pictureUrl | string | 商品图片地址 |
| price | double | 商品单价 |
| details | string | 商品详情 |
| score | double | 商品评分 |

#####


## 2.5、商品列表显示接口

##### 简要描述

- 商品列表显示接口

##### 请求URL

- `[http://47.106.193.0:8080/api/product](http://47.106.193.0:8080/api/product)

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| page | 否 | int | 页数 |
| --- | --- | --- | --- |
| pageNum | 否 | int | 每页商品个数 |
| categoryFirst | 是 | int | 商品类型-第一级 |
| categorySecond | 否 | int | 商品类型-第二级 |

备注： page： 默认为0 page-num： 默认为6

##### json参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | int | 商品id |
| --- | --- | --- |
| name | string | 商品名 |
| price | double | 商品价格 |
| sales\_amount | int | 商品销量 |
| score | double | 商品评分 |
| pictureUrl | String | 商品图片url |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;查找成功&quot;,&quot;data&quot;: [{&quot;id&quot;: &quot;7&quot;,&quot;productId&quot;: 10,&quot;name&quot;: &quot;苹果1&quot;,&quot;price&quot;: 123.0,&quot;salesAmount&quot;: 5，&quot;score&quot;: 3.5,&quot;pictureUrl&quot;:&quot;example.Url&quot;},{&quot;id&quot;: &quot;6&quot;,&quot;productId&quot;: 9,&quot;name&quot;: &quot;苹果2&quot;,&quot;price&quot;: 123.0,&quot;salesAmount&quot;: 4,&quot;score&quot;: 3.5,&quot;pictureUrl&quot;:&quot;example.Url&quot;},{&quot;id&quot;: &quot;4&quot;,&quot;productId&quot;: 7,&quot;name&quot;: &quot;苹果富士康&quot;,&quot;price&quot;: 123.0,&quot;salesAmount&quot;: 2,&quot;score&quot;: 3.5,&quot;pictureUrl&quot;:&quot;example.Url&quot;},{&quot;id&quot;: &quot;3&quot;,&quot;productId&quot;: 6,&quot;name&quot;: &quot;富士康苹果&quot;,&quot;price&quot;: 123.0,&quot;salesAmount&quot;: 1,&quot;score&quot;: 3.5,&quot;pictureUrl&quot;:&quot;example.Url&quot;}]} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: 找不到相关商品&quot;data&quot;: null} |
| --- |

##### 备注

error\_code: 成功：200 找不到相关商品：404

## 2.6、获取商品评论接口

##### 简要描述

- 获取商品评论接口

##### 请求URL

- `[http://47.106.193.0:8080/api/product/comment](http://47.106.193.0:8080/api/product/comment)

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| productId | 是 | Long | 商品id |
| --- | --- | --- | --- |

##### json参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | int | 评论id |
| --- | --- | --- |
| comment | string | 评论 |
| userid | Long | 用户id |
| username | String | 用户名 |
| userAvatar | String | 用户头像 |
| productId | Long | 商品id |
| productName | String | 商品名 |
| productUrl | String | 商品图片url |
| score | double | 商品评分 |
| createTime | yyyy-MM-dd HH:mm:ss | 评论创建时间 |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;查找成功&quot;,&quot;data&quot;: [{&quot;id&quot;: 1,&quot;comment&quot;: &quot;good&quot;,&quot;userId&quot;: 1,&quot;username&quot;: &quot;123&quot;,&quot;userAvatar&quot;: &quot;666&quot;,&quot;productId&quot;: 1,&quot;productName&quot;: &quot;123&quot;,&quot;productUrl&quot;: &quot;123&quot;,&quot;score&quot;: 3.5,&quot;createTime&quot;: &quot;2021-07-21 07:38:48&quot;},{&quot;id&quot;: 3,&quot;comment&quot;: &quot;ccc&quot;,&quot;userId&quot;: 2,&quot;username&quot;: &quot;uname2&quot;,&quot;userAvatar&quot;: null,&quot;productId&quot;: 1,&quot;productName&quot;: &quot;name1&quot;,&quot;productUrl&quot;: &quot;url1&quot;,&quot;score&quot;: 3.5,&quot;createTime&quot;: &quot;2021-07-21 06:58:49&quot;},{&quot;id&quot;: 4,&quot;comment&quot;: &quot;good&quot;,&quot;userId&quot;: 1,&quot;username&quot;: &quot;123&quot;,&quot;userAvatar&quot;: &quot;666&quot;,&quot;productId&quot;: 1,&quot;productName&quot;: &quot;123&quot;,&quot;productUrl&quot;: &quot;123&quot;,&quot;score&quot;: 3.5,&quot;createTime&quot;: &quot;2021-07-21 07:54:03&quot;}]} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: 找不到相关评论&quot;data&quot;: null} |
| --- |

##### 备注

error\_code: 成功：200 找不到相关商品：404

  1.
## 写商品评论接口

##### 简要描述

- 写商品评论接口

##### 请求URL

- `[http://47.106.193.0:8080/api/user/makecomment](http://47.106.193.0:8080/api/user/makecomment)

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| comment | 是 | string | 评论 |
| --- | --- | --- | --- |
| productId | 是 | Long | 商品id |
| star | 否 | int | 商品评分 |

score默认为5， score范围1,2,3,4,5

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;保存评论成功&quot;,&quot;data&quot;: null} |
| --- |

| {&quot;code&quot;: 500,&quot;msg&quot;: 服务器内部错误&quot;data&quot;: null} |
| --- |

## 2.8、搜索商品建议接口

##### 简要描述

- 搜索商品建议接口

##### 请求URL

- `[http://47.106.193.0:8080/api/product/suggestion](http://47.106.193.0:8080/api/product/suggestion)

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| search | 是 | String | 搜索语句 |
| --- | --- | --- | --- |

##### json参数

直接在data中返回搜索建议

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;查找建议成功&quot;,&quot;data&quot;: [&quot;香瓜&quot;,&quot;香蕉&quot;,&quot;百香果&quot;,&quot;香椿&quot;,&quot;香菜&quot;,&quot;茴香&quot;,&quot;芜荽（大叶香菜&quot;,&quot;小叶香菜）&quot;,&quot;香椿芽&quot;,&quot;香菇&quot;]} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;找不到相关建议&quot;,&quot;data&quot;: {&quot;search&quot;: &quot;xiangd&quot;}} |
| --- |

##### 备注

error\_code: 成功：200 找不到相关建议：404

## 2.9、首页商品秒杀列表接口

##### 简要描述

- 首页商品秒杀列表接口

##### 请求URL

- `[http://47.106.193.0:8080/api/product/kill](http://47.106.193.0:8080/api/product/kill)

##### 请求方式

- POST

##### json参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | int | 商品id |
| --- | --- | --- |
| name | string | 商品名 |
| originPrice | double | 商品原价 |
| currentPrice | double | 商品现价 |
| pictureUrl | String | 商品图片url |
| time | String | 秒杀时间 |
| stock | int | 商品库存 |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;查找秒杀商品成功&quot;,&quot;data&quot;: [{&quot;pictureUrl&quot;: &quot;http://img13.360buyimg.com/n1/jfs/t1/111185/10/1129/287838/5e954fb0E9e54edf1/e278565253cb598e.jpg&quot;,&quot;name&quot;: &quot;一整只酱板鸭，甄选散养【老麻鸭】，非速成鸭，肉质紧实，有嚼劲&quot;,&quot;currentPrice&quot;: 11.639999999999999,&quot;id&quot;: 109770,&quot;originPrice&quot;: 38.8,&quot;time&quot;: &quot;2021-07-30 20:00:00&quot;,&quot;stock&quot;: 999},{&quot;pictureUrl&quot;: &quot;http://img14.360buyimg.com/n1/jfs/t1/195852/37/250/113890/6088cea6Ea9a0441e/4b3e6eda1cb3d80f.jpg&quot;,&quot;name&quot;: &quot;【富含维C的超级水果】源自新西兰的12粒特大果王，超级性价比实惠包装，甜蜜多汁圆润饱满，综合营养纵享健康【免费开卡享会员福利】&quot;,&quot;currentPrice&quot;: 47.699999999999996,&quot;id&quot;: 108196,&quot;originPrice&quot;: 159.0,&quot;time&quot;: &quot;2021-07-30 20:00:00&quot;,&quot;stock&quot;: 999},{&quot;pictureUrl&quot;: &quot;http://img14.360buyimg.com/n1/jfs/t1/176124/33/1797/131814/60680b08Eab0cd50d/fcc2a95f9daaa9e1.jpg&quot;,&quot;name&quot;: &quot;新鲜粽叶竹叶包粽子的大粽子叶大叶真空包装棕叶子50片批发 精品大粽叶10cm以上 50片粽绳&quot;,&quot;currentPrice&quot;: 4.08,&quot;id&quot;: 165775,&quot;originPrice&quot;: 13.6,&quot;time&quot;: &quot;2021-07-30 20:00:00&quot;,&quot;stock&quot;: 999},{&quot;pictureUrl&quot;: &quot;http://img11.360buyimg.com/n1/jfs/t1/196158/18/9425/146836/60cfe292Efb34a09a/4cd4b494e05c897c.jpg&quot;,&quot;name&quot;: &quot;必品阁bibigo韩式王饺子组合蒸饺煎饺速冻早餐希杰 白菜1菌菇1玉米1泡菜1+煎饺250g&quot;,&quot;currentPrice&quot;: 65.97,&quot;id&quot;: 161655,&quot;originPrice&quot;: 219.9,&quot;time&quot;: &quot;2021-07-30 20:00:00&quot;,&quot;stock&quot;: 999}]} |
| --- |

# 3、购物车模块

## 3.1、购物车增加商品接口

##### 简要描述

- 购物车增加商品接口

##### 请求URL

- http://xx.com/api/user/cart/addone

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| username | 是 | string | 用户名 |
| --- | --- | --- | --- |
| product\_name | 是 | string | 商品名称 |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: 商品添加成功&quot;data&quot;: {&quot;product\_name&quot;: &quot;菠萝&quot;,&quot;quantity&quot;: &quot;20&quot;,&quot;stock&quot;: &quot;1000&quot;}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: 商品添加失败&quot;data&quot;: {&quot;product\_name&quot;: &quot;菠萝&quot;,&quot;quantity&quot;: &quot;20&quot;,&quot;stock&quot;: &quot;1000&quot;}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

| **data**  **参数名** | **类型** | **说明** |
| --- | --- | --- |
| product\_name | string | 购物车中添加商品名称 |
| --- | --- | --- |
| quantity | int | 购物车中该商品数量 |
| stock | int | 商品库存 |

##### 备注

error\_code: 商品添加成功：200 商品添加失败：404

## 3.2、购物车删除商品接口

##### 简要描述

- 购物车删除商品接口

##### 请求URL

- http://xx.com/api/user/cart/deleteone

##### 请求方式

- DELETE

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| username | 是 | string | 用户名 |
| --- | --- | --- | --- |
| product\_name | 是 | string | 商品名称 |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: 商品删除成功&quot;data&quot;: {&quot;product\_name&quot;: &quot;菠萝&quot;,}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: 商品删除失败&quot;data&quot;: {&quot;product\_name&quot;: &quot;菠萝&quot;,}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

| **data**  **参数名** | **类型** | **说明** |
| --- | --- | --- |
| product\_name | string | 购物车中被删除商品名称 |
| --- | --- | --- |

##### 备注

error\_code: 商品删除成功：200 商品删除失败：404

## 3.3、购物车更新商品接口

##### 简要描述

- 购物车更新商品接口

##### 请求URL

- http://47.106.193.0:8080/api/cart/update

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |

##### product数组

##### 后端接收JSON数据示例

| {&quot;products&quot;:[{&quot;id&quot;: 1,&quot;quantity&quot;: 111},{&quot;id&quot;: 2,&quot;quantity&quot;: 222},{&quot;id&quot;: 3,&quot;quantity&quot;: 333}]} |
| --- |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;购物车更新成功&quot;,&quot;data&quot;: {}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;购物车更新失败&quot;&quot;data&quot;: {}}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 全局错误码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

##### 备注

## 3.4、购物车查找商品接口

##### 简要描述

- 购物车商品查找商品接口

##### 请求URL

- http://47.106.193.0:8080/api/user/cart/find

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |

##### 后端返回JSON数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;购物车商品信息查询成功&quot;,&quot;data&quot;: {&quot;products&quot;: [{&quot;productQuantity&quot;: 2,&quot;productId&quot;: 2,&quot;productPicture&quot;: &quot;2&quot;,&quot;currentPrice&quot;: 2.0,&quot;productStatus&quot;: 1,&quot;OldPrice&quot;: 2.0,&quot;productName&quot;: &quot;2&quot;},{&quot;productQuantity&quot;: 2,&quot;productId&quot;: 1,&quot;productPicture&quot;: &quot;1&quot;,&quot;currentPrice&quot;: 30.0,&quot;productStatus&quot;: 2,&quot;OldPrice&quot;: 17.9,&quot;productName&quot;: &quot;1&quot;}]}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: 购物车商品查询失败&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

| **data**  **参数名** | **类型** | **说明** |
| --- | --- | --- |
| id | string | 商品id |
| --- | --- | --- |
| quantity | int | 商品数量 |

##### postman测试结果

# 4、订单模块

## 4.1、删除订单接口

##### 简要描述

- 删除订单接口

##### 请求URL

- http://47.106.193.0:8080/api/order/delete

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |
| orderId | 是 | int | 订单id |

##### 后端接收JSON参数

| {&quot;orderId&quot;: 2,} |
| --- |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: 订单删除成功,&quot;data&quot;:{}} |
| --- |

| {&quot;code&quot;: 400,&quot;msg&quot;: 订单删除失败,&quot;data&quot;:{}} |
| --- |

## 4.2、订单支付接口

##### 简要描述

- 订单支付接口

##### 请求URL

- http://47.106.193.0:8080/api/alipay/topay

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |
| orderId | 是 | int | 订单id |
| receiverId | 是 | int | 收件人id |
| PaymentType | 是 | string | 支付方式 |

##### 后端接收JSON格式数据

| {&quot;orderId&quot;:101,&quot;receiverId&quot;:12,&quot;PaymentType&quot;:&quot;支付宝&quot;} |
| --- |

##### 后端返回JSON格式数据

| 支付宝官方html页面 |
| --- |

## 4.3、获取单个订单信息接口

##### 简要描述

- 获取单个订单信息接口

##### 请求URL

- http://47.106.193.0:8080/api/order/information

##### 请求方式

- POST

##### 参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| token | string | token令牌 |
| --- | --- | --- |
| orderId | int | 订单号Id |

##### 后端接收JSON数据示例

| {&quot;orderId&quot;:1} |
| --- |

##### 后端返回JSON数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;订单信息获取成功&quot;,&quot;data&quot;: {&quot;receiver&quot;: [{&quot;address&quot;: &quot;东南大学九龙湖校区&quot;,&quot;name&quot;: &quot;闫志豪&quot;,&quot;phone&quot;: &quot;13344445555&quot;,&quot;id&quot;: 1},{&quot;phone&quot;: &quot;1111&quot;,&quot;address&quot;: &quot;玄武湖&quot;,&quot;id&quot;: 2,&quot;name&quot;: &quot;yzh&quot;}],&quot;orderItem&quot;: [{&quot;price&quot;: 1.0,&quot;pictureUrl&quot;: &quot;xxx&quot;,&quot;name&quot;: &quot;葡萄&quot;,&quot;quantity&quot;:1,&quot;details&quot;: &quot;1&quot;},{&quot;price&quot;: 1.0,&quot;pictureUrl&quot;: &quot;xxx&quot;,&quot;quantity&quot;:22,&quot;name&quot;: &quot;苹果&quot;,&quot;details&quot;: &quot;1&quot;}],&quot;orderId&quot;:1,&quot;orderStatus&quot;: 1,&quot;orderPaymentMoney&quot;: 0.0}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;订单信息获取失败&quot;，&quot;data&quot;:{}} |
| --- |

## 4.4、订单创建接口

##### 简要描述

- 订单创建接口

##### 请求URL

- http://47.106.193.0:8080/api/order/generation

##### 请求方式

- POST

##### 请求头header参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| token | string | token令牌 |
| --- | --- | --- |

##### 后端接收Json数据示例

| {&quot;products&quot;:[{&quot;id&quot;: 1,&quot;quantity&quot;: 20},{&quot;id&quot;: 2,&quot;quantity&quot;: 203},{&quot;id&quot;: 3,&quot;quantity&quot;: 333}]} |
| --- |

##### 后端返回Json数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;订单创建成功&quot;,&quot;data&quot;: {}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;订单创建成功&quot;&quot;data&quot;: {}} |
| --- |

## 4.5、订单号列表获取接口

##### 简要描述

- 订单号列表获取接口

##### 请求URL

- http://47.106.193.0:8080/api/order/ids

##### 请求方式

- GET

##### 请求头header参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| token | string | token令牌 |
| --- | --- | --- |

##### 后端返回Json数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;订单号列表获取成功&quot;,&quot;data&quot;: {&quot;orderIds&quot;: [1,2,3,4,5,6,7,8,9,10]}} |
| --- |

## 4.6、获取全部订单信息接口

##### 简要描述

- 获取全部订单信息接口

##### 请求URL

- http://47.106.193.0:8080/api/order/all/information

##### 请求方式

- POST

##### 参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| token | string | token令牌 |
| --- | --- | --- |

##### 后端返回JSON数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;订单信息获取成功&quot;,&quot;data&quot;: {&quot;orders&quot;: [{&quot;orderItem&quot;: [{&quot;quantity&quot;: 6,&quot;price&quot;: 65.6,&quot;pictureUrl&quot;: &quot;https://img14.360buyimg.com/n7/jfs/t1/156497/38/14593/52789/60478313E6c0926d6/97a388f0af12d53e.jpg&quot;,&quot;name&quot;: &quot;东北茄子&quot;,&quot;details&quot;: null},{&quot;quantity&quot;: 3,&quot;price&quot;: 78.8,&quot;pictureUrl&quot;: &quot;https://img11.360buyimg.com/n7/jfs/t1/103684/30/14119/346433/5e5f7997Eff54bc87/d4fa38c109699a88.jpg&quot;,&quot;name&quot;: &quot;烟台红富士苹果&quot;,&quot;details&quot;: null},{&quot;quantity&quot;: 3,&quot;price&quot;: 29.9,&quot;pictureUrl&quot;: &quot;https://img11.360buyimg.com/n7/jfs/t1/173391/37/10778/538114/60a6fef7Eee07344d/43e20b4eb8ea58b9.jpg&quot;,&quot;name&quot;: &quot;葡萄&quot;,&quot;details&quot;: null},{&quot;quantity&quot;: 3,&quot;price&quot;: 23.8,&quot;pictureUrl&quot;: &quot;https://img13.360buyimg.com/n7/jfs/t1/179183/3/14195/313290/60f0008eE25993789/d6e6b276ebebd5ae.jpg&quot;,&quot;name&quot;: &quot;火龙果&quot;,&quot;details&quot;: null}],&quot;orderId&quot;: 86,&quot;orderStatus&quot;: 1,&quot;orderPaymentMoney&quot;: 791.1},{&quot;orderItem&quot;: [{&quot;quantity&quot;: 2,&quot;price&quot;: 29.9,&quot;pictureUrl&quot;: &quot;https://img11.360buyimg.com/n7/jfs/t1/173391/37/10778/538114/60a6fef7Eee07344d/43e20b4eb8ea58b9.jpg&quot;,&quot;name&quot;: &quot;葡萄&quot;,&quot;details&quot;: null},{&quot;quantity&quot;: 2,&quot;price&quot;: 78.8,&quot;pictureUrl&quot;: &quot;https://img11.360buyimg.com/n7/jfs/t1/103684/30/14119/346433/5e5f7997Eff54bc87/d4fa38c109699a88.jpg&quot;,&quot;name&quot;: &quot;烟台红富士苹果&quot;,&quot;details&quot;: null},{&quot;quantity&quot;: 1,&quot;price&quot;: 65.6,&quot;pictureUrl&quot;: &quot;https://img14.360buyimg.com/n7/jfs/t1/156497/38/14593/52789/60478313E6c0926d6/97a388f0af12d53e.jpg&quot;,&quot;name&quot;: &quot;东北茄子&quot;,&quot;details&quot;: null}],&quot;orderId&quot;: 87,&quot;orderStatus&quot;: 1,&quot;orderPaymentMoney&quot;: 283.0}]}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;订单信息获取失败&quot;，&quot;data&quot;:{}} |
| --- |

## 4.7、订单目录数量获取接口

##### 简要描述

- 订单目录数量获取接口

##### 请求URL

- http://47.106.193.0:8080/api/order/category

##### 请求方式

- POST

##### 参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| token | string | token令牌 |
| --- | --- | --- |

##### 后端返回JSON数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;用户订单商品目录数量统计成功&quot;,&quot;data&quot;: {&quot;categoryFirst&quot;: {&quot;冷冻即食&quot;: 0,&quot;海鲜水产&quot;: 0,&quot;新鲜水果&quot;: 3,&quot;精选肉类&quot;: 0,&quot;蔬菜蛋品&quot;: 0},&quot;categorySecond&quot;: {&quot;牛肉&quot;: 0,&quot;蛋类&quot;: 0,&quot;热带水果&quot;: 2,&quot;面点&quot;: 0,&quot;火锅丸料&quot;: 0,&quot;热销水果&quot;: 2,&quot;鲜菌菇&quot;: 0,&quot;猪肉&quot;: 0,&quot;内脏类&quot;: 0,&quot;烘焙食材&quot;: 0,&quot;茄果瓜类&quot;: 0,&quot;葱姜蒜椒&quot;: 0,&quot;时令水果&quot;: 0,&quot;鸡肉&quot;: 0,&quot;叶菜类&quot;: 0,&quot;海参&quot;: 0,&quot;鱼类&quot;: 0,&quot;虾类&quot;: 0,&quot;贝类&quot;: 0,&quot;鸭肉&quot;: 0,&quot;羊肉&quot;: 0,&quot;根茎类&quot;: 0,&quot;蟹类&quot;: 0,&quot;饮品甜品&quot;: 0,&quot;方便速食&quot;: 0}}} |
| --- |

## 4.8、订单总金额获取接口

##### 简要描述

- 订单总金额获取接口

##### 请求URL

- http://47.106.193.0:8080/api/order/money

##### 请求方式

- POST

##### 参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| token | string | token令牌 |
| --- | --- | --- |

##### 后端返回JSON数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;订单总金额信息获取成功&quot;,&quot;data&quot;: {&quot;已支付&quot;: 87.6,&quot;待支付&quot;: 9.99}} |
| --- |

## 4.9、订单时间段获取接口

##### 简要描述

- 订单时间段获取接口

##### 请求URL

- http://47.106.193.0:8080/api/order/time

##### 请求方式

- POST

##### 参数

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| token | string | token令牌 |
| --- | --- | --- |

##### 后端返回JSON数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;订单时间段获取成功&quot;,&quot;data&quot;: {&quot;time&quot;: {&quot;00&quot;: 0,&quot;11&quot;: 0,&quot;22&quot;: 1,&quot;01&quot;: 2,&quot;12&quot;: 0,&quot;23&quot;: 1,&quot;02&quot;: 0,&quot;13&quot;: 0,&quot;03&quot;: 0,&quot;14&quot;: 0,&quot;04&quot;: 0,&quot;15&quot;: 0,&quot;05&quot;: 0,&quot;16&quot;: 0,&quot;06&quot;: 0,&quot;17&quot;: 0,&quot;07&quot;: 0,&quot;18&quot;: 0,&quot;08&quot;: 0,&quot;19&quot;: 0,&quot;09&quot;: 0,&quot;20&quot;: 0,&quot;10&quot;: 0,&quot;21&quot;: 0}}} |
| --- |

# 5、收件人模块

## 5.1、增加收件人接口

##### 简要描述

- 增加收件人接口

##### 请求URL

- http://47.106.193.0/api/receiver/add

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |
| name | 是 | string | 收件人姓名 |
| phone | 是 | string | 收件人电话 |
| province | 是 | string | 收件人省份 |
| city | 是 | string | 收件人城市 |
| district | 是 | string | 收件人县/区 |
| address | 是 | string | 收件人详细地址 |

##### 后端接收JSON格式数据示例

| {&quot;name&quot;:&quot;xxx&quot;,&quot;phone&quot;:&quot;13344445555&quot;,&quot;province&quot;:&quot;江苏省&quot;,&quot;city&quot;:&quot;南京市&quot;,&quot;district&quot;:&quot;江宁区&quot;,&quot;address&quot;:&quot;江苏省南京市江宁区东南大学路2号&quot;} |
| --- |

##### 后端返回JSON格式数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;收件人添加成功&quot;,&quot;data&quot;: {&quot;address&quot;: &quot;江苏省南京市江宁区东南大学路2号&quot;,&quot;province&quot;: &quot;江苏省&quot;,&quot;phone&quot;: &quot;13344445555&quot;,&quot;city&quot;: &quot;南京市&quot;,&quot;district&quot;: &quot;江宁区&quot;,&quot;name&quot;: &quot;xxx&quot;,&quot;id&quot;: 4}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

| **data**** 参数名 **|** 必选 **|** 类型 **|** 说明** |
| --- | --- | --- | --- |
| id | 是 | int | 收件人id |
| --- | --- | --- | --- |
| name | 是 | string | 收件人姓名 |
| phone | 是 | string | 收件人电话 |
| province | 是 | string | 收件人省份 |
| city | 是 | string | 收件人城市 |
| district | 是 | string | 收件人县/区 |
| address | 是 | string | 收件人详细地址 |

## 5.2、删除收件人接口

##### 简要描述

- 删除收件人接口

##### 请求URL

- http://47.106.193.0/api/receiver/delete

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |
| receiverId | 是 | int | 收件人id |

##### 后端接收JSON格式数据示例

| {&quot;receiverId&quot;:&quot;6&quot;} |
| --- |

##### 后端返回JSON格式数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;收件人删除成功&quot;,&quot;data&quot;: {}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;收件人删除失败&quot;,&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

# 6、文件上传模块

## 6.1、文件上传接口

##### 简要描述

- 文件上传接口

##### 请求URL

- http://47.106.193.0:8080/api/user/upload

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| file | 是 | MultipartFile | 文件 |
| --- | --- | --- | --- |

##### 返回示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;文件上传成功&quot;&quot;data&quot;: {&quot;url&quot;: &quot;http://xxxxxx&quot;}} |
| --- |

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;文件上传失败&quot;&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

##### data类 数据属性说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| url | string | 可访问该文件地址 |
| --- | --- | --- |

# 7、收藏夹模块

## 7.1、收藏夹添加商品接口

##### 简要描述

- 收藏夹添加商品接口

##### 请求URL

- http://47.106.193.0:8080/api/user/favorite/add

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |
| productId | 是 | int | 商品id |

##### 后端接收JSON数据格式

| {&quot;productId&quot;:8536} |
| --- |

##### 后端返回JSON数据格式

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;收藏夹添加商品成功&quot;,&quot;data&quot;: {}} |
| --- |

错误一：该商品不存在

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;收藏夹添加商品不存在&quot;,&quot;data&quot;: {}} |
| --- |

错误二：用户已收藏该商品

| {&quot;code&quot;: 405,&quot;msg&quot;: &quot;你已收藏该商品&quot;,&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

## 7.2、收藏夹删除商品接口

##### 简要描述

- 收藏夹删除商品接口

##### 请求URL

- http://47.106.193.0:8080/api/user/favorite/delete

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |
| productId | 是 | int | 商品id |

##### 后端接收JSON数据格式

| {&quot;productId&quot;:8536} |
| --- |

##### 后端返回JSON数据格式

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;收藏夹删除商品成功&quot;,&quot;data&quot;: {}} |
| --- |

该用户收藏夹中不存在该商品

| {&quot;code&quot;: 404,&quot;msg&quot;: &quot;收藏夹删除商品失败&quot;,&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

## 7.3、收藏夹商品查找接口

##### 简要描述

- 收藏夹商品查找接口

##### 请求URL

- http://47.106.193.0:8080/api/user/favorite/find

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |

##### 后端返回JSON数据格式

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;收藏夹查找成功&quot;,&quot;data&quot;: {&quot;products&quot;: [{&quot;productId&quot;: 8542,&quot;productPicture&quot;: &quot;http://img10.360buyimg.com/n7/jfs/t1/167568/20/21240/127498/6088cc9fE31988e11/eecfe59b7d20139b.jpg&quot;,&quot;productStatus&quot;: 1,&quot;productName&quot;: &quot;【精美定制礼盒】佳沛新西兰产季包装精美的水果礼盒，国际大牌Zespri佳沛出品，沐浴纯净阳光【免费开卡享会员福利】&quot;,&quot;oldPrice&quot;: 109.0,&quot;currentPrice&quot;: 30},{&quot;productId&quot;: 8541,&quot;productPicture&quot;: &quot;http://img11.360buyimg.com/n7/jfs/t1/167493/14/12148/97249/604b1bf6E40ea4c95/ba5d6001257d3f44.jpg&quot;,&quot;productStatus&quot;: 1,&quot;productName&quot;: &quot;【产地直采】【百万好评】爆款烟台红富士，清脆酸甜！&quot;,&quot;oldPrice&quot;: 38.8&quot;currentPrice&quot;: 30}]}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

# 8、用户通知模块

## 8.1、获取用户通知接口

##### 简要描述

- 获取用户通知接口

##### 请求URL

- http://47.106.193.0:8080/api/user/message/find

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |

##### 后端返回JSON数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;用户通知获取成功&quot;,&quot;data&quot;: {&quot;messageId&quot;: 43,&quot;productId&quot;: 108180,&quot;productName&quot;:&quot;香蕉&quot;,&quot;oldPrice&quot;: 89.0,&quot;currentPrice&quot;: 50.99,&quot;percentage&quot;: 0.43,&quot;productPicture&quot;: &quot;http://img11.360buyimg.com/n1/jfs/t1/164269/26/11561/175674/604861acE512bf3c9/169cf1fb33a875d8.jpg&quot;,&quot;message&quot;: &quot;尊敬的用户你好: 你收藏的商品： 低卡轻食，618狂欢！食力好物满199减100元，爆品，低至5折！速来！查看(此商品不参加上述活动) 加入收藏夹以来降价幅度超过30%，快来关注一下吧！！&quot;,&quot;createTime&quot;: &quot;2021-07-27 15:41:57&quot;}} |
| --- |

| {&quot;code&quot;: 201,&quot;msg&quot;: &quot;用户暂无通知&quot;,&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

## 8.2、更改消息状态接口

##### 简要描述

- 更改消息状态接口

##### 请求URL

- http://47.106.193.0:8080/api/user/message/read

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | string | token令牌 |
| --- | --- | --- | --- |

##### 后端接收JSON数据示例

| {&quot;messageId&quot;:20} |
| --- |

##### 后端返回JSON数据示例

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;用户通知阅读状态修改成功&quot;,&quot;data&quot;: {&quot;messageId&quot;: 20}} |
| --- |

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;用户通知阅读状态修改失败&quot;,&quot;data&quot;: {&quot;messageId&quot;: 20}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

# 9、论坛模块

## 9.1、发帖接口

##### 简要描述

- 发帖接口

##### 请求URL

- http://47.106.193.0:8080/api/forum/add

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| token | 是 | String | token令牌 |
| --- | --- | --- | --- |
| title | 是 | String | 帖子标题 |
| content | 是 | String | 帖子内容 |

##### 后端接收JSON格式数据

| {&quot;title&quot;:&quot;标题&quot;,&quot;content&quot;:&quot;内容&quot;} |
| --- |

##### 后端返回JSON格式数据

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;发帖成功&quot;,&quot;data&quot;: {}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

## 9.2、查询论坛接口

##### 简要描述

- 查询论坛接口

##### 请求URL

- http://47.106.193.0:8080/api/forum/findall

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| pageIndex | 是 | int | 当前查询页码（从0开始） |
| --- | --- | --- | --- |
| pageSize | 是 | int | 每页大小 |

##### 后端接收JSON格式数据

| {&quot;pageIndex&quot;:0,&quot;pageSize&quot;:3} |
| --- |

##### 后端返回JSON格式数据

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;论坛获取成功&quot;,&quot;data&quot;: {&quot;forum&quot;: [{&quot;id&quot;: 7,&quot;userId&quot;: 30,&quot;username&quot;: &quot;河马先生&quot;,&quot;userAvatar&quot;: &quot;http://47.106.193.0:8080/upload/2021/07/25/bd6dbd306f344e95b14aee183da32ce5.png&quot;,&quot;title&quot;: &quot;111&quot;,&quot;content&quot;: &quot;1111&quot;,&quot;createTime&quot;: &quot;2021-07-25 12:53:01&quot;},{&quot;id&quot;: 6,&quot;userId&quot;: 30,&quot;username&quot;: &quot;河马先生&quot;,&quot;userAvatar&quot;: &quot;http://47.106.193.0:8080/upload/2021/07/25/bd6dbd306f344e95b14aee183da32ce5.png&quot;,&quot;title&quot;: null,&quot;content&quot;: &quot;1111&quot;,&quot;createTime&quot;: &quot;2021-07-25 12:01:55&quot;},{&quot;id&quot;: 5,&quot;userId&quot;: 30,&quot;username&quot;: &quot;河马先生&quot;,&quot;userAvatar&quot;: &quot;http://47.106.193.0:8080/upload/2021/07/25/bd6dbd306f344e95b14aee183da32ce5.png&quot;,&quot;title&quot;: null,&quot;content&quot;: &quot;222&quot;,&quot;createTime&quot;: &quot;2021-07-25 12:01:51&quot;}]}} |
| --- |

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| code | int | 状态码 |
| --- | --- | --- |
| msg | string | 请求消息提示 |

## 9.3、根据帖子id获取内容接口

##### 简要描述

- 根据帖子id获取内容接口

##### 请求URL

- http://47.106.193.0:8080/api/forum/find

##### 请求方式

- POST

##### 参数

| **参数名** | **必选** | **类型** | **说明** |
| --- | --- | --- | --- |
| forumId | 是 | int | 帖子id |
| --- | --- | --- | --- |

##### 后端返回JSON格式数据

| {&quot;code&quot;: 200,&quot;msg&quot;: &quot;查找帖子成功&quot;,&quot;data&quot;: {&quot;userAvatar&quot;: &quot;http://47.106.193.0:8080/upload/2021/07/26/101ac55e9d4d4e22857cc0c15607e2b8.png&quot;,&quot;title&quot;: &quot;\b这是我的帖子标题&quot;,&quot;forumId&quot;: 8,&quot;content&quot;: &quot;今天买了超级好吃的生鲜哈哈哈哈 太好吃了&quot;,&quot;username&quot;: &quot;josh00&quot;}} |
| --- |

{ &quot;code&quot;: 404, &quot;msg&quot;: &quot;找不到相关帖子&quot;, &quot;data&quot;: 9 } ps：9为帖子id

##### 返回参数说明

| **参数名** | **类型** | **说明** |
| --- | --- | --- |
| title | string | 帖子标题 |
| --- | --- | --- |
| forumId | int | 帖子id |
| content | stirng | 帖子内容 |
