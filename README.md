这是一个后端基于spring boot， 前端基于vue开发的购物网站, 前端仓库：https://github.com/Josh00-Lu/MrHippoFront

**《河马生鲜后端技术开发文档》**

![image](https://user-images.githubusercontent.com/67038748/128480962-c8f8faf0-3af7-44cc-a290-d90ac32b997c.png)

第一部分 引言

1.  编写目的

此项目后端开发技术文档的编写主要是为了给开发《河马先生生鲜购物商城》做主要的规划和整合，在开发过程中起到引导作用，保证项目团队学习使用的技术完全遵照文档规定，便于项目团队成员更好地合作，使项目工作开展的各个过程合理有序，以文件化的形式，作为项目团队成员之间的共识与约定，它又是项目生命周期内的所有项目活动的行动基础、项目团队开展和检查项目工作的依据。

在项目开发的过程中，小组成员对技术开发文档不断更新完善，确保技术开发文档与项目实际情况保持一致。标准规范的项目开发文档也有利于技术人员后期的维护与运营。

2.  读者对象

该文档的读者为《河马先生生鲜购物商城》开发小组后端成员、软件分析人员、开发管理人员和测试人员。

第二部分 后端技术介绍

1.  后端技术选型概述

![image](https://user-images.githubusercontent.com/67038748/128481127-3223f5b7-fab0-4bb6-8775-e51e619e9d74.png)

2.  后端技术选型详细介绍

**1.后端主体框架——Spring Boot框架**

1.1 什么是Spring Boot ?

Spring Boot 是由 Pivotal 团队提供的全新框架，其设计目的是用来简化新 Spring
应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。

2.  Spring Boot 的优点——自动装配

（1）配置简单，避免使用大量XML方式进行配置。

2.  开发者专注于业务逻辑，提高开发效率。

1.3 Spring Boot框架分层设计

> ![image](https://user-images.githubusercontent.com/67038748/128481179-94728ccf-2d74-4e67-aa5a-202381912ad0.png)


（1）entity层

entity层即数据库实体层，也被称为model层，pojo层。一般数据库一张表对应一个实体类，类属性同表字段一一对应。

（2）dao层

dao层即数据持久层，也被称为mapper层。dao层的作用为访问数据库，向数据库发送sql语句，完成数据的增删改查任务。

（3）service层

service层即业务逻辑层。service层的作用为完成功能设计与业务逻辑的实现。service层调用dao层接口，接收dao层返回的数据，完成项目的基本功能设计。

（4）controller层

controller层即控制层。功能为请求和响应控制。controller层负责前后端交互，接受前端请求，调用service层，接收service层返回的数据，最后返回具体的页面和数据到客户端。

**2.数据库操作框架——Spring Data JPA**

2.1什么是Spring Data JPA？

Spring Data
JPA是Spring提供对数据库访问的技术，其中包括关系型数据库、非关系型数据库（No-sql数据库）、以及map-reduce（大数据Hadoop）框架的访问。

2.2 Spring Data JPA的优点？

（1）简化持久层开发工作

Spring Data
JPA可以让我们对于基础功能的实现在持久层不用去书写sql语句，直接继承Repository接口的Dao接口，开启JPA的注解扫描就可以进行持久层开发。对于一些复杂功能还提供了属性表达式、命名查询、Query注解等方式供我们使用。

（2）简化分页业务处理

Spring Data
JPA的Repository接口实现了PagingAndSortingRepository接口，可以直接传参调用实现分页功能。

2.3项目开发过程中的应用

（1）基础功能：数据库增删改查功能（CRUD）；

（2）复杂功能：数据统计、模糊查询；

（3）分页与排序功能；

**3.安全权限管理框架——Spring Security**

网络安全问题至关重要。作为生鲜购物平台开发项目，涉及到用户个人信息、用户与商家金钱交易等诸多用户个人数据，项目开发的安全性能极其关键。我们选择了基于
Spring AOP 和 Servlet 过滤器的Spring Security安全框架，确保项目拥有很好的安全性能。

3.1 什么是Spring Security ?

Spring
Security是一个功能强大且高度可定制的身份验证和访问控制框架。提供了完善的认证机制和方法级的授权功能。是一款非常优秀的权限管理框架。它的核心是一组过滤器链，不同的功能经由不同的过滤器。

3.2 Spring Security的核心功能？

（1）认证（你是谁，用户/设备/系统）

（2）验证（你能干什么，也叫权限控制/授权，允许执行的操作）

（3）攻击防护（防止伪造身份）

3.3 Spring Security的原理技术？

创建一系列的过滤层和拦截器来进行请求的验证和拦截，由认证管理器（AuthenticationManager）和决策管理器（AccessDecisionManager）来放行或拒绝请求（如下图），以此来达到安全的效果。

![image](https://user-images.githubusercontent.com/67038748/128481261-ac0940b3-fbb1-42b6-b776-213fd7d6889d.png)

优点：既可以免去我们手动配置各种拦截器和过滤器的操作，又可以为项目提供更好的安全性能。

![image](https://user-images.githubusercontent.com/67038748/128481360-d8b27137-0f63-4caf-8f74-df19e9643d45.png)
![image](https://user-images.githubusercontent.com/67038748/128481401-55154d21-3940-4ee6-8021-fd7041fd38cb.png)


3.4 项目中的应用

对于河马先生购物商城web应用，基本的用户注册页、用户登录页等页面可以任何用户无权限访问，而对于用户主页、订单等用户个人信息需要用户权限，并且需要设置拦截，我们通过Spring
Security 进行了很好的权限管理。

**4. JSON web 令牌——JWT**

4.1 什么是JWT？

Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC
7519).该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的其它业务逻辑所必须的声明信息，该token也可直接被用于认证，也可被加密。

![image](https://user-images.githubusercontent.com/67038748/128481490-e4aeccb5-557c-4093-a9bb-f8daf65f2ce5.png)

4.2 Token的优点

相比与传统session的用户验证方式，token验证主要具有以下优点：

（1）支持跨域访问， 有效预防跨域网站攻击， 更加安全。

（2）在网络传输过程中性能更好

（3）不占用服务器储存资源

（4）适合移动设备

4.3 Spring Security 与 JWT结合

由于本项目采用前后端分离的方法，首先要解决的就是登录及授权的问题。传统的session认证限制了应用的扩展能力，无状态的JWT认证方法应运而生，该认证机制特别适用于分布式站点的单点登录（SSO）场景。

但spring
security本身是通过session验证，不原生支持token验证，我小组成员为了使项目实现高安全性能与无状态验证，在spring
security框架的基础上，手动添加了jwt token过滤层与相应的处理组件，以实现token的发放与验证。

![image](https://user-images.githubusercontent.com/67038748/128481535-a4453553-47a1-4b98-bfad-aab446866e0e.png)

![image](https://user-images.githubusercontent.com/67038748/128481580-d0230b9d-ea8b-40da-8f65-0cb6fd2827ed.png)

项目实现的流程图如下：

![image](https://user-images.githubusercontent.com/67038748/128481620-585f2e99-8f2f-461f-a8ea-2e728dae9b2d.png)

**5. 分布式搜索和分析引擎——Elasticsearch**

5.1 什么是Elasticsearch？

![image](https://user-images.githubusercontent.com/67038748/128481706-3d94f06f-81c0-4a33-adc4-bf219dbd2486.png)
Elasticsearch
是一个分布式、高扩展、高实时的搜索与数据分析引擎。它能很方便的使大量数据具有搜索、分析和探索的能力。

5.2 本项目使用Elasticsearch的原因

（1）相比于传统的数据库，由于采用倒排索引，Elasticsearch的搜索响应时间平均较短。

（2）相对于数据库的精准匹配，Elasticsearch的相关性匹配更贴近人的思维方式。在本次项目中，我们小组采用相关度打分匹配的方式，在返回结果中就会根据分数由高到低排列，分数越高意味着和查询语句越相关。此外，
我们还创新型加入了权重搜索模式，将用户搜索词与商品名称、商品详情、商品类别等不同元素赋予不同的权重，进行综合打分，返回用户一个更加精准的结果。

【举例】当用户搜索“星巴克咖啡”，带有“星巴克咖啡”的信息就要比只包含“咖啡”的信息靠前。那么到底什么是「相关性匹配」，什么才是「人的思维」呢？

比如我搜「莎士比亚」，我要的肯定不只是精确包含「莎士比亚」的文稿，我可能还要搜「莎翁」、「Shakespeare」、「哈姆雷特」、「罗密欧和朱丽叶」、「威尼斯的商人」…

又比如我输错了，输成「莎士笔亚」，「相关性匹配」可以智能的帮我优化为「莎士比亚」，返回对应的搜索结果。

这就是搜索引擎的强大之处，它似乎可以理解你的真实意图。

![image](https://user-images.githubusercontent.com/67038748/128481761-0cc3e697-fdaa-4ad0-86e0-b8bef74986be.png)
（3）我们小组还在Elasticsearch中加入了IK分词器和拼音搜索功能，更能满足中文用户的搜索需求。下图分别为错字搜索和拼音搜索：

![image](https://user-images.githubusercontent.com/67038748/128481816-f200ec2d-3244-4091-b9c1-4abbe91e2ad2.png)

**6. 邮件服务——JavaMailSender**

6.1邮件发送原理

从1969年10月世界上的第一封电子邮件发出，到2019年，已经过去将近半个世纪了。虽然即时通讯和视频会议，甚至全息投影都变得日益普及，但电子邮件依然有着广泛的使用场景和不可撼动的历史地位。

![image](https://user-images.githubusercontent.com/67038748/128481853-062ce459-d8be-4adf-af42-42a889e1e31b.png)

6.2 Spring Boot 配置

（1）QQ邮箱开通POP3/SMTP服务。

（2）Spring Boot 引入 maven。

（3）Spring Boot 配置yml文件，添加相关邮箱信息。

（4）Service层实现业务逻辑，Controller层调用接口。

6.3邮箱功能在本项目开发中的应用

为了确保用户邮箱的真实性，以及限制每个邮箱仅能注册单一账号，我们使用邮箱验证码来进行验证。

![image](https://user-images.githubusercontent.com/67038748/128481898-2d828d99-d2ca-46c2-9482-b40dffa34bd9.png)

![image](https://user-images.githubusercontent.com/67038748/128481924-4201caa1-18a7-4aa2-bd24-eef5bffdbce1.png)

**7. 第三方支付接口——支付宝 alipay easysdk**

支付是商品购买中一个必不可少的流程。作为生鲜购物平台，核心业务以卖家与买家之间的交易为主，利用支付宝第三方接口，使项目开发产品功能完备。

7.1 Spring Boot 配置

（1）使用支付宝提供的密钥生成工具生成公钥和私钥；

（2）在支付宝开发者平台中开发服务中的研发服务找到沙箱环境，配置公钥；

（3）Spring Boot 中导入 alipay-easysdk maven依赖；

（4）创建config文件进行相关配置；

5.  编写支付和回调业务逻辑层与控制层；

7.2沙箱环境

由于支付宝开发平台申请使用正式环境需要商家营业执照，我们选择了全真模拟真实开发环境的支付宝沙箱开发环境，通过支付宝官网提供的开发流程，介入支付宝接口，可以很好的体现出项目的完整性。

![image](https://user-images.githubusercontent.com/67038748/128481967-9c87aad9-2391-48d1-8ccf-6696067f915d.png)

**8. 统一的异常处理**

由于我们采用的是前端和后端分离，统一的异常处理显得尤为重要，可以给前端返回一个明确的信息。如果不使用统一的异常处理，当后台出现了异常，直接给前端抛一个500错误，这种错误会被用户看到非常不友好，而我们的统一异常处理就是把所有的异常都经过规范处理，前端可以收到可读性极强的信息，并且符合项目规范。

![image](https://user-images.githubusercontent.com/67038748/128481993-754e68bd-c9d5-4e51-8b87-f2f210bc7059.png)

![image](https://user-images.githubusercontent.com/67038748/128482071-9ec185e5-8b41-470b-9b9e-03d9feded685.png)项目异常处理实例：

**9.** **统一的日志记录——SLF4J和Logback日志框架**

9.1 什么是SLF4J和Logback？

SLF4J是简单的日志外观模式框架，抽象了各种日志框架例如Logback、Log4j、Commons-logging和JDK自带的logging实现接口。它使得用户可以在部署时使用自己想要的日志框架。SLF4J是轻量级的，在性能方面几乎是零消耗的。

Logback是Log4j的改进版本，而且原生支持SLF4J（因为是同一作者开发的），因此从其它日志框架如Log4j或JDK的logging迁移到Logback是完全可行的。

![image](https://user-images.githubusercontent.com/67038748/128482090-0d1fd2a9-72bf-4cd1-8247-1bc8df86a292.png)

**10.** **严格遵守API规范 前后端分离高效进行**

10.1 RESTful API规范

RESTful 架构可以充分的利用 HTTP 协议的各种功能，是 HTTP 协议的最佳实践。RESTful API
是一种软件架构风格、设计风格，可以让软件更加清晰，更简洁，更有层次，可维护性更好。

客户端请求时，要明确告诉服务器，接受 JSON 格式，请求的 HTTP 头的 ACCEPT 属性要设成 application/json。

服务端返回的数据也是一个 JSON 对象。服务器回应的 HTTP 头的 Content-Type 属性要设为 application/json。

10.2 规范的API接口文档

我们在此次项目开发中完全按照spring Boot + Vue
的前后端分离开发模式。在这一过程中，规范的API接口文档起着举足轻重的作用，保证了前后端开发小组工作的高效有序进行。累计接口文档60余页，近一万字。

![image](https://user-images.githubusercontent.com/67038748/128482125-0279728a-93e4-4726-8e0a-eeda1a54111a.png)

10.3 postman 规范测试

API接口在开发完成后，后端开发小组成员通过postman测试，确保API接口功能正常，并保证接口接收JSON数据格式与返回JSON数据格式与接口文档保持一致，这样为前后端之间工作的高效有序进行提供了很大的便利。

![image](https://user-images.githubusercontent.com/67038748/128482158-47196a55-80cb-4894-8581-fb6a094af9a4.png)

**11．商品降价提醒模块**

生鲜购物平台的应用最终是服务于卖家，为卖家提供便利的平台，为商家顾客提供便捷的服务，以吸引更多的用户，获得更大的盈利。

我们通过降价提醒功能，对于用户已加入购物车、收藏夹的商品，以及用户浏览点击的商品进行记录，分析用户的喜爱偏好，给予用户醒目的降价提醒，便捷的支付渠道，增加用户购买商品的机率。

11．1 项目实现原理

（1）购物车、收藏夹静态降价提醒

在MYSQL数据库建立触发器，购物车和收藏夹数据表中保存了当前价格和加入时价格，当后台管理系统修改商品价格时，会自动更新购物车表和收藏夹表中的当前价格，当用户查看购物车或收藏夹时，对于降价商品会有一个醒目的提醒，增加用户购买机率。

（2）用户登录降价弹窗

通过对MYSQL数据库的实时检测，将降价商品加入降价商品表，并采用定时功能选择用户所喜爱的最大降价幅度的商品发送消息通知，用户在登陆后便可以接收到通知，通过便捷的弹窗直接进入支付界面，缩短用户思考时间，增加用户购买机率，实现卖家收益最大化。
![image](https://user-images.githubusercontent.com/67038748/128482197-b132839a-f9af-4d42-97a2-5ccd5e8d4f8c.png)

**12．秒杀功能模块**

秒杀活动是绝大部分电商选择的低价促销、推广品牌的方式。不仅可以给平台带来用户量，还可以提高平台知名度。一个好的秒杀系统，可以提高平台系统的稳定性和公平性。

在每年的618、双11等节日，天猫、淘宝、京东等购物平台都推出了秒杀功能，即有限数量的商品在规定时间开始抢购，网站会有很大的用户点击量，这种短时间内高并发的访问往往会给服务器带来巨大的负载，对此我们小组开发秒杀功能模块，学习理解了高并发性能的处理方法。

12.1 项目技术应用

前端：

（1）点击置灰：在秒杀未开始时，秒杀按钮点击置灰；

（2）随机丢弃：为减轻后端高并发处理的压力，前端随机丢弃一部分用户的点击，默认无效；

（3）人机验证：为防止爬虫进行快速抢购造成服务器巨大压力，增设人机验证功能，提升安全性能；

后端：

（1）针对接口进行限流控制，限制同一用户同一IP的访问频率；

![image](https://user-images.githubusercontent.com/67038748/128482291-c8d5111c-bb07-4007-a951-ae4961151e22.png)（2）通过使用Redis作为消息队列，异步处理订单入库。

**13．安全性能**

**13.1 验证码——Secure Random随机数**

通常采取的随机数生成方法是Math.random，它生成的是一般随机数，采用的是类似于统计学的随机数生成规则，其输出结果很容易预测，因此可能导致被攻击者击中。

而SecureRandom是真随机数，采用的是类似于密码学的随机数生成规则，其输出结果较难预测，若想要预防被攻击者攻击，最好做到使攻击者根本无法，或不可能鉴别生成的随机值和真正的随机值。所以我们本项目采用了SecureRandom随机数确保安全性能。

**13.2 密码加密——BCryptPasswordEncoder**

spring security中的BCryptPasswordEncoder方法采用SHA-256
+随机盐+密钥对密码进行加密。SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密，但是采用Hash处理，其过程是不可逆的。

在本应用中充分考虑安全性能，采用不可逆的SHA Hash算法加密，即使数据库泄露，也不会造成密码泄露，确保用户安全。

第三部分 数据库

1.  E-R 实体-联系图

![image](https://user-images.githubusercontent.com/67038748/128482344-8b79f9e6-33fc-428a-b3f9-a50cbccdeb23.png)

2.  数据表

<!-- end list -->

1.  **用户表**

> 该表主要用于存储用户个人基本信息、用户状态权限以及创建时间。

![image](https://user-images.githubusercontent.com/67038748/128482385-885b6117-2ec1-4647-8789-370e557ec095.png)


2.  **收件人表**

> 该表主要用于存储收件人基本信息、创建时间与更新时间。

![image](https://user-images.githubusercontent.com/67038748/128482393-59521262-149a-4908-997e-9a2e54b1a5a2.png)

3.  **商品表**

> 该表主要用于存储商品基本信息、状态、添加时间以及商品评论相关信息。

![image](https://user-images.githubusercontent.com/67038748/128482405-5012e47c-81ce-44f6-8c74-09fbac2d194c.png)

4.  **购物车表**

> 该表主要用于存储用户购物车信息，购物车中相关商品信息。

![image](https://user-images.githubusercontent.com/67038748/128482415-9b03af6a-d12a-4dfd-9ba1-70c804a24995.png)

5.  **订单表**

> 该表主要用于存储用户订单基本信息，订单相关时间信息。

![image](https://user-images.githubusercontent.com/67038748/128482431-69fcd65a-15f7-4e8d-b3e5-361143fad8e3.png)

6.  **订单条目表**

> 该表主要用于存储用户订单中每个商品的相关信息。

![image](https://user-images.githubusercontent.com/67038748/128482441-832ec0a0-4ee1-4d79-9ea1-ea076b358e95.png)

7.  **收藏夹表**

> 该表主要用于存储用户收藏夹相关商品信息和创建时间相关信息。

![image](https://user-images.githubusercontent.com/67038748/128482452-ffd0caa3-a690-41f2-9d16-cd527cad73ac.png)

8.  **评论表**

> 该表主要用于存储用户对于商品的评论基本信息。

![image](https://user-images.githubusercontent.com/67038748/128482464-15a26fab-d51e-4dfc-8fb3-3d6b2a3de8a5.png)

9.  **验证码表**

> 该表主要用于存储用户验证码与验证码创建时间，便于进行时间失效检测。

> ![image](https://user-images.githubusercontent.com/67038748/128482471-84e08a04-5a99-495a-8a40-ff0a1df815fe.png)

**10．论坛表**

该表主要用于存储用户交流论坛中帖子相关信息。

> ![image](https://user-images.githubusercontent.com/67038748/128482481-30445361-2d92-4735-9db4-19a315ffae1a.png)

