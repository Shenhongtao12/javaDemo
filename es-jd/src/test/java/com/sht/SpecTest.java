package com.sht;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sht.entity.Content;
import com.sht.utils.HtmlParseUtil;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

//@SpringBootTest
class SpecTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void testCreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("jd_goods");
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void CallApi() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        List<Content> contents = HtmlParseUtil.parseJd("机器人");
        for (int i = 0; i < contents.size(); i++) {

            Content content = contents.get(i);
            System.out.println(content);
            if (content.getTitle() != null && !"".equals(content.getTitle())) {
                int j = 1;
                String Url = content.getImages();
                while ((i+j < contents.size()) && (contents.get(i+j).getTitle() == null || "".equals(contents.get(i+j).getTitle()))) {
                    Url = Url + "," + contents.get(i+j).getImages();
                    //System.out.println("---- " + Url + ",");
                    j++;
                }

                content.setImages(Url);
                System.out.println(content);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", content.getTitle().substring(0, 30));
                jsonObject.put("intro", content.getTitle());
                jsonObject.put("price1", content.getPrice().substring(1, Math.min(content.getPrice().length(), 7)));
                jsonObject.put("images", content.getImages());
                jsonObject.put("state", 0);
                jsonObject.put("classify2_id", 2);
                jsonObject.put("userid", 3);
                jsonObject.put("weixin", "eurasia.plus");
                //System.out.println(jsonObject.toString());
                restTemplate.postForEntity("http://localhost:8086/api/goods/add", jsonObject, String.class);
            }
        }

    }

    @Test
    public void test() {
        int i = 0;
        while (i < 10) {
            System.out.println(i);
            i++;
        }

        String b = "￥123.00  %%";
        System.out.println(b.substring(1,7));

    }


}
