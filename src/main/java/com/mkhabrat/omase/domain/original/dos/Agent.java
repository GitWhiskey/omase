package com.mkhabrat.omase.domain.original.dos;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.roles.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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

    /**
     * Здесь происходит смена ролей в зависимости от законченной роли, состояния агента и его положения на карте
     * @param finishedRole Завершенная роль
     * @param area Карта
     */
    private void changeRoleList(Role finishedRole, Area area) {
        switch (finishedRole.getName()) {
            case RESOURCE_SEARCHER: {
                // Если роль "Поисковик ресурсов" завершена, значит мы либо наткнулись на ресурс, либо на след,
                // оставленный другим агентом
                this.roles = area.positionHasResources(this.position) // Если на текущей позиции есть ресурс...
                    // ... добавляем роль "Поднимателя ресурсов" ...
                    ? Collections.singletonList(new ResourcePicker())
                    // ... иначе "Следователь по пути"
                    : Collections.singletonList(new TrailFollower(this, area, this.position));
                break;
            }
            case RESOURCE_PICKER: {
                boolean resourcesAllPickedUp = !area.positionHasResources(this.position);
                boolean trailPresent = area.positionHasTrailSegment(this.position);
                // Агент будет нести ресурс на базу
                this.roles = new ArrayList<>();
                this.roles.add(new ResourceToBaseCarrier());
                // Если при этом ресурсы еще остались, а след еще не проложен - попутно прокладываем след
                if (!resourcesAllPickedUp && !trailPresent) {
                    this.roles.add(new TrailCreator(this.id, area, this.position));
                    this.followedPathId = this.id;
                // Если все ресурсы в этой позиции собраны и проложен путь - его нужно попутно убирать
                } else if (resourcesAllPickedUp && trailPresent) {
                    this.roles.add(new TrailRemover(area, position));
                }
                break;
            }
            case RESOURCE_TO_BASE_CARRIER: {
                // Если роль "Носитель до базы" завершена, значит мы на базе, поэтому сбрасываем ресурс
                this.roles = Collections.singletonList(new ResourceAtBaseDropper());
                break;
            }
            case RESOURCE_AT_BASE_DROPPER: {
                // Сбросив ресурс, агент смотрит, присустствует ли след до базы.
                this.roles = area.positionHasTrailSegment(this.position)
                        // Если присутствует следуем по нему
                        ? Collections.singletonList(new TrailFollower(this, area, this.position))
                        // Иначе идем искать другие ресурсы
                        : Collections.singletonList(new ResourceSearcher());
                break;
            }
            case TRAIL_FOLLOWER: {
                // Если роль "Следователь по пути" завершена, значит агент либо потерял след
                // (его затер другой агент, забрав последний ресурс), либо добрался до ресурса, и должен его поднять
                boolean agentLostTrail = this.followedPathId <= 0;
                if (agentLostTrail) {
                    this.roles = Collections.singletonList(new ResourceSearcher());
                } else {
                    this.roles = Collections.singletonList(new ResourcePicker());
                }
                break;
            } case TRAIL_CREATOR: {
                // При завершении этой роли ничего делать не нужно
                break;
            } case TRAIL_REMOVER: {
                // При завершении этой роли ничего делать не нужно
                break;
            }
            default:
                log.error("Unknown role: {}", finishedRole.getName());
        }
    }
}
