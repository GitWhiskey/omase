package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.goals.Goal;
import lombok.Getter;

public abstract class Role {

    @Getter
    protected RoleName name;

    @Getter
    protected Goal goal;

    /**
     * Choose new position to move to and do additional stuff according to role.
     * @param area The map of the environment.
     */
    public abstract void makeIteration(Area area, Agent agent);

    public enum RoleName {
        RESOURCE_SEARCHER,
        RESOURCE_PICKER,
        RESOURCE_TO_BASE_CARRIER,
        RESOURCE_AT_BASE_DROPPER
    }
}
