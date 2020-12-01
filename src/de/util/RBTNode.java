package de.util;

import java.util.Objects;

public class RBTNode<T> {
    public static final boolean black = false;
    public static final boolean red = true;

    public int key;
    public T val;
    public Boolean color;
    public RBTNode<T> left, right, parent;

    public RBTNode(int k, T v) {
        this.key = k;
        this.val = v;
        this.color = red;
        if (v == null) {
            this.color = black;
        }
    }

    public RBTNode(RBTNode<T> x) {
        this.key = x.key;
        this.val = x.val;
        this.color = x.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RBTNode<?> rbtNode = (RBTNode<?>) o;
        return key == rbtNode.key &&
                Objects.equals(val, rbtNode.val) &&
                Objects.equals(color, rbtNode.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, val, color, left, right, parent);
    }

    @Override
    public String toString() {
        return "RBTNode{" +
                "key=" + key +
                ", val=" + val +
                ", color=" + color +
                '}';
    }
}
