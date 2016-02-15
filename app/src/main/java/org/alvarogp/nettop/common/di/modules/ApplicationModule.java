package org.alvarogp.nettop.common.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import org.alvarogp.nettop.common.data.cache.InMemoryCacheImpl;
import org.alvarogp.nettop.common.data.executor.BackgroundSingleThreadExecutionScheduler;
import org.alvarogp.nettop.common.data.executor.JobExecutionScheduler;
import org.alvarogp.nettop.common.domain.cache.Cache;
import org.alvarogp.nettop.common.domain.executor.ExecutionScheduler;
import org.alvarogp.nettop.common.domain.executor.PostExecutionThread;
import org.alvarogp.nettop.common.domain.executor.SingleThreadExecutionScheduler;
import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.common.presentation.UiThread;
import org.alvarogp.nettop.common.presentation.logger.AndroidLogger;
import org.alvarogp.nettop.metric.data.repository.MetricDataRepository;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;
import org.alvarogp.nettop.metric.domain.repository.MetricRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return application;
    }

    @Provides @Singleton PackageManager providePackageManager() {
        return application.getPackageManager();
    }

    @Provides @Singleton Logger provideLogger(AndroidLogger androidLogger) {
        return androidLogger;
    }

    @Provides @Singleton ExecutionScheduler provideExecutionScheduler(JobExecutionScheduler jobExecutionScheduler) {
        return jobExecutionScheduler;
    }

    @Provides @Singleton SingleThreadExecutionScheduler provideSingleThreadExecutionScheduler(BackgroundSingleThreadExecutionScheduler backgroundSingleThreadExecutionScheduler) {
        return backgroundSingleThreadExecutionScheduler;
    }

    @Provides @Singleton PostExecutionThread providePostExecutionThread(UiThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton MetricRepository provideMetricRepository(MetricDataRepository metricDataRepository) {
        return metricDataRepository;
    }

    @Provides @Singleton Cache<OwnerList> provideOwnerListCache(InMemoryCacheImpl<OwnerList> ownerListInMemoryCache) {
        return ownerListInMemoryCache;
    }
}
