package com.ratelimiting.flux;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FluxTest {
    public static void main(String[] args) {

        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .filter(i -> true)
                .publishOn(s)
                .map(i -> "value " + i);
        flux.subscribe(System.out::println);
        // 创建两个 Flux
        Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<String> flux2 = Flux.just("X", "Y", "Z");

        // 创建包含两个 Flux 的 Flux
        Flux<Flux<String>> fluxOfFlux = Flux.just(flux1, flux2);
        flux1.subscribe(System.out::println);
        // 使用 switchOnNext 操作符将嵌套的 Flux 平铺成单一的 Flux
        Flux<String> resultFlux = Flux.switchOnNext(fluxOfFlux);

        // 订阅并输出元素
        resultFlux.subscribe(System.out::println);

        // 切换到新的 Publisher
        Flux<String> flux3 = Flux.just("K", "H", "V");
        Flux<String> flux4 = Flux.just("K1", "H1", "V1");
        fluxOfFlux = Flux.just(flux4, flux3);
        resultFlux.switchOnNext(fluxOfFlux);

        // 订阅并输出新的元素
        // resultFlux.subscribe(System.out::println);
    }

    public static void main1(String[] args) throws InterruptedException {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);
        seq2.subscribe(t -> System.out.println("打印" + Thread.currentThread().getName() + ":" + t));
        System.out.println("隔行");
        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(ss);


        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .publishOn(s)
                .map(i -> "value " + i);

        new Thread(() -> flux.subscribe((t) -> System.out.println(t + ":" + Thread.currentThread().getName()))).start();

        System.out.println("隔行1");
        final Flux<String> other = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .publishOn(s)
                .map(i -> "value " + i);
        flux.switchOnNext(System.out::println);


        // 创建一个 Sinks.Many
        Sinks.Many<Integer> sink = Sinks.many().unicast().onBackpressureBuffer();

        // 创建一个 Flux
        Flux<Integer> sinkFlux = sink.asFlux();
        sink.tryEmitNext(1);
        // 订阅并输出元素
        sinkFlux.subscribe(System.out::println);

        // 动态添加元素
        sink.tryEmitNext(1);
        sink.tryEmitNext(2);


        AtomicReference<List<Integer>> state = new AtomicReference<>(Arrays.asList(17, 27, 37));

        Flux<Integer> deferFlux = Flux.defer(() -> Flux.fromIterable(state.get()));

        deferFlux.subscribe(System.out::println);

// 在运行时动态添加元素
        List<Integer> newState = new ArrayList<>(state.get());
        newState.add(4);
        state.set(newState);


        DirectProcessor<Integer> processor = DirectProcessor.create();
        Flux<Integer> flux3 = processor;
        processor.onNext(18);
        flux3.subscribe(System.out::println);

        processor.onNext(19);
        processor.onNext(20);

    }

    public static class SampleSubscriber<T> extends BaseSubscriber<T> {

        @Override
        public void hookOnSubscribe(Subscription subscription) {
            System.out.println(subscription.getClass());
            System.out.println("Subscribed");
            request(1);
        }

        @Override
        public void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }
}
