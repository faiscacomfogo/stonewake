package dev.stonewake.utils;

public class NamespacedKey {
    private final String namespace;
    private final String key;

    public NamespacedKey(String namespace, String key) {
        this.namespace = namespace.toLowerCase();
        this.key = key.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof String str) {
            return this.toString().equals(str);
        }

        if (o instanceof NamespacedKey that) {
            return namespace.equals(that.namespace) && key.equals(that.key);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(namespace, key);
    }

    @Override
    public String toString() {
        return namespace + ":" + key;
    }
}