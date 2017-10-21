package com.mkhabrat.omase.domain.original;

import com.mkhabrat.omase.Settings;
import com.mkhabrat.omase.domain.astar.DomainNode;
import com.mkhabrat.omase.domain.astar.DomainNodeFactory;
import com.mkhabrat.omase.domain.astar.EnhancedMap;
import com.mkhabrat.omase.domain.original.dos.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class Area {

    @Getter
    private int width;

    @Getter
    private int height;

    private EnhancedMap map;

    @Getter @Setter
    int totalAmountOfResources;

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new EnhancedMap(width, height, new DomainNodeFactory());
    }

    public void placeDomainObjects(Position position, DomainObject... dObjects) {
        placeDomainObjects(position.getX(), position.getY(), dObjects);
    }

    public void placeDomainObjects(int x, int y, DomainObject... dObjects) {
        map.addDomainObjectsToNode(x, y, dObjects);
    }

    public void removeDomainObjects(Position position, DomainObject... dObjects) {
        map.removeDomainObjects(position, dObjects);
    }

    public boolean positionHasBase(Position position) {
        return map.hasTypeOnPosition(position, Base.class);
    }

    public boolean positionHasResources(Position position) {
        return map.hasTypeOnPosition(position, Resource.class);
    }

    public boolean positionHasAgent(Position position) {
        return map.hasTypeOnPosition(position, Agent.class);
    }

    public boolean positionHasTrailSegment(Position position) {
        return map.hasTypeOnPosition(position, TrailSegment.class);
    }

    public boolean positionOutOfXBound(Position position) {
        return position.getX() < 0 || position.getX() >= width;
    }

    public boolean positionIsCorner(Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x == 0 || x == width - 1) && (y == 0 || y == height - 1);
    }

    public Corner getCornerAtPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        if (x == 0 && y == 0) {
            return Corner.LEFT_TOP;
        } else if (x == width - 1 && y == 0) {
            return Corner.RIGHT_TOP;
        } else if (x == 0 && y == height - 1) {
            return Corner.LEFT_BOTTOM;
        } else if (x == width - 1 && y == height - 1) {
            return  Corner.RIGHT_BOTTOM;
        } else {
            throw new RuntimeException("Position " + x + ", " + y + " is not a corner");
        }
    }

    public List<DomainNode> findShortestPath(Position from, Position to) {
        return map.findPath(from.getX(), from.getY(), to.getX(), to.getY());
    }

    public void moveDomainObjects(Position oldPosition, Position newPosition, DomainObject... domainObjects) {
        removeDomainObjects(oldPosition, domainObjects);
        placeDomainObjects(newPosition, domainObjects);
    }

    public DomainObject getDomainObjectAtPosition(Position position, Class type) {
        return map.getDomainObjectAtPosition(position, type);
    }

    public List<DomainObject> getAllDomainObjectsOfTypeAtPosition(Position position, Class type) {
        return map.getAllDomainObjectsOfTypeAtPosition(position, type);
    }

    public List<DomainObject> getAllDomainObjectsAtPosition(Position position) {
        return map.getAllDomainObjectsAtPosition(position);
    }

    public Base getBase() {
        return (Base) getDomainObjectAtPosition(Settings.BASE_POSITION, Base.class);
    }

    public Resource getResourceAtPosition(Position position) {
        return (Resource) getDomainObjectAtPosition(position, Resource.class);
    }

//    public TrailSegment getTrailSegmentById(int followedPathId, Position position) {
//        Optional<DomainObject> optionalTrailSegment;
//        if (followedPathId <= 0) {
//            log.info("Going to follow a new trail.");
//            optionalTrailSegment = findNewTrailSegment(position);
//        } else {
//            optionalTrailSegment = findOldTrailSegment(followedPathId, position);
//        }
//        if (optionalTrailSegment.isPresent()) {
//            return (TrailSegment) optionalTrailSegment.get();
//        } else {
//            log.error("No trail segments start at {}", position);
//            return null;
//        }
//    }

    public TrailSegment findNewTrailSegment(Position position) {
        log.info("Going to follow a new trail.");
        List<DomainObject> trailSegmentsAtPosition = getAllDomainObjectsOfTypeAtPosition(position, TrailSegment.class);
        Optional<DomainObject> optionalTrailSegment = trailSegmentsAtPosition.stream().findAny();
        if (optionalTrailSegment.isPresent()) {
            return (TrailSegment) optionalTrailSegment.get();
        } else {
            log.error("No trail segments start at {}", position);
            return null;
        }
    }

    public TrailSegment findOldTrailSegment(int followedPathId, Position position) {
        List<DomainObject> trailSegmentsAtPosition = getAllDomainObjectsOfTypeAtPosition(position, TrailSegment.class);
        Optional<DomainObject> optionalTrailSegment = trailSegmentsAtPosition.stream().filter(
                domainObject -> ((TrailSegment) domainObject).getId() == followedPathId
        ).findAny();
        if (optionalTrailSegment.isPresent()) {
            return (TrailSegment) optionalTrailSegment.get();
        } else {
            log.error("No trail segments start at {}", position);
            return null;
        }
    }

    public enum Corner {
        LEFT_TOP,
        RIGHT_TOP,
        RIGHT_BOTTOM,
        LEFT_BOTTOM
    }
}
