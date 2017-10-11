package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.domain.original.dos.Resource;
import com.mkhabrat.omase.goals.ResourcePickedUp;

/**
 * Created by Maxon on 11.10.2017.
 */
public class ResourcePicker extends Role {

    public ResourcePicker() {
        this.name = RoleName.RESOURCE_PICKER;
        this.goal = new ResourcePickedUp();
    }

    @Override
    public void makeIteration(Area area, Agent agent) {
        Position currentPosition = agent.getPosition();
        // На всякий случай предполагаем, что тут точно есть ресурс
        assert area.positionHasResources(currentPosition);
        // Подбираем ресурс
        Resource resource = (Resource) area.getDomainObjectAtPosition(currentPosition, Resource.class);
        if (resource.getQuantity() < agent.getCapacity()) {
            // Если агент может взять все
            agent.setCarriedResourceAmount(resource.getQuantity());
            area.removeDomainObjects(currentPosition, resource);
        } else {
            // Если ресурсов больше, чем агент может унести
            agent.setCarriedResourceAmount(agent.getCapacity());
            int resourcesLeft = resource.getQuantity() - agent.getCapacity();
            resource.setQuantity(resourcesLeft);
        }
    }
}
