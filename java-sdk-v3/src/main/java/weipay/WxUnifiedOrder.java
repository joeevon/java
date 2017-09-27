/**
 * Created by joee on 2017/9/27.
 */

package weipay;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sdk.WXPay;
import sdk.WXPayConfigImpl;

import java.util.HashMap;
import java.util.Map;

public class WxUnifiedOrder {
    private static Log log = LogFactory.getLog(WxUnifiedOrder.class);
    private WXPay wxpay;
    private WXPayConfigImpl config;

    public WxUnifiedOrder() throws Exception {
        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
    }

    private String GetTradeNo() {
        return "201413091059590000003433-asd005";
    }

    private String GetExpireTime() {
        return "20170930000000";
    }

    public String UnifiedOrder(String Openid) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "益爱海蓓课程支付");
        data.put("out_trade_no", GetTradeNo());
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "0.0.0.0");
        data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
        data.put("trade_type", "JSAPI");
        data.put("time_expire", GetExpireTime());
        data.put("openid", Openid);

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            log.info(r);
            return r.get("prepay_id");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        log.info("WxUnifiedOrder begin.");
        WxUnifiedOrder order = new WxUnifiedOrder();
        order.UnifiedOrder("oMDigwQEv-nmXH29CIt0Hx5uCw3o");
        log.info("WxUnifiedOrder end.");
    }
}
