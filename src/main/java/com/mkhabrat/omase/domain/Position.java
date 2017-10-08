package com.mkhabrat.omase.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Position implements Cloneable {

    @Getter @Setter
    private int x;

    @Getter @Setter
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void incrementXBy(int number) {
        this.x += number;
    }

    public void incrementYBy(int number) {
        this.y += number;
    }

    @Override
    public Object clone() {
        return new Position(x, y);
    }
}
