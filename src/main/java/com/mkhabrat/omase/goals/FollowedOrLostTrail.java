package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.TrailSegment;
import com.mkhabrat.omase.roles.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FollowedOrLostTrail extends Goal {

    @Override
    public boolean isAchieved(Area area, Agent agent) {
        if (agent.getFollowedPathId() <= 0) {
            log.info("Trail is lost. Returning to {} role.", Role.RoleName.RESOURCE_SEARCHER);
            return true;
        } else {
            Position currentPosition = agent.getPosition();
            boolean hasResource = area.positionHasResources(currentPosition);
            TrailSegment trailSegment = area.getTrailSegmentById(agent.getFollowedPathId(), currentPosition);
            if (hasResource && trailSegment.getPrevious() == null) {
                log.info("Followed trail and found resource at {}", currentPosition);
                return true;
            } else {
                return false;
            }
        }
    }
}
