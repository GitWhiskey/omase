package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.dos.Agent;
import com.mkhabrat.omase.domain.dos.Resource;

import java.util.List;

public abstract class Goal {

    public abstract boolean isAchieved(List<Resource> resources, List<Agent> agents);
}
