package org.alvarogp.nettop.common.di.modules;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import org.alvarogp.nettop.common.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides @PerActivity Activity provideActivity() {
        return activity;
    }

    @Provides @PerActivity Context provideActivityContext() {
        return activity;
    }

    @Provides @PerActivity LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(activity);
    }

    @Provides @PerActivity InputMethodManager provideInputMethodManager() {
        return (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }
}
