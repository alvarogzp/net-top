package org.alvarogp.nettop.metric.domain.model.owner.transform.comparator;

import org.alvarogp.nettop.metric.domain.model.owner.Owner;

import javax.inject.Inject;

public class IdOwnerComparator implements OwnerComparator {
    @Inject
    public IdOwnerComparator() {}

    @Override
    public int compare(Owner lhs, Owner rhs) {
        int lhsId = lhs.getId();
        int rhsId = rhs.getId();
        return compare(rhsId, lhsId);
    }

    /**
     * Copied from {@link Integer#compare(int, int)}, which was added in Java 7 (Android API 19).
     *
     * Preferred over {@link Integer#compareTo(Integer)} to avoid a new Integer object allocation.
     */
    private static int compare(int lhs, int rhs) {
        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
    }
}
