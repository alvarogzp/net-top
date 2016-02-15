package org.alvarogp.nettop.metric.data.android.owner;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import org.alvarogp.nettop.common.domain.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class InstalledApplications {
    private final Logger logger;
    private final PackageManager packageManager;

    @Inject
    public InstalledApplications(Logger logger, PackageManager packageManager) {
        this.logger = logger;
        this.packageManager = packageManager;
    }

    public Observable<ApplicationInfo> installedApplications() {
        // Using Observable.defer() to avoid call to getInstalledApplications() on calling thread
        return Observable.defer(() -> Observable.from(getInstalledApplications()));
    }

    private List<ApplicationInfo> getInstalledApplications() {
        logger.debug(this, "Retrieving installed applications");
        return packageManager.getInstalledApplications(0);
    }
}
