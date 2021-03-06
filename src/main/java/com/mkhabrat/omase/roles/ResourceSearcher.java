package com.mkhabrat.omase.roles;

import com.mkhabrat.omase.domain.original.Area;
import com.mkhabrat.omase.domain.original.Position;
import com.mkhabrat.omase.domain.original.dos.Agent;
import com.mkhabrat.omase.goals.ResourcesOrTrailFound;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class ResourceSearcher extends Role {

    private SearchDirection xDirection;
    private SearchDirection yDirection;

    public ResourceSearcher() {
        this.name = RoleName.RESOURCE_SEARCHER;
        this.goal = new ResourcesOrTrailFound();
        this.xDirection = SearchDirection.FROM_LEFT_TO_RIGHT;
        this.yDirection = SearchDirection.FROM_TOP_TO_BOTTOM;
    }

    public void makeIteration(Area area, Agent agent) {
        log.debug("Searching for new position.");
        int xDirectionInt = xDirection.getDirectionInt();
        int yDirectionInt = yDirection.getDirectionInt();
        Position oldPosition = agent.getPosition();
        Position newPosition = (Position) oldPosition.clone();

        // Сдвигаемся на одну клетку по х
        newPosition.incrementXBy(xDirectionInt);
        // Если мы натыкаемся на конец карты...
        if (area.positionOutOfXBound(newPosition)) {
            // ... Меняем направление по х...
            xDirection = xDirection.getOppositeDirection();
            // ... и сдвигаемся на новый ряд клеток (т. е. по оси у)
            newPosition = (Position) oldPosition.clone();
            newPosition.incrementYBy(yDirectionInt);
        }
        // В конце хода проверяем, не в углу ли мы. Если да, меняем направление Y в зависимости от угла
        if (area.positionIsCorner(newPosition)) {
            Area.Corner corner = area.getCornerAtPosition(newPosition);
            yDirection = SearchDirection.getYDirectionBasedOnCorner(corner);
        }

        log.debug("Chose new position: {}", newPosition);
        agent.move(area, oldPosition, newPosition);
    }

    private enum SearchDirection {
        FROM_LEFT_TO_RIGHT (1) {
            @Override
            public SearchDirection getOppositeDirection() {
                return FROM_RIGHT_TO_LEFT;
            }
        },
        FROM_RIGHT_TO_LEFT (-1) {
            @Override
            public SearchDirection getOppositeDirection() {
                return FROM_LEFT_TO_RIGHT;
            }
        },
        FROM_TOP_TO_BOTTOM (1) {
            @Override
            public SearchDirection getOppositeDirection() {
                return FROM_BOTTOM_TO_TOP;
            }
        },
        FROM_BOTTOM_TO_TOP (-1) {
            @Override
            public SearchDirection getOppositeDirection() {
                return FROM_TOP_TO_BOTTOM;
            }
        };

        @Getter
        private int directionInt;

        SearchDirection (int directionInt) {
            this.directionInt = directionInt;
        }

        public abstract SearchDirection getOppositeDirection();

        public static SearchDirection getYDirectionBasedOnCorner(Area.Corner corner) {
            if (corner == Area.Corner.LEFT_TOP || corner == Area.Corner.RIGHT_TOP) {
                return FROM_TOP_TO_BOTTOM;
            } else {
                return FROM_BOTTOM_TO_TOP;
            }
        }
    }
}
