package com.mkhabrat.omase.domain.original.dos;

import com.mkhabrat.omase.domain.original.Position;
import lombok.Getter;

public class Base extends DomainObject {

    @Getter
    private int amountOfResources;

    public Base(Position position) {
        super(position);
    }

    public void addResourcesToBase(int amountOfResources) {
        this.amountOfResources += amountOfResources;
    }
}
