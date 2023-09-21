package com.sht.logback;

import cn.zhxu.okhttps.AHttpTask;
import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author as2i
 * @date 2023/3/29 11:02
 */
public class OkHttpsTest {
		public HTTP http = HTTP.builder().baseUrl("http://apis-gdev.newegg.org/content/v1").build();


		@Test
		public void test() {
			System.out.println(Math.round(Math.random() * 3));
//			for (int i = 0; i < 100; i++) {
//				HttpResult.Body body1 = OkHttps.async("http://localhost:8080/kindy-system/kindy/user?aaa=bbb&ccc=ddd").get().getResult().getBody();
//				System.out.println(body1.toString());
//			}
		}

	@Test
	public void getTest() {
//		HttpResult.Body body = http.async("/item/product/66-666-005").get().getResult().getBody();
//		System.out.println(body.toString());
		// 异步请求
		HttpResult.Body body = http.async("/item/product/66-666-005").get().getTask().addHeader("Accept", "application/json").get().getResult().getBody();
		System.out.println(body.toString());

		// 同步请求
		HttpResult.Body accept = http.sync("/item/product/66-666-005").addHeader("Accept", "application/json").get().getBody();
		System.out.println("2: " + accept);
	}

	@Test
	public void postTest() {
		HashMap<String, Object> itemList = new HashMap<String, Object>(10);
		itemList.put("ItemNumber", "66-666-005");
		itemList.put("CountryCode", "USA");
		itemList.put("CompanyCode", 1003);
		ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
		arrayList.add(itemList);
		HashMap<String, Object> request = new HashMap<String, Object>(10);
		request.put("itemList", arrayList);
		// 异步请求
		OkHttps.async("http://apis-gdev.newegg.org/content/v1/item/item-owner/list")
				.bodyType("json")
				.setBodyPara(request)
				.addHeader("Accept", "application/json")
				.setOnResBody((HttpResult.Body res) -> {
					System.out.println("------------- " + res);
				}).post().getResult();
		System.out.println("=============================");

	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(100);

		for (int i = 1; i <= 50; i++) {
			executor.execute(new InventoryRequestTask("线程" + i));
		}

		executor.shutdown();
	}

	static class InventoryRequestTask implements Runnable {

			private String name;

			public InventoryRequestTask(String name) {
				this.name = name;
			}
		@Override
		public void run() {
			long start = System.currentTimeMillis();
			HttpResult.Body body1 = OkHttps.async("http://localhost:8080/system/config/deductionInventory?name=" + name)
					.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX2tleSI6IjdkN2ZjZjA3LTQwOGQtNGEzYS04YzVkLWQ2Yzk1M2M2MWFjMiIsInVzZXJuYW1lIjoiYWRtaW4ifQ.-ljAkX3Ew9YcPu_zY-boy2RTrn25JKO7V6LuU77K0A_AtNYsj8_gDkqweGQJxi9raXFPVX1SZEy_3kRoQTImkg")
					.get().getResult().getBody();
			System.out.println(name + " : " +body1.toString() + "    耗时： " + (System.currentTimeMillis() - start));
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
