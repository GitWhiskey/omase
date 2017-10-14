package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.DomainObject;
import com.mkhabrat.omase.domain.original.dos.TrailSegment;
import com.mkhabrat.omase.goals.FollowedOrLostTrail;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class TrailFollower extends Role {

    private TrailSegment currentTrailSegment;

    public TrailFollower(int followedPathId, Area area, Position startPosition) {
        this.name = RoleName.TRAIL_FOLLOWER;
        this.goal = new FollowedOrLostTrail();
        this.currentTrailSegment = area.getTrailSegmentById(followedPathId, startPosition);
    }

    @Override
    public void makeIteration(Area area, Agent agent) {
        if (currentTrailSegment != null) {
            TrailSegment previous = currentTrailSegment.getPrevious();
            TrailSegment previousOnMap = area.getTrailSegmentById(previous.getId(), previous.getPosition());
            if (previousOnMap != null) {
                Position newPosition = previousOnMap.getPosition();
                agent.move(area, agent.getPosition(), newPosition);
                log.debug("Agent made a step to {}", newPosition);
                currentTrailSegment = previousOnMap;
            } else {
                agent.setFollowedPathId(-1);
            }
        } else {
            agent.setFollowedPathId(-1);
        }

    }
}
