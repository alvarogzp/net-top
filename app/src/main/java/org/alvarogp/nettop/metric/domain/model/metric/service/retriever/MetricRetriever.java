package org.alvarogp.nettop.metric.domain.model.metric.service.retriever;

import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.metric.domain.model.metric.list.MetricList;
import org.alvarogp.nettop.metric.domain.model.metric.transform.comparator.MetricComparator;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.MetricFilter;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.MetricMapper;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;
import org.alvarogp.nettop.metric.domain.repository.MetricRepository;

import javax.inject.Inject;

import rx.Observable;

public class MetricRetriever {
    private final Logger logger;
    private final MetricRepository metricRepository;

    @Inject
    public MetricRetriever(Logger logger, MetricRepository metricRepository) {
        this.logger = logger;
        this.metricRepository = metricRepository;
    }

    public Observable<MetricList> metrics(Observable<OwnerList> owners,
                                          MetricMapper metricMapper,
                                          MetricFilter metricFilter,
                                          MetricComparator metricComparator) {
        return metricRepository.metrics(owners)
                .doOnNext(metricList -> {
                    logger.debug(this, "Start metrics manipulation");
                    metricList.map(metricMapper);
                    metricList.filter(metricFilter);
                    metricList.sort(metricComparator);
                    logger.debug(this, "Ended metrics manipulation");
                });
    }
}
