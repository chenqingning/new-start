package com.example.basejava.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolCorrespondExample {
    /**
     * 父子进程间的值获取
     */
    public void ParentAndChild() {
        TransmittableThreadLocal context = new TransmittableThreadLocal();
        context.set("value-set-in-parent");
        Thread childThread = new Thread(() -> {
            System.out.println(context.get());
        });

    }

    /**
     * 线程池间的值获取
     */
    public void threadPool() {
        TransmittableThreadLocal context = new TransmittableThreadLocal();
        context.set("value-set-in-parent");
        Runnable task = new RunnaleTask();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(task);
    }
}
