package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.dos.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllResourcesAreCollected extends Goal {

    public boolean isAchieved(Area area, Agent agent) {
        int totalAmountOfResources = area.getTotalAmountOfResources();
        int resourcesAtBase = area.getBase().getAmountOfResources();
        if (resourcesAtBase == totalAmountOfResources) {
            log.info("Success! Primary goal is achieved and all resources are collected.");
            return true;
        } else {
            return false;
        }
    }
}
