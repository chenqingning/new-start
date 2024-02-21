package com.example.basejava.jvmCommand;

/**
 * 使用jstack打印线程dump文件
 * jstack pid
 * -l print additional info such locks
 * -m mixed mode(java thread and native thread)
 */
public class JstackExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new Thread1());
        thread.start();
    }

    static class Thread1 implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println(1);
            }
        }
    }
}
