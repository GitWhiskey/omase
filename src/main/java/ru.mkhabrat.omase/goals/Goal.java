package ru.mkhabrat.omase.goals;

import ru.mkhabrat.omase.domain.dos.Agent;
import ru.mkhabrat.omase.domain.dos.Resource;

import java.util.List;

public abstract class Goal {

    public abstract boolean isAchieved(List<Resource> resources, List<Agent> agents);
}
