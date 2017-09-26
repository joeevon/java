
/**
 * Created by joee on 2017/9/26.
 */

package HttpUtil;

import sdk.*;

import java.util.HashMap;
import java.util.Map;

public class WeiXinPay {
    private WXPay wxpay;
    private WXPayConfigImpl config;

    public WeiXinPay() throws Exception {
        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
    }

    public String GenerateTradeNo() {
        return "201413091059590000003433-asd006";
    }

    public String ExpireTime() {
        return "20170930000000";
    }

    public String doUnifiedOrder() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "益爱海蓓课程支付");
        data.put("out_trade_no", GenerateTradeNo());
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "1.1.1.1");
        data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
//        data.put("trade_type", "JSAPI");
        data.put("trade_type", "NATIVE");
        data.put("product_id", "12");
        data.put("time_expire", ExpireTime());

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            System.out.println(r);
            return r.get("prepay_id");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("weixinpay begin.\n");
        WeiXinPay WxPay = new WeiXinPay();
        WxPay.doUnifiedOrder();
        System.out.println("\nweixinpay end.");
    }
}
