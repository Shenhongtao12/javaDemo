package com.sht.logback.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/28 14:11
 */
public class ChatServer {
	private List<Socket> clients = new ArrayList<>();

	public ChatServer(int port) {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("聊天室服务器已启动，监听端口：" + port);

			while (true) {
				Socket client = serverSocket.accept();
				clients.add(client);

				ClientHandler clientHandler = new ClientHandler(client, clients);
				new Thread(clientHandler).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int port = 5000;
		new ChatServer(port);
	}
}
