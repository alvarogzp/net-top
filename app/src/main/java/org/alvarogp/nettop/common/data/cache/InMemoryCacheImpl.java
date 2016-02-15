package org.alvarogp.nettop.common.data.cache;

import org.alvarogp.nettop.common.domain.cache.Cache;

import java.util.NoSuchElementException;

import javax.inject.Inject;

public class InMemoryCacheImpl<T> implements Cache<T> {
    private T element;
    private boolean isCached;

    @Inject
    public InMemoryCacheImpl() {}

    @Override
    public T get() {
        if (!isCached) {
            throw new NoSuchElementException("no element cached");
        }
        return element;
    }

    @Override
    public void put(T element) {
        this.element = element;
        isCached = true;
    }

    @Override
    public void clear() {
        element = null; // prevent memory leak
        isCached = false;
    }

    @Override
    public boolean isCached() {
        return isCached;
    }
}
