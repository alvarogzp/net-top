package org.alvarogp.nettop.metric.domain.model.owner;

import javax.inject.Inject;

public class SpecialOwners {
    public static final int TOTAL = Integer.MIN_VALUE;

    @Inject
    public SpecialOwners() {}

    public boolean isSpecial(Owner owner) {
        return isTotal(owner);
    }

    public boolean isTotal(Owner owner) {
        return owner.getId() == TOTAL;
    }
}
