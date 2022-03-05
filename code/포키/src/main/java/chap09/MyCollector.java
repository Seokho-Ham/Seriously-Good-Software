package chap09;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector.*;

public interface MyCollector<V, S, R> {

    Supplier<S> supplier();

    BiConsumer<S, V> accumulator();

    BinaryOperator<S> combiner();

    Function<S, R> finisher();

    Set<Characteristics> characteristics();
}
