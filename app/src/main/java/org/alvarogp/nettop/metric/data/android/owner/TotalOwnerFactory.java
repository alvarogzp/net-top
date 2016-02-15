package org.alvarogp.nettop.metric.data.android.owner;

import android.content.Context;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.metric.domain.model.owner.Owner;
import org.alvarogp.nettop.metric.domain.model.owner.SpecialOwners;

import javax.inject.Inject;

public class TotalOwnerFactory {
    private final Context context;

    @Inject
    public TotalOwnerFactory(Context context) {
        this.context = context;
    }

    public Owner getTotalOwner() {
        return new Owner(SpecialOwners.TOTAL, context.getString(R.string.total_metric_label), false);
    }
}
