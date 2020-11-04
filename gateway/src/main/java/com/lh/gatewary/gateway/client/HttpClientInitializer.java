package com.lh.gatewary.gateway.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

public class HttpClientInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 客户端接收到的是HttpResponse 响应，所以要使用 HttpResponseDecoder 进行解码
        socketChannel.pipeline().addLast(new HttpResponseDecoder());
        // 客户端发送的是HttpRequest，所以要使用 HttpRequestEncoder 进行编码
        socketChannel.pipeline().addLast(new HttpRequestEncoder());
        socketChannel.pipeline().addLast(new HttpClientHandler());
    }
}
