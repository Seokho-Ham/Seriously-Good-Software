package chap09;

import chap03.Speed3_Container;

public class UnionFindNode<V, S> implements ContainerLike<V, UnionFindNode<V, S>> {

    private UnionFindNode<V, S> parent = this;
    private int groupSize = 1;
    private final Attribute<V, S> attribute;
    private S summary;

    public UnionFindNode(Attribute<V, S> dom) {
        this.attribute = dom;
        summary = dom.seed();
    }

    @Override
    public V get() {
        UnionFindNode<V, S> root = findRootAndCompress();
        return attribute.report(root.summary);
    }

    @Override
    public void update(V value) {
        UnionFindNode<V, S> root = findRootAndCompress();
        attribute.update(root.summary, value);
    }

    @Override
    public void connectTo(UnionFindNode<V, S> other) {
        UnionFindNode<V, S> root1 = findRootAndCompress();
        UnionFindNode<V, S> root2 = other.findRootAndCompress();

        if (root1 == root2) {
            return;
        }

        int size1 = root1.groupSize;
        int size2 = root2.groupSize;
        S newSummary = attribute.merge(root1.summary, root2.summary);

        if (size1 <= size2) {
            root1.parent = root2;
            root2.summary = newSummary;
            root2.groupSize += size1;
        }else{
            root2.parent = root1;
            root1.summary = newSummary;
            root1.groupSize += size2;
        }
    }

    private UnionFindNode<V, S> findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }
        return parent;
    }
}
