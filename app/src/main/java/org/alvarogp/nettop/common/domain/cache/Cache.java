package org.alvarogp.nettop.common.domain.cache;

public interface Cache<T> {
    T get();
    void put(T element);
    void clear();
    boolean isCached();
}
