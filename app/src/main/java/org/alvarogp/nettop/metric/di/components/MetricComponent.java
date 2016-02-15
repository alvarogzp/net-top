package org.alvarogp.nettop.metric.di.components;

import org.alvarogp.nettop.common.di.components.ActivityComponent;
import org.alvarogp.nettop.common.di.components.ApplicationComponent;
import org.alvarogp.nettop.common.di.modules.ActivityModule;
import org.alvarogp.nettop.common.di.scopes.PerActivity;
import org.alvarogp.nettop.metric.di.modules.MetricModule;
import org.alvarogp.nettop.metric.presentation.view.android.activity.MetricsActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MetricModule.class})
public interface MetricComponent extends ActivityComponent {
    void inject(MetricsActivity metricsActivity);
}
