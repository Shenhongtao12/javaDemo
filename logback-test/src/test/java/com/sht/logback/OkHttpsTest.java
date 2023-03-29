package com.sht.logback;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author as2i
 * @date 2023/3/29 11:02
 */
public class OkHttpsTest {
		public HTTP http = HTTP.builder().baseUrl("http://apis-gdev.newegg.org/content/v1").build();


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
}
