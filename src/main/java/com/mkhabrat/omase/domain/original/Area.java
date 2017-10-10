package com.mkhabrat.omase.domain.original;

import com.mkhabrat.omase.Settings;
import com.mkhabrat.omase.domain.astar.EmptyObjectFactory;
import com.mkhabrat.omase.domain.astar.EnhancedMap;
import com.mkhabrat.omase.domain.original.dos.Base;
import com.mkhabrat.omase.domain.original.dos.DomainObject;
import com.mkhabrat.omase.domain.original.dos.Resource;
import lombok.Getter;

import java.util.List;

public class Area {

    @Getter
    private int width;

    @Getter
    private int height;

    private EnhancedMap<DomainObject> map;

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new EnhancedMap<>(width, height, new EmptyObjectFactory());
    }

    public void placeDomainObject(DomainObject dObject, int x, int y) {
        map.placeNode(dObject, x, y);
    }

    public void placeDomainObject(DomainObject dObject, Position position) {
        placeDomainObject(dObject, position.getX(), position.getY());
    }

    public boolean positionHasResources(Position position) {
        return checkTypeOnPosition(position, Resource.class);
    }

    private boolean checkTypeOnPosition(Position position, Class checkedType) {
        DomainObject domainObject = (DomainObject) map.getNode(position.getX(), position.getY());
        return checkedType.isInstance(domainObject);
    }

    public boolean positionOutOfXBound(Position position) {
        return position.getX() < 0 || position.getX() >= width;
    }

    public boolean positionHasObstacles(Position position) {
        DomainObject domainObject = (DomainObject) map.getNode(position.getX(), position.getY());
        return domainObject instanceof Base;
    }

    public boolean positionIsCorner(Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x == 0 || x == width - 1) && (y == 0 || y == height - 1);
    }

    public Corner getCornerAtPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        if (x == 0 && y == 0) {
            return Corner.LEFT_TOP;
        } else if (x == width - 1 && y == 0) {
            return Corner.RIGHT_TOP;
        } else if (x == 0 && y == height - 1) {
            return Corner.LEFT_BOTTOM;
        } else if (x == width - 1 && y == height - 1) {
            return  Corner.RIGHT_BOTTOM;
        } else {
            throw new RuntimeException("Position " + x + ", " + y + " is not a corner");
        }
    }

    public List<DomainObject> getPathToBase(Position currentPosition) {
        Position basePosition = Settings.BASE_POSITION;
        return map.findPath(currentPosition.getX(), currentPosition.getY(), basePosition.getX(), basePosition.getY());
    }

    public enum Corner {
        LEFT_TOP,
        RIGHT_TOP,
        RIGHT_BOTTOM,
        LEFT_BOTTOM
    }
}
