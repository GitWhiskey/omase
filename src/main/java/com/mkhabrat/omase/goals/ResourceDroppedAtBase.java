package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.Settings;
import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.Base;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceDroppedAtBase extends Goal {

    @Override
    public boolean isAchieved(Area area, Agent agent) {
        Position currentPosition = agent.getPosition();
        int amountOfCarriedResources = agent.getCarriedResourceAmount();
        if (currentPosition.equals(Settings.BASE_POSITION) && amountOfCarriedResources == 0) {
            log.info("Resources dropped at base.");
            Base base = area.getBase();
            int resourcesOnBase = base.getAmountOfResources();
            int resourcesLeftOnMap = area.getTotalAmountOfResources() - resourcesOnBase;
            log.info("Resources on base: {}. Resources left on map: {}", resourcesOnBase, resourcesLeftOnMap);
            return true;
        } else {
            return false;
        }
    }
}
