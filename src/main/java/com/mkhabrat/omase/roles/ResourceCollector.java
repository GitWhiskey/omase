package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.DomainObject;
import com.mkhabrat.omase.goals.ResourceBroughtToBase;
import com.sun.java.browser.plugin2.DOM;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
        log.debug("Getting shortest path to base.");
        List<DomainObject> path = area.getPathToBase(currentPosition);
        printPath(path);

        return path.get(0).getCurrentPosition();
    }

    private void printPath(List<DomainObject> path) {
        StringBuilder message = new StringBuilder();
        for (DomainObject p : path) {
            message.append(" -> ")
                    .append("(")
                    .append(p.getxPosition())
                    .append(",")
                    .append(p.getyPosition())
                    .append(")");
        }
        log.debug(message.toString());
    }
}
