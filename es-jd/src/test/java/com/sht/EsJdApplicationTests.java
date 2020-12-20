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
class EsJdApplicationTests {

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
        List<Content> contents = HtmlParseUtil.parseJd("耐克鞋子");
        for (int i = 0; i <= contents.size(); i++) {

            Content content = contents.get(i);
            if (content.getTitle() != null && !"".equals(content.getTitle())) {
                int j = 1;
                String Url = content.getImages();
                while (contents.get(i+j).getTitle() == null || "".equals(contents.get(i+j).getTitle())) {
                    Url = Url + "," + contents.get(i+j).getImages();
                    //System.out.println("---- " + Url + ",");
                    j++;
                }

                content.setImages(Url);
                System.out.println(content);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", content.getTitle());
                jsonObject.put("price", content.getPrice().substring(1, Math.min(content.getPrice().length(), 7)));
                jsonObject.put("images", content.getImages());
                jsonObject.put("brand", content.getBrand());
                //System.out.println(jsonObject.toString());
                restTemplate.postForEntity("http://localhost:8086/api/goods", jsonObject, String.class);
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
