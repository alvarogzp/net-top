package org.alvarogp.nettop.common.di.components;

import org.alvarogp.nettop.common.di.modules.ApplicationModule;
import org.alvarogp.nettop.common.domain.cache.Cache;
import org.alvarogp.nettop.common.domain.executor.ExecutionScheduler;
import org.alvarogp.nettop.common.domain.executor.PostExecutionThread;
import org.alvarogp.nettop.common.domain.executor.SingleThreadExecutionScheduler;
import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.common.presentation.view.android.activity.BaseActivity;
import org.alvarogp.nettop.common.presentation.view.android.activity.BaseActivityView;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;
import org.alvarogp.nettop.metric.domain.repository.MetricRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseActivityView baseActivityView);

    Logger logger();
    ExecutionScheduler executionScheduler();
    SingleThreadExecutionScheduler singleThreadExecutionScheduler();
    PostExecutionThread postExecutionThread();
    MetricRepository metricRepository();
    Cache<OwnerList> ownerListCache();
}
