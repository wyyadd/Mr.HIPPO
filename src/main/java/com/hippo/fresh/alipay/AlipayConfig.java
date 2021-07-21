package com.hippo.fresh.alipay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import org.springframework.stereotype.Component;

/**使用修改说明*/
//请将 AlipayConfig.java 中的 appId、alipayPublicKey、notifyUrl 换成自己的配置；
//同时修改 merchantPrivateKey 的路径或者直接粘贴 merchantPrivateKey 的字符串；
//另外, 请将 AlipayService.java 中的重定向url(return url) 修改为自己项目的url；

@Component
public class AlipayConfig {

    // 1. 设置参数（全局只需设置一次）
    static {
        Factory.setOptions(getOptions());
    }

    private static Config getOptions() {
        Config config = new Config();

        config.protocol = "https";

        //真实环境
        config.gatewayHost = "openapi.alipay.com";
        //沙箱环境
        config.gatewayHost = "openapi.alipaydev.com";

        config.signType = "RSA2";

        //支付宝开放平台中的APPID
        config.appId = "2021000117690160";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
//        config.merchantPrivateKey = FileUtils.readFileOfTxt("C:\\Users\\Yan'zhi'hao\\Documents\\支付宝开放平台开发助手\\RSA密钥\\应用私钥2048.txt");
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVVAvYj4kgkkyIuE4rpklI07rpTN9pvWHfN61g6WNDjHgNlbbuwZTtED8nZl0t9SR7npm2s/3MhBEeJPLx3HZXtuaPBH5nXvRfgF02s8Tgg0i+rvwJKN6/cIIh7+LvJcXZE9dLYa2Q2XInQ0zYVDHwEjdaTPJA56V1vqQlUAMZ2vCTgF1lk1r2Cqcld7ea2Kh4DfSfhbs4nlSGeIgoZVyUgYK22Nukc351ZXRzOiCs8Jiu8ycRBOG2ivBLIJoc7Vgk3nshwUXfqrmynFIPCJpQl333H7kY2b/1Lqx7T9WZJkhSfg1gX7A18DJCzUoYBTtksUcdwoB/Y1/7zZVHjKbDAgMBAAECggEBAJPluxnRB8Kvw4eB60JGHr03nx7kWT40KNRZI6ohXYQQgvMJfjY4mpS1C6etNcqHyot5SMrwuAUwJHgTpl8vuDIl33J0w/ED1cskqq28HNk0jf719n/n773x46FTIxMy+SKICSsK4kndY7dJa7myogchM5uHLERmabUVjSO7fGbEpdcaBuHz2V07eTc04KEA6B51YgibOOa/BzbYS654RJgim27EjyuIGegPfDzHiOYuUF/NUSjcI8KLcrhBlXSqQ4oIFO9UwLWhk2fzOrFuYi5SN6mPtzBMYTXvfbwfrx+FvrjYxSMvlvuesCKoOLnEhrO9l5EmXjJRCEvW3G/jT0kCgYEA/kDPRAMlfGfGYnVK3/2qzg2cOblhtvefbbhFTCjQkxQdRZepAZypBe7FuJqOxe2srIrAUTD1g7jAIf5pSgzS8g90Rm+lYXbEWn7TlXUkByMg32vHLVpb2G2Soj/CMmWvpf55SzC1IioykhT0cOmAFSlCM3qhsVwRLEFfgbPeGO8CgYEAllqw0rljHedWlhrnJhV8+xgdllKRUnWRvHmmpSNTYkAalSdsB3sEagDkzrmkl4FcFsaAqYdEmL4AXDOPZZaTB1QK+XyuWRI+2d6ytQCfGmn7ceEsYNFXgPgZ2rWK/31KljO6LYmjL9THVzfN4O6DIM/kY6ddP9KgVXo3pf6Kh20CgYBgxGveN9BZk+mWaYvQymvqtpViBuNgzsf36208ekA/6iTl6r3hJi3SWzkXdIOoYzgYB0sA28BlnLhLd9e83jqe9DDN6goomRpMUv7WYFoQ+Va2kf+hjEVuju2DIP8ZTUhXxg2GB4i48xSCMsWB/+zGoQhYLXFC3oaOsGbYJ4v9MwKBgHaTPWJirEV4HXQx8Vu6lJ9d7G+R3GPgrreqZP9VdbxFe/Fnbq4TmW6UF97kz2ZgsaSQlgWc1T90kMomK4/diUS9Rg5uAv3iAslF9UAEJnXpYVD1WU9dqZnfkFxI0j8dq4X7c3fYVWcycqdZLjeXQrjM23ywED12OdZPlbWGoe+dAoGBAJDbLRJ0oFZrtaOxq/rKtwKD1aNRvIVYfroq5GtwX9XR2GJUurqOKczbQnXUwAvw3nMiu5foOOFv0tXbWx/S7HW0m9oxHY78PyUNuhCpNxVcCy3iHNsQslTBed4PG/A/MD9JhLMyWHWPhCKTLdqyToku/tMQ+MydcxTTewUF2Fjh";

        //注：采用非证书模式，赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArSlzV7QZKVTMLWdQdfydC4Uaw6YIIUOvts4CSnGwXw3SC+G2id1WX1iK760BbFsKmdxtV31az2UdGjdOKPjE0VxX04a1cAo3a8j7GZhni925yY2uXvKSoRnwVGNRLAd0Naw+m+STU0CAhovxlFuvab0gpnaglJzpg62Z+ROlbKws2yMH1ILz4AV5EubrOQat46K4xkAOE7aX4/jxoOQtfdhx2moW9KUN8rneYogRg6GWSKvySTHCjZUITYqEuTgDepggd3E4tQOyqM/Ftamdhf8dtZ6CJNqBUrM9L29Nw8Amx4Jq5xL/n8WfQHjoxsS1WIEUUZMP8aJTlEeZtbYQgQIDAQAB";

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = "http://47.106.193.0:8080/api/alipay/callback";

        return config;
    }
}

