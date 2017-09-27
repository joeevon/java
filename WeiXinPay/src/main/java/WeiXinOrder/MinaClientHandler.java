/**
 * Created by joee on 2017/9/27.
 */

package WeiXinOrder;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaClientHandler extends IoHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(MinaClientHandler.class);

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // 当session被创建的时候调用
        log.info("create session success.");
        super.sessionCreated(session);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        // 出现异常
        log.error("exception");
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        // 从服务器中接收到消息后的处理
        log.info("Message from server:" + message.toString());
        super.messageReceived(session, message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // 往服务器中发送消息
        log.info("send message to server.");
        super.messageSent(session, message);
    }
}
