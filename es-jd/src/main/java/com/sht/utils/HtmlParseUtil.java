package com.sht.utils;

import com.sht.entity.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aaron
 * @date 2020/11/2 22:58
 */
@Component
public class HtmlParseUtil {

    public static void main(String[] args) throws Exception {
        new HtmlParseUtil().parseJd("java").forEach(System.out::println);

    }

    public List<Content> parseJd(String keyword) throws Exception {
        //获取请求 https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        // 解析网页(返回的就是浏览器Document的对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 所有在js中使用的方法，在这里都可以使用
        Element element = document.getElementById("J_goodsList");
        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();
        // 获取元素中的内容
        for (Element el : elements) {
            // 图片懒加载
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
           goodsList.add(new Content(title, price, img));

        }
        return goodsList;
    }
}
