package com.mkhabrat.omase.domain;

import com.mkhabrat.omase.domain.dos.DomainObject;
import com.mkhabrat.omase.domain.dos.Resource;

import java.util.List;

public class Area {

    public DomainObject[][] map;

    public Area(int width, int height) {
        map = new DomainObject[height][width];
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
                && ((List) domainObject).get(0).getClass().equals(checkedType);

    }
}
