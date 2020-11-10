package com.lh.gatewary.gateway.servernew;

import com.lh.gatewary.gateway.outbound.httpclient4.HttpOutboundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static org.apache.http.HttpHeaders.CONNECTION;

@Slf4j
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final String proxyServer;
    private HttpOutboundHandler handler;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        handler = new HttpOutboundHandler(this.proxyServer);
    }

    private void send(String content, ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String result = "";

        //接收到完成的Http请求
        FullHttpRequest httpRequest = (FullHttpRequest) msg;

        try {
            String path = httpRequest.uri();
            log.info("path:{}", path);
            String body = httpRequest.content().toString(CharsetUtil.UTF_8);
            HttpMethod method = httpRequest.method();

            //处理Http Get 请求
            if (HttpMethod.GET.equals(method)) {
                log.info("body:{}", body);
                result = "GET Request, Response = " + body;
                send(result, ctx, HttpResponseStatus.OK);
            }
        } catch (Exception e) {
            log.info("处理请求失败");
            e.printStackTrace();
        } finally {
            httpRequest.release();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接的客户端地址:{}", ctx.channel().remoteAddress());
    }
}
