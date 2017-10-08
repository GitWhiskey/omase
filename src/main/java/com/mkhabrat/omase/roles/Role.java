package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.Area;
import com.mkhabrat.omase.domain.Position;
import com.mkhabrat.omase.goals.Goal;
import lombok.Getter;

public abstract class Role {

    @Getter
    protected RoleNames name;

    @Getter
    protected Goal goal;

    /**
     * Choose new position to move to and do additional stuff according to role.
     * @param area The map of the environment.
     * @param currentPosition Current agent position.
     * @return New chosen position to move to.
     */
    public abstract Position act(Area area, Position currentPosition);

    public enum RoleNames {
        RESOURCE_SEARCHER,
        RESOURCE_COLLECTOR
    }
}
