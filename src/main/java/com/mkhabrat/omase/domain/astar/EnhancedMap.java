package com.mkhabrat.omase.domain.astar;

import com.mkhabrat.omase.astar.Map;
import com.mkhabrat.omase.astar.NodeFactory;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.DomainObject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnhancedMap extends Map<DomainNode> {

    public EnhancedMap(int width, int height, NodeFactory nodeFactory) {
        // Парметры перевернуты, так как в массиве идут сначала строки (т. е. высота),
        // затем столбцы (т. е. ширина)
        super(width, height, nodeFactory);
    }

    public void addDomainObjectsToNode(Position position, DomainObject... domainObjects) {
        addDomainObjectsToNode(position.getX(), position.getY(), domainObjects);
    }

    public void addDomainObjectsToNode(int x, int y, DomainObject... domainObjects) {
        getNode(x, y).addObjectsToNode(domainObjects);
    }

    public boolean hasTypeOnPosition(Position position, Class type) {
        List<DomainObject> dos = getNode(position.getX(), position.getY()).getDomainObjects();
        // Жесткая лямбда :) В этой строке описано следующее: проходимся по каждому элементу списка и возвращаем тру,
        // если хотя бы один элемент возвращает тру на методе type.isInstance(элемент списка)
        return dos.stream().anyMatch(type::isInstance);
    }

    public void removeDomainObjects(Position position, DomainObject... dObjects) {
        List<DomainObject> dos = getNode(position.getX(), position.getY()).getDomainObjects();
        dos.removeAll(Arrays.asList(dObjects));
    }

    public DomainObject getDomainObjectAtPosition(Position position, Class type) {
        List<DomainObject> dos = getNode(position.getX(), position.getY()).getDomainObjects();
        Optional<DomainObject> optDO = dos.stream().filter(type::isInstance).findFirst();
        if (optDO.isPresent()) {
            return optDO.get();
        } else {
            throw new RuntimeException("No objects of type " + type + " found at position " + position);
        }
    }

    public List<DomainObject> getAllDomainObjectsOfTypeAtPosition(Position position, Class type) {
        List<DomainObject> dos = getNode(position.getX(), position.getY()).getDomainObjects();
        return dos.stream().filter(type::isInstance).collect(Collectors.toList());
    }

    public List<DomainObject> getAllDomainObjectsAtPosition(Position position) {
        return getNode(position.getX(), position.getY()).getDomainObjects();
    }
}
