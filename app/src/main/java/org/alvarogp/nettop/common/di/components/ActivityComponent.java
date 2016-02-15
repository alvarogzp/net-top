package org.alvarogp.nettop.common.di.components;

import org.alvarogp.nettop.common.di.modules.ActivityModule;
import org.alvarogp.nettop.common.di.scopes.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
}
