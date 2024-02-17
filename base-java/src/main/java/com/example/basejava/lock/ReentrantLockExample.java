package com.example.basejava.lock;

import com.sun.corba.se.impl.interceptors.PICurrent;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private static final int WORKER_COUNT = 3;
    private static int countIndex = 0;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        final List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < WORKER_COUNT; i++) {
            Condition condition = LOCK.newCondition();
            conditions.add(condition);
            Worker worker = new Worker(i, conditions);
            worker.start();

        }
    }

    public static class Worker extends Thread {
        int index;
        List<Condition> conditions;

        Worker(int index, List<Condition> conditions) {
            this.conditions = conditions;
            this.index = index;
        }

        void signalNext() {
            int nextIndex = (index + 1) % conditions.size();
            conditions.get(nextIndex).signal();
        }

        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    if (countIndex % 3 != index) {
                        conditions.get(index).await();
                    }
                    if (countIndex > 100) {
                        signalNext();
                        return;
                    }
                    System.out.println(this.getName() + ":" + countIndex);
                    countIndex++;
                    signalNext();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }
}
