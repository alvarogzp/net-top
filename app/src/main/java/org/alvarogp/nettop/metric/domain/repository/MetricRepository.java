package org.alvarogp.nettop.metric.domain.repository;

import org.alvarogp.nettop.metric.domain.model.metric.list.MetricList;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;

import rx.Observable;

public interface MetricRepository {
    /**
     * Get available metric owners (eg. processes which metrics can be obtained separately).
     *
     * @return The list of available owners, as an Observable
     */
    Observable<OwnerList> owners();

    /**
     * Get the current metrics for the given owners.
     *
     * @param owners The owners which metrics you want, see {@link #owners()}
     * @return The current metrics once and the Observable ends
     */
    Observable<MetricList> metrics(Observable<OwnerList> owners);
}
