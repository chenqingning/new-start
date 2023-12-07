package com.ratelimiting;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class MyTestBenchmark {
    @Measurement(iterations = 5) //测量次数以及每次测试的持续时间，测量次数越多且每次测量的持续时间越长，测试结果的可信度就越高。一次执行testFunction方法的次数是不固定的。
    @BenchmarkMode(Mode.AverageTime)  //该注释可以测出某个接口的吞吐量、平均执行等指标的数据
    @Benchmark //该注释是基准测试方法
    @Warmup(iterations = 3) //预热：可以提升测试结果的准确度
    @OutputTimeUnit(TimeUnit.MINUTES) //输出方法执行耗时的单位
    @Fork(1) //fork注解用于指定Fork多少个子进程来执行同一基准测试方法
    @Threads(2) //指定使用多少个线程来执行基准测试方法，一般用来测试吞吐量 跟@Fork的关系-是一个子进程有两个线程嘛？

    @Test
    public void testFunction() {

    }

}
