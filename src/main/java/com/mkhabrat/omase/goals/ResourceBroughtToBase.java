package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.Settings;
import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Maxon on 09.10.2017.
 */
@Slf4j
public class ResourceBroughtToBase extends Goal {

    @Override
    public boolean isAchieved(Area area, Agent agent) {
        Position currentPosition = agent.getPosition();
        Integer amountOfResourcesCarried = agent.getCarriedResourceAmount();
        if (amountOfResourcesCarried > 0 && currentPosition.equals(Settings.BASE_POSITION)) {
            log.info("{} units of resources successfully brought to base.", amountOfResourcesCarried);
            return true;
        } else {
            return false;
        }
    }
}
