package com.mkhabrat.omase.goals;

import com.mkhabrat.omase.domain.dos.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AllResourcesAreCollected extends Goal {

    public boolean isAchieved(Object... factors) {
        List<Resource> resources;
        try {
            resources = (List<Resource>) factors[0];
            if (resources.size() == 0) {
                log.info("Success! Primary goal is achieved and all resources are collected.");
                return true;
            } else {
                return false;
            }
        } catch (ClassCastException e) {
            log.error("There should be only one argument of type List<Resources>. Full exception: {}", e);
            return false;
        }
    }
}
