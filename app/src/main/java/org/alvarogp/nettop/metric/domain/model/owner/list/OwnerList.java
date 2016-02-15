package org.alvarogp.nettop.metric.domain.model.owner.list;

import org.alvarogp.nettop.metric.domain.model.owner.Owner;
import org.alvarogp.nettop.metric.domain.model.owner.transform.comparator.OwnerComparator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OwnerList {
    private Owner totalOwner;
    private List<Owner> owners;

    public OwnerList(Owner totalOwner, List<Owner> owners) {
        this.totalOwner = totalOwner;
        this.owners = owners;
    }

    public Owner getTotalOwner() {
        return totalOwner;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void distinct() {
        Set<Owner> uniqueOwners = new HashSet<>();
        for (int i = 0; i < owners.size(); i++) {
            Owner owner = owners.get(i);
            boolean added = uniqueOwners.add(owner);
            if (!added) { // repeated owner
                owners.remove(i);
                i--; // do not advance counter
            }
        }
    }

    public void sort(OwnerComparator ownerComparator) {
        Collections.sort(owners, ownerComparator);
    }
}
