package com.lh.gatewary.gateway.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public HttpInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        if (sslCtx != null) {
            channelPipeline.addLast(sslCtx.newHandler(socketChannel.alloc()));
        }

        //http响应编码
        channelPipeline.addLast("encode",new HttpResponseEncoder());
        //http请求编码
        channelPipeline.addLast("decode",new HttpRequestDecoder());
        //聚合http请求
        channelPipeline.addLast("aggre",
                new HttpObjectAggregator(10*1024*1024));
        //启用http压缩
        channelPipeline.addLast("compressor",new HttpContentCompressor());
        //自己的业务处理
        channelPipeline.addLast("busi",new HttpHandler());
    }
}
