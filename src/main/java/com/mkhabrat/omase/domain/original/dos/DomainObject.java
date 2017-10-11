package com.mkhabrat.omase.domain.original.dos;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import lombok.Getter;
import lombok.Setter;

public class DomainObject {

    @Getter @Setter
    protected Position position;

    public DomainObject(Position position) {
        this.position = position;
    }

    public DomainObject(int xPosition, int yPosition) {
        this(new Position(xPosition, yPosition));
    }

    public void move(Area area, Position oldPosition, Position newPosition) {
        setPosition(newPosition);
        area.moveDomainObjects(oldPosition, newPosition, this);
    }
}
