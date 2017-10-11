package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.dos.Agent;

public abstract class Goal {

    public abstract boolean isAchieved(Area area, Agent agent);
}
