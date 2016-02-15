package org.alvarogp.nettop.metric.data.repository;

import org.alvarogp.nettop.metric.data.repository.datasource.AndroidApiMetricDataSource;
import org.alvarogp.nettop.metric.data.repository.datasource.MetricDataSource;
import org.alvarogp.nettop.metric.domain.model.metric.list.MetricList;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;
import org.alvarogp.nettop.metric.domain.repository.MetricRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class MetricDataRepository implements MetricRepository {
    private final MetricDataSource metricDataSource;

    @Inject
    public MetricDataRepository(AndroidApiMetricDataSource metricDataSource) {
        this.metricDataSource = metricDataSource;
    }

    @Override
    public Observable<OwnerList> owners() {
        return metricDataSource.owners();
    }

    @Override
    public Observable<MetricList> metrics(Observable<OwnerList> owners) {
        return metricDataSource.metrics(owners);
    }
}
