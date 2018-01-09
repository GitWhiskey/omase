package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.TrailSegment;
import com.mkhabrat.omase.goals.FollowedOrLostTrail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrailFollower extends Role {

    private TrailSegment currentTrailSegment;

    public TrailFollower(Agent agent, Area area, Position startPosition) {
        this.name = RoleName.TRAIL_FOLLOWER;
        this.goal = new FollowedOrLostTrail();
        if (agent.getFollowedPathId() <= 0) {
            this.currentTrailSegment = area.findNewTrailSegment(startPosition);
            agent.setFollowedPathId(currentTrailSegment.getId());
        } else {
            this.currentTrailSegment = area.findOldTrailSegment(agent.getFollowedPathId(), startPosition);
        }
    }

    @Override
    public void makeIteration(Area area, Agent agent) {
        if (currentTrailSegment != null) {
            TrailSegment previous = currentTrailSegment.getPrevious();
            if (previous != null) {
                TrailSegment previousOnMap = area.findOldTrailSegment(previous.getId(), previous.getPosition());
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
        } else {
            agent.setFollowedPathId(-1);
        }

    }
}
