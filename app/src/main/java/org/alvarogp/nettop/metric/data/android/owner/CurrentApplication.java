package org.alvarogp.nettop.metric.data.android.owner;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import javax.inject.Inject;

public class CurrentApplication {
    private final Context context;

    private ApplicationInfo currentApplicationInfo;

    @Inject
    public CurrentApplication(Context context) {
        this.context = context;
    }

    public ApplicationInfo getCurrentApplication() {
        if (currentApplicationInfo == null) {
            currentApplicationInfo = context.getApplicationInfo();
        }
        return currentApplicationInfo;
    }

    public boolean isCurrentApplication(ApplicationInfo applicationInfo) {
        //return getCurrentApplication().equals(applicationInfo); // does not work
        // uid check is not enough, as another app can share the same uid with us in the future
        return getCurrentApplication().uid == applicationInfo.uid &&
                getCurrentApplication().packageName.equals(applicationInfo.packageName);
    }
}
