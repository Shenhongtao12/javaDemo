package main.juc.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Aaron
 * @date 2021/1/1 15:20
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>(50);

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
               map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().replace("-", ""));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
