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

    public TrailRemover() {
        this.name = RoleName.TRAIL_REMOVER;
        this.goal = new TrailRemoved();
    }

    @Override
    public void makeIteration(Area area, Agent agent) {
        Position currentPosition = agent.getPosition();
        TrailSegment segment = getFirstTrailSegment(area, currentPosition);

        while (segment != null) {
            area.removeDomainObjects(segment.getPosition(), segment);
            segment = segment.getNext();
        }
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
