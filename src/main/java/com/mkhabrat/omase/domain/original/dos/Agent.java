package com.mkhabrat.omase.domain.original.dos;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.roles.ResourceCollector;
import lombok.Getter;
import lombok.Setter;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.roles.ResourceSearcher;
import com.mkhabrat.omase.roles.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Agent extends DomainObject {

    /** ID, чтобы агентов было проще различать в логах **/
    @Getter
    private int id;

    /** Роль агента **/
    @Getter @Setter
    private Role role;

    /** Ресурс, который агент несет на базу. **/
    @Getter @Setter
    private Resource collectedResource;

    /** Расположение базы **/
    @Getter
    private Position basePosition;

    public Agent(int id, Position position, Position basePosition) {
        super(position);
        this.id = id;
        this.basePosition = basePosition;
        this.role = new ResourceSearcher();
        log.debug("Agent {} created. Init position - {}.", id, position);
    }

    public void makeStep(Area area) {
        log.info("Agent {} as a {} makes a step.", id, role.getName());
        Position newPosition = role.act(area, currentPosition);
        setCurrentPosition(newPosition);
        if (role.getGoal().isAchieved(area, getCurrentPosition())) {
            changeToNextRole();
        }
    }

    private void changeToNextRole() {
        Role.RoleName currentRole = role.getName();
        switch (currentRole) {
            case RESOURCE_SEARCHER:
                collectedResource = (Resource) area.getObjectAtPosition(currentPosition);

                setRole(new ResourceCollector());
                break;
            case RESOURCE_COLLECTOR:
                setRole(new ResourceSearcher());
                break;
            default:
                log.error("Unknown role: {}", currentRole);
        }
    }
}
