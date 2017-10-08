package ru.mkhabrat.omase.roles;

import lombok.extern.slf4j.Slf4j;
import ru.mkhabrat.omase.domain.Area;
import ru.mkhabrat.omase.domain.dos.Agent;

@Slf4j
public class ResourceSearcher extends Role {

    public void makeStep(Agent agent, Area area) {
        log.info("I'm a resource searcher and I made a step at position: {}.", agent.getCurrentPosition());
        if ()
    }
}
