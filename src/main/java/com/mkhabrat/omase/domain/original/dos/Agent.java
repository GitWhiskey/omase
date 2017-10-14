package com.mkhabrat.omase.domain.original.dos;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.roles.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
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

    @Getter @Setter
    private int followedPathId = 0;

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
                changeRoleList(role, area);
            }
        }
    }

    private void changeRoleList(Role finishedRole, Area area) {
        switch (finishedRole.getName()) {
            case RESOURCE_SEARCHER:
                Resource resource = area.getResourceAtPosition(this.position);
                boolean trailPresent = area.positionHasTrailSegment(this.position);
                // Если агент может взять все и до ресурса есть след, агент должен его убрать
                this.roles = resource.getQuantity() <= this.capacity && trailPresent
                        ? Arrays.asList(new ResourcePicker(), new TrailRemover())
                        : Collections.singletonList(new ResourcePicker());
                break;
            case RESOURCE_PICKER:
                // Если путь уже проложен просто несем на базу, иначе несем на базу и создаем след
                if (area.positionHasTrailSegment(this.position)) {
                    this.roles = Collections.singletonList(new ResourceToBaseCarrier());
                } else {
                    this.roles = Arrays.asList(new ResourceToBaseCarrier(), new TrailCreator(this.id, this.position));
                    this.followedPathId = this.id;
                }
                break;
            case RESOURCE_TO_BASE_CARRIER:
                this.roles = Collections.singletonList(new ResourceAtBaseDropper());
                break;
            case RESOURCE_AT_BASE_DROPPER:
                this.roles = area.positionHasTrailSegment(this.position)
                        ? Collections.singletonList(new TrailFollower(this.followedPathId, area, this.position))
                        : Collections.singletonList(new ResourceSearcher());
                break;
            case TRAIL_FOLLOWER:
                this.roles = this.followedPathId <= 0
                        ? Collections.singletonList(new ResourceSearcher())
                        : Collections.singletonList(new ResourcePicker());
                break;
            default:
                log.error("Unknown role: {}", finishedRole.getName());
        }
    }
}
