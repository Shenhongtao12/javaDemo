package com.sht.logback.chat;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/28 14:14
 */
public class MessageHandler implements Runnable{

	private BufferedReader reader;

	public MessageHandler(BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public void run() {
		String message;
		try {
			while ((message = reader.readLine()) != null) {
				System.out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
