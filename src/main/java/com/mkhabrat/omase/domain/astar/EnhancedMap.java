package com.mkhabrat.omase.domain.astar;

import com.mkhabrat.omase.astar.AbstractNode;
import com.mkhabrat.omase.astar.Map;
import com.mkhabrat.omase.astar.NodeFactory;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.DomainObject;

public class EnhancedMap<T extends AbstractNode> extends Map {

    public EnhancedMap(int width, int height, NodeFactory nodeFactory) {
        // Парметры перевернуты, так как в массиве идут сначала строки (т. е. высота),
        // затем столбцы (т. е. ширина)
        super(width, height, nodeFactory);
    }

    public void placeNode(DomainObject node, Position position) {
        placeNode(node, position.getX(), position.getY());
    }

    public void placeNode(DomainObject node, int x, int y) {
        nodes[x][y] = node;
    }
}
