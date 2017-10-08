package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.dos.Agent;
import com.mkhabrat.omase.domain.dos.Resource;

import java.util.List;

public class AllResourcesAreCollected extends Goal {

    public boolean isAchieved(List<Resource> resources, List<Agent> agents) {
        return resources.size() == 0;
    }
}
