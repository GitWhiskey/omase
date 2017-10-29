package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.Settings;
import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrailRemoved extends Goal {

    @Override
    public boolean isAchieved(Area area, Agent agent) {
        Position currentPosition = agent.getPosition();
        boolean trailPresentAtStart = area.positionHasTrailSegment(currentPosition);
        boolean trailPresentAtEnd = area.positionHasTrailSegment(Settings.BASE_POSITION);
        if (!trailPresentAtStart && !trailPresentAtEnd) {
            log.info("Agent {} removed path.", agent.getId());
            agent.setFollowedPathId(-1);
            return true;
        } else {
            return false;
        }
    }
}
