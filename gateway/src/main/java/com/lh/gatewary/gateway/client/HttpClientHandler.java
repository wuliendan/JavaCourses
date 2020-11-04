package com.lh.gatewary.gateway.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse httpResponse = (FullHttpResponse) msg;
            log.info("CONTENT_TYPE:" + httpResponse.headers().get(HttpHeaders.Names.CONTENT_TYPE));
        }

        if(msg instanceof HttpContent) {
            HttpContent content = (HttpContent)msg;
            ByteBuf buf = content.content();
            log.info("Server:" + buf.toString(io.netty.util.CharsetUtil.UTF_8));
            buf.release();
        }
    }
}
