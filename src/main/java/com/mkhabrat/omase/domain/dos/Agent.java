package com.mkhabrat.omase.domain.dos;

import com.mkhabrat.omase.domain.Area;
import lombok.Getter;
import lombok.Setter;
import com.mkhabrat.omase.domain.Position;
import com.mkhabrat.omase.roles.ResourceSearcher;
import com.mkhabrat.omase.roles.Role;

public class Agent extends DomainObject {

    @Getter @Setter
    private Position currentPosition;

    @Getter @Setter
    Role role;

    public Agent(Position position) {
        currentPosition = position;
        role = new ResourceSearcher();
    }

    public void makeStep(Area area) {
        role.makeStep(this, area);
    }
}
