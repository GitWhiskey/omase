package com.mkhabrat.omase.domain.astar;

import com.mkhabrat.omase.astar.AbstractNode;
import com.mkhabrat.omase.domain.original.dos.DomainObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maxon on 10.10.2017.
 */
public class DomainNode extends AbstractNode {

    @Getter
    private List<DomainObject> domainObjects;

    public DomainNode(int xPosition, int yPosition) {
        this(xPosition, yPosition, new LinkedList<>());
    }

    public DomainNode(int xPosition, int yPosition, List<DomainObject> initDomainObjects) {
        super(xPosition, yPosition);
        this.domainObjects = initDomainObjects;
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

    public void clearDomainNode() {
        domainObjects.clear();
    }

    public void addObjectsToNode(DomainObject... dos) {
        domainObjects.addAll(Arrays.asList(dos));
    }
}
