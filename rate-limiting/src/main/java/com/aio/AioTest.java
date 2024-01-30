package com.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AioTest {
    public static void main(String[] args) {
        Path filePath = Paths.get("README.md");

        try {
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                    filePath, StandardOpenOption.READ);

            ByteBuffer buffer = ByteBuffer.allocate(3);

            // 注入回调代码
            fileChannel.read(buffer, 0, null, new CompletionHandler<Integer, Void>() {
                @Override
                public void completed(Integer result, Void attachment) {
                    // IO操作完成时调用的回调函数
                    System.out.println("Read " + result + " bytes from file. Thread: " + Thread.currentThread().getName());
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    // IO操作失败时调用的回调函数
                    System.err.println("Error reading from file. Thread: " + Thread.currentThread().getName() +
                            ". Error message: " + exc.getMessage());
                }
            });

            // 用户线程继续执行其他任务
            System.out.println("主线程：" + Thread.currentThread().getName());
            // 等待异步IO操作完成
            // 由于异步IO是非阻塞的，这里可以加入一些等待机制
            Thread.sleep(1000000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
