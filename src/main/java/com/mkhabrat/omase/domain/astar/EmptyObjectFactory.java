package com.mkhabrat.omase.domain.astar;

import com.mkhabrat.omase.astar.AbstractNode;
import com.mkhabrat.omase.astar.NodeFactory;
import com.mkhabrat.omase.domain.original.Position;

public class EmptyObjectFactory implements NodeFactory {

    @Override
    public AbstractNode createNode(int x, int y) {
        return new EmptyObject(new Position(x, y));
    }
}
