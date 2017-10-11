package com.mkhabrat.omase.domain.astar;

import com.mkhabrat.omase.astar.AbstractNode;
import com.mkhabrat.omase.astar.NodeFactory;

public class DomainNodeFactory implements NodeFactory {

    @Override
    public AbstractNode createNode(int x, int y) {
        return new DomainNode(x, y);
    }
}
