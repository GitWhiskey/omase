package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.Area;
import com.mkhabrat.omase.domain.Position;
import com.mkhabrat.omase.goals.ResourcesFound;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class ResourceSearcher extends Role {

    public ResourceSearcher() {
        name = RoleNames.RESOURCE_SEARCHER;
        goal = new ResourcesFound();
    }

    public Position act(Area area, Position currentPosition) {
        log.info("I'm a resource searcher and I made a step at position: {}.", currentPosition);
        return new Position(currentPosition.getX() + 1, currentPosition.getY() + 1);
    }
}
