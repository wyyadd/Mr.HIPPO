package com.hippo.fresh.alipay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.hippo.fresh.alipay.util.JSONUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AlipayService {


    /**
     * Payment（支付能力） Page（电脑网站）	 pay（生成交易表单，渲染后自动跳转支付宝网站引导用户完成支付）
     * pay(subject: string, outTradeNo: string, totalAmount: string, returnUrl: string)
     *
     * subject：|string| 订单标题
     * outTradeNo：|string| 交易创建时传入的商户订单号
     * totalAmount：|string|  订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * returnUrl：|string| 支付成功后同步跳转的页面，是一个http/https开头的字符串
     */

    public String toPay(String orderId,String paymentMoney) throws Exception {

//        System.out.println("到这里啦111");
        AlipayTradePagePayResponse pay = Factory.Payment.Page().pay("河马生鲜购物平台订单",orderId,
                paymentMoney,"http://39.101.199.242/finalhome");

//        System.out.println("到这里啦222");
        String payForm = null;
        if (ResponseChecker.success(pay)) {
            payForm = pay.getBody();
//            System.out.println("到这里啦333");
        }
        return payForm;
    }


//    /**
//     * 通过时间生成外部订单号 out_trade_no
//     * @return
//     */
//    private String generateTradeNo() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
//        String tradeNo = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
//        System.out.println(tradeNo);
//        return tradeNo;
//    }
//
    /**
     * 查询交易状态
     * @param outTradeNo 生成的外部订单号 out_trade_no
     * @return
     * @throws Exception
     */
    public Object queryTradeStatus(String outTradeNo) throws Exception {
        AlipayTradeQueryResponse query = Factory.Payment.Common().query(outTradeNo);
        Map<String, Object> map = JSONUtils.jsonToMap(query.getHttpBody());

        // 返回交易结果, 是否交易成功需要根据该对象中的 trade_status 来确定
        // trade_status 的枚举值如下, 请见 https://opendocs.alipay.com/apis/api_1/alipay.trade.query
        // WAIT_BUYER_PAY（交易创建，等待买家付款）
        // TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）
        // TRADE_SUCCESS（交易支付成功）
        // TRADE_FINISHED（交易结束，不可退款）
        // 当 trade_status 等于 TRADE_SUCCESS 或 TRADE_FINISHED 时, 表示支付成功
        return map.get("alipay_trade_query_response");
    }

}