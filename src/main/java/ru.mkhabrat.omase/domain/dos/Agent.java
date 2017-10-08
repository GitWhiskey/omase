package ru.mkhabrat.omase.domain.dos;

import lombok.Getter;
import lombok.Setter;
import ru.mkhabrat.omase.domain.Area;
import ru.mkhabrat.omase.domain.Position;
import ru.mkhabrat.omase.roles.ResourceSearcher;
import ru.mkhabrat.omase.roles.Role;

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
