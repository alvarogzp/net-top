package org.alvarogp.nettop.metric.domain.model.owner.transform.comparator;

import org.alvarogp.nettop.metric.domain.model.owner.Owner;

import javax.inject.Inject;

public class CurrentOwnerComparator implements OwnerComparator {
    @Inject
    public CurrentOwnerComparator() {}

    @Override
    public int compare(Owner lhs, Owner rhs) {
        if (lhs.isCurrent()) {
            if (rhs.isCurrent()) {
                throw new RuntimeException("unexpectedly found two current owners");
                //return 0;
            }
            return -1;
        } else if (rhs.isCurrent()) {
            return 1;
        } else {
            return 0;
        }
    }
}
