package com.mkhabrat.omase.domain;

import com.mkhabrat.omase.domain.dos.Base;
import com.mkhabrat.omase.domain.dos.DomainObject;
import com.mkhabrat.omase.domain.dos.Resource;
import lombok.Getter;

import java.util.List;

public class Area {

    @Getter
    private int width;

    @Getter
    private int height;

    private DomainObject[][] map;

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new DomainObject[height][width];
    }

    public void placeDomainObject(DomainObject dObject, int x, int y) {
        map[y][x] = dObject;
    }

    public void placeDomainObject(DomainObject dObject, Position position) {
        placeDomainObject(dObject, position.getX(), position.getY());
    }

    public boolean positionHasResources(Position position) {
        return checkTypeOnPosition(position, Resource.class);
    }

    private boolean checkTypeOnPosition(Position position, Class checkedType) {
        DomainObject domainObject = map[position.getY()][position.getX()];
        return domainObject != null
                && domainObject instanceof List
                && checkedType.isInstance(((List) domainObject).get(0));

    }

    public boolean positionOutOfXBound(Position position) {
        return position.getX() < 0 || position.getX() >= width;
    }

    public boolean positionHasObstacles(Position position) {
        DomainObject domainObject = map[position.getY()][position.getX()];
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

    public enum Corner {
        LEFT_TOP,
        RIGHT_TOP,
        RIGHT_BOTTOM,
        LEFT_BOTTOM
    }
}
