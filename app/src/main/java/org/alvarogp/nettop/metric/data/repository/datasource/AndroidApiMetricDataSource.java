package org.alvarogp.nettop.metric.data.repository.datasource;

import org.alvarogp.nettop.metric.data.android.metric.GetMetricFromOwner;
import org.alvarogp.nettop.metric.data.android.owner.ApplicationInfoToOwner;
import org.alvarogp.nettop.metric.data.android.owner.InstalledApplications;
import org.alvarogp.nettop.metric.data.android.owner.TotalOwnerFactory;
import org.alvarogp.nettop.metric.domain.model.metric.list.MetricList;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;

import javax.inject.Inject;

import rx.Observable;

public class AndroidApiMetricDataSource implements MetricDataSource {
    private final InstalledApplications installedApplications;
    private final ApplicationInfoToOwner applicationInfoToOwner;
    private final GetMetricFromOwner getMetricFromOwner;
    private final TotalOwnerFactory totalOwnerFactory;

    @Inject
    public AndroidApiMetricDataSource(InstalledApplications installedApplications,
                                      ApplicationInfoToOwner applicationInfoToOwner,
                                      GetMetricFromOwner getMetricFromOwner,
                                      TotalOwnerFactory totalOwnerFactory) {
        this.installedApplications = installedApplications;
        this.applicationInfoToOwner = applicationInfoToOwner;
        this.getMetricFromOwner = getMetricFromOwner;
        this.totalOwnerFactory = totalOwnerFactory;
    }

    @Override
    public Observable<OwnerList> owners() {
        return installedApplications.installedApplications()
                .map(applicationInfoToOwner)
                .toList()
                .map((owners) -> new OwnerList(totalOwnerFactory.getTotalOwner(), owners));
    }

    @Override
    public Observable<MetricList> metrics(Observable<OwnerList> owners) {
        return owners.map(getMetricFromOwner);
    }
}
