package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.TrailSegment;
import com.mkhabrat.omase.goals.TrailCreated;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrailCreator extends Role {

    private TrailSegment previousTrailSegment;

    public TrailCreator(int trailId, Area area, Position startPosition) {
        this.name = RoleName.TRAIL_CREATOR;
        this.goal = new TrailCreated();
        TrailSegment trailStart = new TrailSegment(trailId, startPosition);
        area.placeDomainObjects(startPosition, trailStart);
        this.previousTrailSegment = trailStart;
    }

    @Override
    public void makeIteration(Area area, Agent agent) {
        // Создаем сегмент пути на текущей позиции
        Position currentPosition = agent.getPosition();
        TrailSegment currentTrailSegment = new TrailSegment(agent.getId(), currentPosition);
        // Связываем текущий сегмент с предыдущим, если это не первый элемент в цепи
        currentTrailSegment.setPrevious(previousTrailSegment);
        previousTrailSegment.setNext(currentTrailSegment);
        // Кладем сегмент на текущую позицию и заменяем предыдущий для следующего шага
        area.placeDomainObjects(currentPosition, currentTrailSegment);
        previousTrailSegment = currentTrailSegment;
    }
}
