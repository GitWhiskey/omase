package com.mkhabrat.omase;

import com.mkhabrat.omase.domain.original.Area;
import lombok.extern.slf4j.Slf4j;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.Base;
import com.mkhabrat.omase.domain.original.dos.Resource;
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
            if (allResourcesAreCollected.isAchieved(resources)) {
                break;
            }
        }
    }

    private void init() {
        // init area
        area = new Area(20, 10);
        // init base
        Base base = new Base(Settings.BASE_POSITION);
        area.placeDomainObject(base, base.getCurrentPosition());
        // init resources
        initResources();
        // init 3 agents
        initAgents();
    }

    private void initResources() {
        Resource r1 = new Resource(3,5,3);
        Resource r2 = new Resource(4, 1,6);
        Resource r3 = new Resource(10, 3, 7);
        Resource r4 = new Resource(19, 9,2);
        Resource r5 = new Resource(2, 8,1);

        resources.add(r1);
        resources.add(r2);
        resources.add(r3);
        resources.add(r4);
        resources.add(r5);

        area.placeDomainObject(r1, r1.getCurrentPosition());
        area.placeDomainObject(r2, r2.getCurrentPosition());
        area.placeDomainObject(r3, r3.getCurrentPosition());
        area.placeDomainObject(r4, r4.getCurrentPosition());
        area.placeDomainObject(r5, r5.getCurrentPosition());
    }

    private void initAgents() {
        Agent a1 = new Agent(1, new Position(7, 1), Settings.BASE_POSITION);
        Agent a2 = new Agent(2, new Position(15, 9), Settings.BASE_POSITION);
        Agent a3 = new Agent(3, new Position(0, 5), Settings.BASE_POSITION);

        agents.add(a1);
        agents.add(a2);
        agents.add(a3);

        area.placeDomainObject(a1, a1.getCurrentPosition());
        area.placeDomainObject(a2, a2.getCurrentPosition());
        area.placeDomainObject(a3, a3.getCurrentPosition());
    }

    private void makeStep() {
        // all agents must perform action based on their roles
        agents.forEach(a -> a.makeStep(area));
        delay(500);
    }

    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
