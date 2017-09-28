package WeiXinOrder;

/**
 * Created by joee on 2017/9/25.
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.HttpHeaders.Values;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static Log log = LogFactory.getLog(ServerHandler.class);
    private HttpRequest request;
    private ByteBufToBytes reader;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            if (HttpHeaders.isContentLengthSet(request)) {
                reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(request));
            }
            log.info(request.getMethod() + "," + request.getUri());
        }
        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf content = httpContent.content();
            reader.reading(content);
            content.release();
            if (reader.isEnd()) {
                String resultStr = new String(reader.readFull());
                log.info("recv client request, data:" + resultStr);
                WxUnifiedOrder order = new WxUnifiedOrder();
                String res = order.UnifiedOrder(resultStr);
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(res.getBytes()));
                response.headers().set(CONTENT_TYPE, "text/plain");
                response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
                response.headers().set(CONNECTION, Values.KEEP_ALIVE);
                log.info("respond client.");
                ctx.write(response);
                ctx.flush();
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage());
        ctx.close();
    }

}

