package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by Maxon on 08.10.2017.
 *
 * Цель роли ResourceSearcher. Цель будет достигнута тогда, когда агент попадет на клетку с ресурсами.
 */
@Slf4j
public class ResourcesFound extends Goal {

    public boolean isAchieved(Object... factors) {
        Area area;
        Position currentPosition;
        try {
            area = (Area) factors[0];
            currentPosition = (Position) factors[1];
            if (area.positionHasResources(currentPosition)) {
                log.info("Found resources on position {}", currentPosition);
                return true;
            } else {
                return false;
            }
        } catch (ClassCastException e) {
            log.error("There should be two arguments. First of type Area and second of type Position. " +
                    "Full exception: {}", e);
            return false;
        }
    }
}
