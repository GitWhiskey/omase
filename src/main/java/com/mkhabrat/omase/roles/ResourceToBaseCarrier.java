package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.Settings;
import com.mkhabrat.omase.domain.astar.DomainNode;
import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.goals.ResourceBroughtToBase;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by Maxon on 08.10.2017.
 */
@Slf4j
@ToString
public class ResourceToBaseCarrier extends Role {

    public ResourceToBaseCarrier() {
        this.name = RoleName.RESOURCE_TO_BASE_CARRIER;
        this.goal = new ResourceBroughtToBase();
    }

    public void makeIteration(Area area, Agent agent) {
        log.debug("Getting shortest path to base.");
        List<DomainNode> path = area.findShortestPath(agent.getPosition(), Settings.BASE_POSITION);
        printPath(path);

        DomainNode nextNode = path.get(0);
        Position oldPosition = agent.getPosition();
        Position newPosition = new Position(nextNode.getxPosition(), nextNode.getyPosition());
        agent.move(area, oldPosition, newPosition);
    }

    private void printPath(List<DomainNode> path) {
        StringBuilder message = new StringBuilder();
        for (DomainNode p : path) {
            message.append(" -> ")
                    .append("(")
                    .append(p.getxPosition())
                    .append(",")
                    .append(p.getyPosition())
                    .append(")");
        }
        log.debug(message.toString());
    }
}
