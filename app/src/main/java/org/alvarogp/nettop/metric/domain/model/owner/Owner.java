package org.alvarogp.nettop.metric.domain.model.owner;

public class Owner {
    private final int id;
    private final CharSequence name;
    private final boolean current;

    public Owner(int id, CharSequence name, boolean current) {
        this.id = id;
        this.name = name;
        this.current = current;
    }

    /**
     * Returns an id that uniquely identifies this owner
     */
    public int getId() {
        return id;
    }

    public CharSequence getName() {
        return name;
    }

    /**
     * Returns whether this owner represents the currently running app (this one)
     */
    public boolean isCurrent() {
        return current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;

        Owner owner = (Owner) o;
        return id == owner.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
