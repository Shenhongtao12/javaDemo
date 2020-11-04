package com.example.es;

import com.example.es.entity.Item;
import com.example.es.repository.ItemRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(classes = EsApplication.class)
class EsApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void createIndex() {
        restTemplate.indexOps(Item.class);
        restTemplate.indexOps(Item.class).createMapping(Item.class);
    }

    @Test
    void deleteIndex() {
        boolean flag = restTemplate.indexOps(Item.class).delete();
        System.out.println("------------ " + flag);
    }

    @Test
    public void save() {
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);
    }

    @Test
    public void saveList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    @Test
    public void findAll() {
        Iterable<Item> items = itemRepository.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void findByPage() {
        Page<Item> itemPage = itemRepository.findAll(PageRequest.of(0, 2));
        // 打印总条数
        System.out.println("总条数: " + itemPage.getTotalElements());
        // 打印总页数
        System.out.println("总页数: " + itemPage.getTotalPages());
        for (Item item : itemPage) {
            System.out.println(item);
        }
    }

    @Test
    public void nativeSearchQueryBuilderTest() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "小米"));
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        SearchHits<Item> searchHits = restTemplate.search(queryBuilder.build(), Item.class);
        for (SearchHit<Item> searchHit : searchHits) {
            System.out.println(searchHit);
        }
    }

    @Test
    public void aggsQueryTest() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand")
        .subAggregation(AggregationBuilders.avg("priceAvg").field("price")));
        SearchHits<Item> searchHits = restTemplate.search(queryBuilder.build(), Item.class);
        Map<String, Aggregation> asMap = searchHits.getAggregations().getAsMap();
        ParsedStringTerms parsedStringTerms = (ParsedStringTerms) asMap.get("brands");
        List<String> list = parsedStringTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
        List<Long> longs = parsedStringTerms.getBuckets().stream().map(bucket -> bucket.getDocCount()).collect(Collectors.toList());

        /*ParsedStringTerms priceAvg = (ParsedStringTerms) asMap.get("priceAvg");
        List<String> priceList = priceAvg.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
        */for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + " : " + longs.get(i));
            //System.out.println("平均价格: " + priceList.get(i));
        }



    }

}
