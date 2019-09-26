package com.yuan;
import com.yuan.solution.LRUCache;

import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws Exception {
        LRUCache lruCache = new LRUCache(2);
        try {
            lruCache.get(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lruCache.set(2, 2);
        lruCache.set(3, 1);
        int idx = lruCache.get(2);
        lruCache.set(4, 2);
        System.out.println("ct");

        // 堆，默认小顶
        PriorityQueue<Integer> bigEnd = new PriorityQueue<>(Comparator.reverseOrder());
        bigEnd.add(1);
        bigEnd.add(2);
        bigEnd.add(3);
        Integer poll = bigEnd.poll();

        // random用法
        Random random = new Random();
        random.nextInt(100);


    }

    public static int exc() {
        int i = 0;
        try {
            i++;
            throw new Exception(String.valueOf(i));
        } catch (Exception e) {
            return i++;
        } finally {
            i++;
            System.out.println(i);
        }
    }
}

