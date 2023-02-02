package com.sht.logback;

import com.sht.logback.utils.HttpUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author as2i
 * @date 2023/2/1 11:04
 */
@SpringBootTest
public class HttpTest {

	@Test
	public void sendGet() {
		String url = "http://localhost:16239/dev/item/product/66-666-005";
		String s = HttpUtils.sendGet(url);
		System.out.println(s);
	}

	@Test
	public void sendPost() throws JSONException {
		String url = "http://localhost:16239/dev/item/item-owner/list";
		HashMap<String, Object> itemList = new HashMap<String, Object>(10);
		itemList.put("ItemNumber", "66-666-005");
		itemList.put("CountryCode", "USA");
		itemList.put("CompanyCode", 1003);
		ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
		arrayList.add(itemList);
		JSONObject request = new JSONObject();
		request.put("itemList", arrayList);
		String s = HttpUtils.sendPost(url, "request");
		System.out.println(s);
	}
}
