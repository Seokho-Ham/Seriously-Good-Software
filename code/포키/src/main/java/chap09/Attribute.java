package chap09;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Attribute<V, S> {

    static <V, S> Attribute<V, S> of(Supplier<S> supplier,
                                     BiConsumer<S, V> updater,
                                     BinaryOperator<S> combiner,
                                     Function<S, V> finisher) {
        return new Attribute<V, S>() {
            @Override
            public S seed() {
                return supplier.get();
            }

            @Override
            public void update(S summary, V value) {
                updater.accept(summary, value);
            }

            @Override
            public S merge(S summary1, S summary2) {
                return combiner.apply(summary1, summary2);
            }

            @Override
            public V report(S summary) {
                return finisher.apply(summary);
            }
        };
    }

    S seed();

    void update(S summary, V value);

    S merge(S summary1, S summary2);

    V report(S summary);
}
