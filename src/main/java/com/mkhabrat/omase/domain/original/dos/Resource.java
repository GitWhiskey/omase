package com.mkhabrat.omase.domain.original.dos;

import lombok.Getter;
import lombok.Setter;

public class Resource extends DomainObject {

    @Getter
    @Setter
    private int quantity = 1;

    public Resource(int x, int y, int quantity) {
        super(x, y);
        this.quantity = quantity;
    }
}
