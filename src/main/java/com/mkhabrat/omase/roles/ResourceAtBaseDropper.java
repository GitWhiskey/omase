package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.Base;
import com.mkhabrat.omase.goals.ResourceDroppedAtBase;

public class ResourceAtBaseDropper extends Role {

    public ResourceAtBaseDropper() {
        this.name = RoleName.RESOURCE_AT_BASE_DROPPER;
        this.goal = new ResourceDroppedAtBase();
    }

    @Override
    public void makeIteration(Area area, Agent agent) {
        Base base = area.getBase();
        int amountOfResourcesCarried = agent.getCarriedResourceAmount();
        // Агент сбрасывает на базу ресурсы
        base.addResourcesToBase(amountOfResourcesCarried);
        agent.setCarriedResourceAmount(0);
    }
}
