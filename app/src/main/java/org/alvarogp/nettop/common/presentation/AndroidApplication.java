package org.alvarogp.nettop.common.presentation;

import android.app.Application;

import org.alvarogp.nettop.common.di.components.ApplicationComponent;
import org.alvarogp.nettop.common.di.components.DaggerApplicationComponent;
import org.alvarogp.nettop.common.di.modules.ApplicationModule;

public class AndroidApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
