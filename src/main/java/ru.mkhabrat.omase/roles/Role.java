package ru.mkhabrat.omase.roles;

import ru.mkhabrat.omase.domain.Area;
import ru.mkhabrat.omase.domain.dos.Agent;

public abstract class Role {

    public abstract void makeStep(Agent agent, Area area);
}
