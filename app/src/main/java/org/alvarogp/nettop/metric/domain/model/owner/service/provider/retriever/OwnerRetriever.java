package org.alvarogp.nettop.metric.domain.model.owner.service.provider.retriever;

import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;
import org.alvarogp.nettop.metric.domain.model.owner.transform.comparator.OwnerComparator;
import org.alvarogp.nettop.metric.domain.repository.MetricRepository;

import javax.inject.Inject;

import rx.Observable;

public class OwnerRetriever {
    private final MetricRepository metricRepository;
    private final OwnerComparator ownerComparator;

    @Inject
    public OwnerRetriever(MetricRepository metricRepository, OwnerComparator ownerComparator) {
        this.metricRepository = metricRepository;
        this.ownerComparator = ownerComparator;
    }

    public Observable<OwnerList> retrieveOwners() {
        return metricRepository.owners()
                .doOnNext((ownerList) -> {
                    ownerList.distinct();
                    ownerList.sort(ownerComparator);
                });
    }
}
