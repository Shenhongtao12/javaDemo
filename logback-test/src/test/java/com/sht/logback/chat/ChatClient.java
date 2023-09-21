package com.sht.logback.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/28 14:13
 */
public class ChatClient {
	private String serverHost;
	private int serverPort;

	public ChatClient(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	public void start() {
		try (
				Socket socket = new Socket(serverHost, serverPort);
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))
		) {
			System.out.print("请输入你的用户名：");
			String userName = consoleReader.readLine();

			writer.println(userName);

			MessageHandler messageHandler = new MessageHandler(reader);
			new Thread(messageHandler).start();

			String message;
			while ((message = consoleReader.readLine()) != null) {
				writer.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String serverHost = "localhost";  // 聊天室服务器主机名/IP
		int serverPort = 5000;  // 聊天室服务器端口
		new ChatClient(serverHost, serverPort).start();
	}
}
