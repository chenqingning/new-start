package com.example.basejava.hash;

import java.util.Hashtable;

/**
 * hashtable是线程安全的
 * Map有一个内部接口Entry，是map的collection-view
 */
public class HashTableExample {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable<>();
        hashtable.put("aa", "bb");
    }
}
