package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.DomainObject;
import com.mkhabrat.omase.domain.original.dos.TrailSegment;
import com.mkhabrat.omase.goals.TrailRemoved;

import java.util.List;
import java.util.Optional;

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
            throw new RuntimeException("No trail segments start at " + position);
        }
    }
}
