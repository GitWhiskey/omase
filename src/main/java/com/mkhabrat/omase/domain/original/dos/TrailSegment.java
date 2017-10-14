package com.mkhabrat.omase.domain.original.dos;

import com.mkhabrat.omase.domain.original.Position;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(of = {"id", "position"})
public class TrailSegment extends DomainObject {

    /** id пути для того, чтобы пути можно было различать **/
    @Getter
    private int id;

    @Getter @Setter
    private TrailSegment previous;

    @Getter @Setter
    private TrailSegment next;

    public TrailSegment(int id, Position position) {
        super(position);
        this.id = id;
    }
}
