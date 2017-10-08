package ru.mkhabrat.omase.domain.dos;

import lombok.Getter;
import lombok.Setter;

public class Resource extends DomainObject {

    @Getter
    @Setter
    private int quantity = 1;

    public Resource(int quantity) {
        this.quantity = quantity;
    }
}
