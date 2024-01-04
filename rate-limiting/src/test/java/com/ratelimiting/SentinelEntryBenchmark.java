package com.ratelimiting;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Fork(1)
@Warmup(iterations = 5)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class SentinelEntryBenchmark {
    @Param({"25", "50", "100", "500", "1000"})
    private int length;
    private List<Integer> numbers;

    @Setup
    public void prepare() {
        numbers = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt());
        }
    }

    @Benchmark
    @Threads(8)
    public void doSomething() {
        Collections.shuffle(numbers);
        Collections.sort(numbers);
    }

    @Benchmark
    @Threads(8)
    public void doSomethingWithEntry() {
        Entry e0 = null;
        try {
            e0 = SphU.entry("benchmark");
            doSomething();
        } catch (BlockException e) {
            throw new RuntimeException(e);
        } finally {
            if (e0 != null) {
                e0.exit();
            }
        }

    }
}
