package chap09;

public interface ContainerLike<V, T extends ContainerLike<V, T>> {

    V get();

    void update(V value);

    void connectTo(T other);
}
