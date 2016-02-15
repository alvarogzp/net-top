package org.alvarogp.nettop.metric.data.android.owner;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import org.alvarogp.nettop.metric.domain.model.owner.Owner;

import javax.inject.Inject;

import rx.functions.Func1;

public class ApplicationInfoToOwner implements Func1<ApplicationInfo, Owner> {
    private final PackageManager packageManager;
    private final CurrentApplication currentApplication;

    @Inject
    public ApplicationInfoToOwner(PackageManager packageManager, CurrentApplication currentApplication) {
        this.packageManager = packageManager;
        this.currentApplication = currentApplication;
    }

    @Override
    public Owner call(ApplicationInfo applicationInfo) {
        int uid = applicationInfo.uid;
        CharSequence label = packageManager.getApplicationLabel(applicationInfo);
        boolean current = currentApplication.isCurrentApplication(applicationInfo);
        return new Owner(uid, label, current);
    }
}
