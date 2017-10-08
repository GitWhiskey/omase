package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.Area;
import com.mkhabrat.omase.domain.dos.Agent;

public abstract class Role {

    public abstract void makeStep(Agent agent, Area area);
}
