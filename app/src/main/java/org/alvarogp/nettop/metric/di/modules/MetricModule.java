package org.alvarogp.nettop.metric.di.modules;

import org.alvarogp.nettop.common.di.scopes.PerActivity;
import org.alvarogp.nettop.common.domain.usecase.UseCase;
import org.alvarogp.nettop.metric.di.qualifiers.SelectUseCase;
import org.alvarogp.nettop.metric.domain.interactors.getmetrics.GetMetrics;
import org.alvarogp.nettop.metric.domain.model.metric.transform.comparator.MetricComparator;
import org.alvarogp.nettop.metric.domain.model.metric.transform.comparator.ValueMetricComparator;
import org.alvarogp.nettop.metric.domain.model.owner.transform.comparator.CompoundOwnerComparator;
import org.alvarogp.nettop.metric.domain.model.owner.transform.comparator.CurrentOwnerComparator;
import org.alvarogp.nettop.metric.domain.model.owner.transform.comparator.IdOwnerComparator;
import org.alvarogp.nettop.metric.domain.model.owner.transform.comparator.OwnerComparator;

import dagger.Module;
import dagger.Provides;

import static org.alvarogp.nettop.metric.di.qualifiers.SelectUseCase.UseCase.METRICS;

@Module
public class MetricModule {
    @Provides @PerActivity @SelectUseCase(METRICS)
    UseCase provideGetMetricsUseCase(GetMetrics getMetrics) {
        return getMetrics;
    }

    @Provides @PerActivity
    OwnerComparator provideOwnerComparator(CurrentOwnerComparator currentOwnerComparator,
                                           IdOwnerComparator idOwnerComparator) {
        return new CompoundOwnerComparator(currentOwnerComparator, idOwnerComparator);
    }

    @Provides @PerActivity
    MetricComparator provideMetricComparator(ValueMetricComparator valueMetricComparator) {
        return valueMetricComparator;
    }
}
