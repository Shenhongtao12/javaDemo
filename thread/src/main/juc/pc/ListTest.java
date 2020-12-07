package main.juc.pc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Aaron
 * @date 2020/12/7 23:09
 */
public class ListTest {

    /**
     * 保证多线程时，list安全的三种方式
     * 1、new Vector<>()
     * 2、Collections.synchronizedList(new ArrayList<>())
     * 3、new CopyOnWriteArrayList<>()
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
    }
}
