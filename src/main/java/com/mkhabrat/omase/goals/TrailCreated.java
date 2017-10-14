package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.Settings;
import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrailCreated extends Goal {

    @Override
    public boolean isAchieved(Area area, Agent agent) {
        Position currentPosition = agent.getPosition();
        boolean pathSegmentPresent = area.positionHasTrailSegment(currentPosition);
        if (currentPosition.equals(Settings.BASE_POSITION) && pathSegmentPresent) {
            log.info("Path to base created.");
            return true;
        } else {
            return false;
        }
    }
}
