package com.sht.logback;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author as2i
 * @date 2023/4/18 9:33
 */
public class CommonTest {

	private static volatile boolean isOver = false;

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (!isOver) {
					System.out.println("i   " + i++);
				}
			}
		});
		thread.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isOver = true;
	}

	@Test
	public void test() {
		double i = 2.0/7;
		System.out.println(i);
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis() + 0 * 1000);
		Timestamp timestamp = new Timestamp(-10 * 1000);
		System.out.println(timestamp);
	}

	@Test
	public void stringTest() {
		String str = new String("123");
		System.out.println(str);
		formatString(str);
		System.out.println(str);
	}

	@Test
	public void listTest() {
		List<String> list = new ArrayList<>();
		list.add("abc");
		System.out.println(list);
		formatList(list);
		System.out.println(list);
		restList(list);
		System.out.println(list);
	}


	private void formatString(String str) {
		str = "456";
	}

	private void formatList(@NotNull List<String> list) {
		list.add("666");
	}

	private void restList(List<String> list) {
		List<String> list1 = new ArrayList<>();
		list1.add("pppp");
		list = list1;
	}


}
