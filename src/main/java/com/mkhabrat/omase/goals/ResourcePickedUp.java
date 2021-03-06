package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Maxon on 11.10.2017.
 */
@Slf4j
public class ResourcePickedUp extends Goal {

    @Override
    public boolean isAchieved(Area area, Agent agent) {
        Integer amountOfResourceAgentCollected = agent.getCarriedResourceAmount();
        if (amountOfResourceAgentCollected > 0) {
            log.info("Agent picked up {} units of resource.", amountOfResourceAgentCollected);
            return true;
        } else {
            Position currentPosition = agent.getPosition();
            boolean resourceWasPickedBySomeoneElse = !area.positionHasResources(currentPosition);
            if (resourceWasPickedBySomeoneElse) {
                log.info("Resource was picked by someone else! I will do something else.");
                return true;
            } else {
                return false;
            }
        }

    }
}
