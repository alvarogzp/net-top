package org.alvarogp.nettop.metric.domain.interactors.getmetrics;

import org.alvarogp.nettop.common.domain.executor.SingleThreadExecutionScheduler;
import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.common.domain.usecase.UseCase;
import org.alvarogp.nettop.common.domain.usecase.executable.Executable;
import org.alvarogp.nettop.common.domain.usecase.executable.ExecutableFactory;
import org.alvarogp.nettop.metric.domain.model.metric.service.retriever.MetricRetriever;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;
import org.alvarogp.nettop.metric.domain.model.owner.service.provider.OwnerProvider;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class GetMetrics implements UseCase<GetMetricsRequest, GetMetricsResponse> {
    private final Logger logger;
    private final OwnerProvider ownerProvider;
    private final MetricRetriever metricRetriever;
    private final Scheduler singleThreadExecutionScheduler;
    private final ExecutableFactory executableFactory;

    @Inject
    public GetMetrics(Logger logger,
                      OwnerProvider ownerProvider,
                      MetricRetriever metricRetriever,
                      SingleThreadExecutionScheduler singleThreadExecutionScheduler,
                      ExecutableFactory executableFactory) {
        this.logger = logger;
        this.ownerProvider = ownerProvider;
        this.metricRetriever = metricRetriever;
        this.singleThreadExecutionScheduler = singleThreadExecutionScheduler.getScheduler();
        this.executableFactory = executableFactory;
    }

    @Override
    public Executable<GetMetricsResponse> getExecutable(GetMetricsRequest request) {
        Observable<OwnerList> owners = ownerProvider.owners();
        Observable<GetMetricsResponse> observable = metricRetriever.metrics(owners, request.getMetricMapper(), request.getMetricFilter(), request.getMetricComparator())
                .map(GetMetricsResponse::new)
                .subscribeOn(singleThreadExecutionScheduler)
                .doOnSubscribe(() -> logger.debug(this, "Get Metrics scheduled"))
                .doOnUnsubscribe(() -> logger.debug(this, "Get Metrics end"));
        return executableFactory.createWithObservable(observable);
    }
}
