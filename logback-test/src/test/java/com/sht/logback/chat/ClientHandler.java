package com.sht.logback.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/28 14:12
 */
public class ClientHandler implements Runnable{
	private Socket client;
	private List<Socket> clients;

	public ClientHandler(Socket client, List<Socket> clients) {
		this.client = client;
		this.clients = clients;
	}

	@Override
	public void run() {
		try (
				InputStreamReader isr = new InputStreamReader(client.getInputStream());
				BufferedReader reader = new BufferedReader(isr);
				PrintWriter writer = new PrintWriter(client.getOutputStream(), true)
		) {

			String userName = reader.readLine();
			broadcast(userName + " 进入聊天室");

			String message;
			while ((message = reader.readLine()) != null) {
				if ("exit".equalsIgnoreCase(message)) {
					break;
				}
				broadcast(userName + " 说：" + message);
			}

			clients.remove(client);
			client.close();
			broadcast(userName + " 离开聊天室");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void broadcast(String message) {
		for (Socket client : clients) {
			try {
				PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
				writer.println(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
