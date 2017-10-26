package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.DomainObject;
import com.mkhabrat.omase.domain.original.dos.TrailSegment;
import com.mkhabrat.omase.goals.TrailRemoved;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class TrailRemover extends Role {

    private int removedTrailId;

    public TrailRemover(Area area, Position position) {
        this.name = RoleName.TRAIL_REMOVER;
        this.goal = new TrailRemoved();
        TrailSegment trailSegment = getFirstTrailSegment(area, position);
        this.removedTrailId = trailSegment.getId();
        area.removeDomainObjects(position, trailSegment);
    }

    @Override
    public void makeIteration(Area area, Agent agent) {
        Position currentPosition = agent.getPosition();
        TrailSegment trailSegment = area.findOldTrailSegment(removedTrailId, currentPosition);
        area.removeDomainObjects(currentPosition, trailSegment);
    }

    private TrailSegment getFirstTrailSegment(Area area, Position position) {
        List<DomainObject> trailSegmentsAtPosition =
                area.getAllDomainObjectsOfTypeAtPosition(position, TrailSegment.class);
        Optional<DomainObject> tsOpt = trailSegmentsAtPosition.stream().filter(
                domainObject -> ((TrailSegment) domainObject).getPrevious() == null
        ).findAny();
        if (tsOpt.isPresent()) {
            return (TrailSegment) tsOpt.get();
        } else {
            log.info("No trail segments start at " + position);
            return null;
        }
    }
}
