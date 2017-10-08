package ru.mkhabrat.omase.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Position {

    @Getter @Setter
    private int x;

    @Getter @Setter
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
