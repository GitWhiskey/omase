package com.mkhabrat.omase.domain.original.dos;

import com.mkhabrat.omase.astar.AbstractNode;
import com.mkhabrat.omase.domain.original.Position;
import lombok.Getter;
import lombok.Setter;

public class DomainObject extends AbstractNode {

    @Getter @Setter
    protected Position currentPosition;

    public DomainObject(Position position) {
        super(position.getX(), position.getY());
        this.currentPosition = position;
    }

    public DomainObject(int xPosition, int yPosition) {
        this(new Position(xPosition, yPosition));
    }

    @Override
    public void sethCosts(AbstractNode endNode) {
        this.sethCosts((absolute(this.getxPosition() - endNode.getxPosition())
                + absolute(this.getyPosition() - endNode.getyPosition()))
                * BASICMOVEMENTCOST);
    }

    private int absolute(int a) {
        return a > 0 ? a : -a;
    }
}
