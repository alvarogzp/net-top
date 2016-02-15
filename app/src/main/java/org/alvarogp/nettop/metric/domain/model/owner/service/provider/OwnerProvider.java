package org.alvarogp.nettop.metric.domain.model.owner.service.provider;

import org.alvarogp.nettop.common.domain.cache.Cache;
import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;
import org.alvarogp.nettop.metric.domain.model.owner.service.provider.retriever.OwnerRetriever;

import javax.inject.Inject;

import rx.Observable;

public class OwnerProvider {
    private final Logger logger;
    private final OwnerRetriever ownerRetriever;
    private final Cache<OwnerList> ownerListCache;

    @Inject
    public OwnerProvider(Logger logger, OwnerRetriever ownerRetriever, Cache<OwnerList> ownerListCache) {
        this.logger = logger;
        this.ownerRetriever = ownerRetriever;
        this.ownerListCache = ownerListCache;
    }

    public Observable<OwnerList> owners() {
        return Observable.defer(() -> {
            logger.debug(this, "Creating observable to get owners");
            if (ownerListCache.isCached()) {
                return cachedOwners();
            } else {
                return retrievedOwners();
            }
        });
    }

    private Observable<OwnerList> cachedOwners() {
        return Observable.just(ownerListCache.get());
    }

    private Observable<OwnerList> retrievedOwners() {
        return ownerRetriever.retrieveOwners()
                .doOnNext(ownerListCache::put);
    }
}
