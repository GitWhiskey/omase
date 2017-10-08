package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.Area;
import com.mkhabrat.omase.domain.dos.Agent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceSearcher extends Role {

    public void makeStep(Agent agent, Area area) {
        log.info("I'm a resource searcher and I made a step at position: {}.", agent.getCurrentPosition());
    }
}
