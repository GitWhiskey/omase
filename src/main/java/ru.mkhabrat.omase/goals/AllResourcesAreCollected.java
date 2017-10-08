package ru.mkhabrat.omase.goals;

import ru.mkhabrat.omase.domain.dos.Agent;
import ru.mkhabrat.omase.domain.dos.Resource;

import java.util.List;

public class AllResourcesAreCollected extends Goal {

    public boolean isAchieved(List<Resource> resources, List<Agent> agents) {
        return resources.size() == 0;
    }
}
