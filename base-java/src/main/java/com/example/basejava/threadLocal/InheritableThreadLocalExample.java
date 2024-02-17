package com.example.basejava.threadLocal;

public class InheritableThreadLocalExample {
    static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("main Thread Value");
        Thread childThread = new Thread(() -> {
            System.out.println("child Thread value:" + threadLocal.get());

        });
        childThread.start();
        System.out.println("Main Thread Value:" + threadLocal.get());
    }
}
