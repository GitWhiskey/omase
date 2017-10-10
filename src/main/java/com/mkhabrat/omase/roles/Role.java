package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
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
     * @param currentPosition Current agent position.
     * @return New chosen position to move to.
     */
    public abstract Position act(Area area, Position currentPosition);

    public enum RoleName {
        RESOURCE_SEARCHER,
        RESOURCE_COLLECTOR
    }
}
