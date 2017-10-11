package com.mkhabrat.omase;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.Base;
import com.mkhabrat.omase.domain.original.dos.Resource;
import com.mkhabrat.omase.goals.AllResourcesAreCollected;
import com.mkhabrat.omase.goals.Goal;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            makeIteration();
            if (allResourcesAreCollected.isAchieved(area, null)) {
                break;
            }
        }
    }

    private void init() {
        // init area
        area = new Area(20, 10);
        // init base
        Base base = new Base(Settings.BASE_POSITION);
        area.placeDomainObjects(base.getPosition(), base);
        // init resources
        initResources();
        // init 3 agents
        initAgents();
    }

    private void initResources() {
        resources = Stream.of(
            new Resource(3,5,3),
            new Resource(4, 1,6),
            new Resource(10, 3, 7),
            new Resource(19, 9,2),
            new Resource(2, 8,1)
        ).collect(Collectors.toList());

        int totalResources = 0;
        for (Resource r : resources) {
            area.placeDomainObjects(r.getPosition(), r);
            totalResources += r.getQuantity();
        }
        area.setTotalAmountOfResources(totalResources);
    }

    private void initAgents() {
        Agent a1 = new Agent(1, new Position(7, 1), 5, Settings.BASE_POSITION);
        Agent a2 = new Agent(2, new Position(15, 9), 5, Settings.BASE_POSITION);
        Agent a3 = new Agent(3, new Position(0, 5), 5, Settings.BASE_POSITION);

        agents.add(a1);
        agents.add(a2);
        agents.add(a3);

        area.placeDomainObjects(a1.getPosition(), a1);
        area.placeDomainObjects(a2.getPosition(), a2);
        area.placeDomainObjects(a3.getPosition(), a3);
    }

    private void makeIteration() {
        // all agents must perform action based on their roles
        agents.forEach(a -> a.playRoles(area));
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
