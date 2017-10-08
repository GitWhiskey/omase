package com.mkhabrat.omase;

import com.mkhabrat.omase.domain.Area;
import lombok.extern.slf4j.Slf4j;
import com.mkhabrat.omase.domain.Position;
import com.mkhabrat.omase.domain.dos.Agent;
import com.mkhabrat.omase.domain.dos.Base;
import com.mkhabrat.omase.domain.dos.Resource;
import com.mkhabrat.omase.goals.AllResourcesAreCollected;
import com.mkhabrat.omase.goals.Goal;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class App {

    private Goal allResourcesAreCollected = new AllResourcesAreCollected();

    private Area area;
    private List<Resource> resources = new ArrayList<Resource>();
    private List<Agent> agents = new ArrayList<Agent>();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        log.info("Running OMaSE app.");
        // init all elements
        init();
        // run infinite loop
        while (true) {
            makeStep();
            if (allResourcesAreCollected.isAchieved(resources, agents)) {
                log.info("Goal achieved.");
                break;
            }
        }
    }

    private void init() {
        // init area
        area = new Area(20, 10);
        // init base
        Base base = new Base();
        area.placeDomainObject(base, 0, 0);
        // init resources
        initResources();
        // init 3 agents
        initAgents();
    }

    private void initResources() {
        Resource r1 = new Resource(3);
        Resource r2 = new Resource(6);
        Resource r3 = new Resource(7);
        Resource r4 = new Resource(2);
        Resource r5 = new Resource(1);

        resources.add(r1);
        resources.add(r2);
        resources.add(r3);
        resources.add(r4);
        resources.add(r5);

        area.placeDomainObject(r1, 3, 5);
        area.placeDomainObject(r2, 4, 1);
        area.placeDomainObject(r3, 10, 3);
        area.placeDomainObject(r4, 19, 9);
        area.placeDomainObject(r5, 2, 8);
    }

    private void initAgents() {
        Agent a1 = new Agent(new Position(7, 1));
        Agent a2 = new Agent(new Position(15, 9));
        Agent a3 = new Agent(new Position(0, 5));

        agents.add(a1);
        agents.add(a2);
        agents.add(a3);

        area.placeDomainObject(a1, a1.getCurrentPosition());
        area.placeDomainObject(a2, a2.getCurrentPosition());
        area.placeDomainObject(a3, a3.getCurrentPosition());
    }

    private void makeStep() {
        // all agents must perform action based on their roles
        for (Agent a: agents) {
            a.makeStep(area);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
