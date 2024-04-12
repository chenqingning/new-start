package com.example.basejava.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LinkageExample {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(8);
        list1.add(0);
        list1.add(11);

        List<String> list2 = new ArrayList<>();
        list2.add("C");
        list2.add("A");
        list2.add("B");

        List<Double> list3 = new ArrayList<>();
        list3.add(3.3);
        list3.add(1.1);
        list3.add(2.2);

        // 创建一个辅助类用于存储排序后的元素索引
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            indices.add(i);
        }

        // 使用自定义比较器对第一个列表进行排序，并同时调整第二个和第三个列表的顺序
        Collections.sort(indices, Comparator.comparingInt(list1::get).reversed());
        List<Integer> sortedList1 = new ArrayList<>();
        List<String> sortedList2 = new ArrayList<>();
        List<Double> sortedList3 = new ArrayList<>();
        for (int index : indices) {
            sortedList1.add(list1.get(index));
            sortedList2.add(list2.get(index));
            sortedList3.add(list3.get(index));
        }

        // 更新第二个和第三个列表的内容
        list1.clear();
        list2.clear();
        list3.clear();
        list1.addAll(sortedList1);
        list2.addAll(sortedList2);
        list3.addAll(sortedList3);

        // 打印排序后的结果
        System.out.println("List1: " + list1); // 输出：List1: [1, 2, 3]
        System.out.println("List2: " + list2); // 输出：List2: [A, B, C]
        System.out.println("List3: " + list3); // 输出：List3: [1.1, 2.2, 3.3]
    }
}
