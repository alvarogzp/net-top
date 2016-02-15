package org.alvarogp.nettop.metric.domain.model.owner.transform.comparator;

import org.alvarogp.nettop.metric.domain.model.owner.Owner;

import javax.inject.Inject;

public class CompoundOwnerComparator implements OwnerComparator {
    private final OwnerComparator[] comparators;

    @Inject
    public CompoundOwnerComparator(OwnerComparator... comparators) {
        this.comparators = comparators;
    }

    @Override
    public int compare(Owner lhs, Owner rhs) {
        for (OwnerComparator comparator : comparators) {
            int comparison = comparator.compare(lhs, rhs);
            if (comparison != 0) { // not equal
                return comparison;
            }
        }
        // all comparisons resulted in them being equal, so they must be
        return 0;
    }
}
