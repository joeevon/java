/**
 * Created by joee on 2017/9/26.
 */

package WeiXinOrder;

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
        long l = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(l);
        java.sql.Timestamp time = new java.sql.Timestamp(l);

        String TradeNo = Integer.toString(date.getYear() + 1900) + String.format("%02d", date.getMonth() + 1)
                + String.format("%02d", date.getDate()) + "-" + String.format("%02d", time.getHours())
                + String.format("%02d", time.getMinutes()) + String.format("%02d", time.getSeconds())
                + "-" + Long.toString(l);

        log.info("TradeNo:" + TradeNo);
        return TradeNo;
    }

    private String GetExpireTime() {
        long l = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(l + 15 * 60 * 1000);
        java.sql.Timestamp time = new java.sql.Timestamp(l + 15 * 60 * 1000);

        String ExpireTime = Integer.toString(date.getYear() + 1900) + String.format("%02d", date.getMonth() + 1)
                + String.format("%02d", date.getDate()) + String.format("%02d", time.getHours())
                + String.format("%02d", time.getMinutes()) + String.format("%02d", time.getSeconds());

        log.info("ExpireTime:" + ExpireTime);
        return ExpireTime;
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
            System.out.println(r);
            return r.get("prepay_id");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("WxUnifiedOrder begin.\n");
        WxUnifiedOrder order = new WxUnifiedOrder();
        order.UnifiedOrder("oMDigwQEv-nmXH29CIt0Hx5uCw3o");
        System.out.println("\nWxUnifiedOrder end.");
    }
}
