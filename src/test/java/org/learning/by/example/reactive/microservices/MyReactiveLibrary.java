package org.learning.by.example.reactive.microservices;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.context.Context;

import java.time.Duration;
import java.util.function.LongConsumer;

public class MyReactiveLibrary {

  public Flux<String> alphabet5(char from) {
     return Flux.range((int) from, 5)
           .map(i -> "" + (char) i.intValue());
  }

  public Mono<String> withDelay(String value, int delaySeconds) {
     return Mono.just(value)
                .delaySubscription(Duration.ofSeconds(delaySeconds));
  }

    @Test
    public void testAlphabet5LimitsToZ() {
        MyReactiveLibrary library = new MyReactiveLibrary();
        StepVerifier.create(library.alphabet5('x'))
                .expectNext("x", "y", "z")
                .expectComplete()
                .verify();
    }

    @Test
    public void testAlphabet5LastItemIsAlphabeticalChar() {
        Flux<PriceTick> flux = Flux.create(emitter -> System.out.println(emitter), FluxSink.OverflowStrategy.BUFFER);
        ConnectableFlux<PriceTick> hot = flux.publish();
    }

    class PriceTick implements FluxSink<String>{
        @Override
        public void complete() {

        }

        @Override
        public Context currentContext() {
            return null;
        }

        @Override
        public void error(Throwable throwable) {

        }

        @Override
        public FluxSink<String> next(String o) {
            return null;
        }

        @Override
        public long requestedFromDownstream() {
            return 0;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public FluxSink<String> onRequest(LongConsumer longConsumer) {
            return null;
        }

        @Override
        public FluxSink<String> onCancel(Disposable disposable) {
            return null;
        }

        @Override
        public FluxSink<String> onDispose(Disposable disposable) {
            return null;
        }
    }
}