package org.alvarogp.nettop.common.presentation.view.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.alvarogp.nettop.common.di.components.ApplicationComponent;
import org.alvarogp.nettop.common.di.modules.ActivityModule;
import org.alvarogp.nettop.common.presentation.AndroidApplication;

/**
 * Base class for all activities.
 * Provide access to ApplicationComponent.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
