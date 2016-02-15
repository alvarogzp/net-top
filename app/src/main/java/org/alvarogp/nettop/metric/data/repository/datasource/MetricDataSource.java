package org.alvarogp.nettop.metric.data.repository.datasource;

import org.alvarogp.nettop.metric.domain.model.metric.list.MetricList;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;

import rx.Observable;

public interface MetricDataSource {
    Observable<OwnerList> owners();
    Observable<MetricList> metrics(Observable<OwnerList> owners);
}
