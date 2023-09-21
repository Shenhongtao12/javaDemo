package com.sht.logback.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/28 15:31
 */
public class ChatServer {

	private static List<ChannelHandlerContext> clients = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		// 创建主线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 创建工作线程组
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 创建服务器启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class);
//					.childHandler(new ChannelInitializer<SocketChannel>() {
//						@Override
//						public void initChannel(SocketChannel ch) throws Exception {
////							ChannelPipeline pipeline = ch.pipeline();
////							pipeline.addLast(new ChatServerHandler());
//						}
//					});

			// 绑定端口并开始接收连接
			ChannelFuture channelFuture = serverBootstrap.bind(5000).sync();
			System.out.println("聊天室服务器已启动，监听端口：5000");
			channelFuture.channel().closeFuture().sync();
		} finally {
			// 关闭线程组
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	// 广播消息给所有在线客户端
	public static void broadcast(String message) {
		for (ChannelHandlerContext ctx : clients) {
			ctx.writeAndFlush(message + "\n");
		}
	}

	// 服务器消息处理器
	static class ChatServerHandler extends ChannelInboundHandlerAdapter {
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			// 客户端连接建立时将其加入客户端列表
			clients.add(ctx);
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			String message = (String) msg;
			// 将客户端发送的消息广播给所有在线客户端
			broadcast(message);
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			// 客户端连接断开时将其从客户端列表移除
			clients.remove(ctx);
		}
	}
}
