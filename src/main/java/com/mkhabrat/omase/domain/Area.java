package com.mkhabrat.omase.domain;

import com.mkhabrat.omase.domain.dos.DomainObject;

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
}
