package com.mkhabrat.omase.domain.original.dos;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.roles.ResourcePicker;
import com.mkhabrat.omase.roles.ResourceSearcher;
import com.mkhabrat.omase.roles.ResourceToBaseCarrier;
import com.mkhabrat.omase.roles.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class Agent extends DomainObject {

    /** ID, чтобы агентов было проще различать в логах **/
    @Getter
    private int id;

    /** Роли агента **/
    @Getter @Setter
    private List<Role> roles;

    /** Количество ресурса, который агент несет на базу. **/
    @Getter @Setter
    private int carriedResourceAmount;

    /** Какое количество ресурса агент может взять **/
    @Getter @Setter
    private int capacity;

    /** Расположение базы **/
    @Getter
    private Position basePosition;

    public Agent(int id, Position position, int capacity, Position basePosition) {
        super(position);
        this.id = id;
        this.capacity = capacity;
        this.basePosition = basePosition;
        this.roles = Collections.singletonList(new ResourceSearcher());
        log.debug("Agent {} created. Init position - {}.", id, position);
    }

    public void playRoles(Area area) {
        for (Role role : roles) {
            log.info("Agent {} as a {} makes an iteration.", id, role.getName());
            role.makeIteration(area, this);
            if (role.getGoal().isAchieved(area, this)) {
                changeRoleList(role);
            }
        }
    }

    private void changeRoleList(Role finishedRole) {
        switch (finishedRole.getName()) {
            case RESOURCE_SEARCHER:
                roles = Collections.singletonList(new ResourcePicker());
                break;
            case RESOURCE_PICKER:
                roles = Collections.singletonList(new ResourceToBaseCarrier());
                break;
            default:
                log.error("Unknown role: {}", finishedRole.getName());
        }
    }
}
