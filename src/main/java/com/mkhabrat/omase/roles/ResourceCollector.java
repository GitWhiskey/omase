package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.Area;
import com.mkhabrat.omase.domain.Position;
import com.mkhabrat.omase.goals.ResourceBroughtToBase;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Maxon on 08.10.2017.
 */
@Slf4j
@ToString
public class ResourceCollector extends Role {

    public ResourceCollector() {
        this.name = RoleName.RESOURCE_COLLECTOR;
        this.goal = new ResourceBroughtToBase();
    }

    public Position act(Area area, Position currentPosition) {
        return currentPosition;
    }
}
