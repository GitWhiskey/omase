package com.mkhabrat.omase.domain.dos;

import com.mkhabrat.omase.astar.AbstractNode;
import com.mkhabrat.omase.domain.Position;

public class DomainObject extends AbstractNode {

    public DomainObject(Position position) {
        super(position.getX(), position.getY());
    }

    public DomainObject(int xPosition, int yPosition) {
        super(xPosition, yPosition);
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
