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

    protected Area area;
    private List<Resource> resources = new ArrayList<>();
    private List<Agent> agents = new ArrayList<>();

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
            // Если главная цель (собрать все ресурсы) завершена, прерываем бесконечный цикл
            if (allResourcesAreCollected.isAchieved(area, null)) {
                break;
            }
        }
    }

    public void init() {
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
        // Менять набор и количество ресурсов здесь
        resources = Stream.of(
            new Resource(10,1,12),
            new Resource(4, 1,6),
            new Resource(10, 3, 7),
            new Resource(19, 9,38)
//            new Resource(2, 8,10),
//            new Resource(12, 2,10)
//            new Resource(8, 5,10)
        ).collect(Collectors.toList());

        int totalResources = 0;
        for (Resource r : resources) {
            area.placeDomainObjects(r.getPosition(), r);
            totalResources += r.getQuantity();
        }
        area.setTotalAmountOfResources(totalResources);
    }

    private void initAgents() {
        // Менять набор и атрибуты агентов здесь
        agents = Stream.of(
            new Agent(1, new Position(5, 1), 5, Settings.BASE_POSITION),
            new Agent(2, new Position(13, 9), 5, Settings.BASE_POSITION),
            new Agent(3, new Position(0, 5), 5, Settings.BASE_POSITION)
        ).collect(Collectors.toList());

        agents.forEach(a -> area.placeDomainObjects(a.getPosition(), a));
    }

    protected void makeIteration() {
        // all agents must perform action based on their roles
        agents.forEach(a -> a.playRoles(area));
        delay(500);
    }

    private void delay(int millis) {
        try {
            Thread.sleep(millis/2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
