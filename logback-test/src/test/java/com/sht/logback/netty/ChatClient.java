package com.sht.logback.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/28 15:33
 */
public class ChatClient {
	private String serverHost;
	private int serverPort;

	public ChatClient(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	public void start() throws Exception {
		// 创建事件循环组
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			// 创建客户端启动类
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							//pipeline.addLast(new ChatClientHandler());
						}
					});

			// 连接到服务器
			ChannelFuture channelFuture = bootstrap.connect(serverHost, serverPort).sync();
			System.out.println("成功连接到聊天室服务器 " + serverHost + ":" + serverPort);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			String message;
			while ((message = reader.readLine()) != null) {
				// 将用户输入的消息发送给服务器
				channelFuture.channel().writeAndFlush(message + "\n");
			}
		} finally {
			// 关闭事件循环组
			eventLoopGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		String serverHost = "localhost";  // 聊天室服务器主机名/IP
		int serverPort = 5000;  // 聊天室服务器端口
		new com.sht.logback.chat.ChatClient(serverHost, serverPort).start();
	}
}
