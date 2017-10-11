package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by Maxon on 08.10.2017.
 *
 * Цель роли ResourceSearcher. Цель будет достигнута тогда, когда агент попадет на клетку с ресурсами.
 */
@Slf4j
public class ResourcesFound extends Goal {

    public boolean isAchieved(Area area, Agent agent) {
        Position currentPosition = agent.getPosition();
        if (area.positionHasResources(currentPosition)) {
            log.info("Found resources on position {}", currentPosition);
            return true;
        } else {
            return false;
        }

    }
}
