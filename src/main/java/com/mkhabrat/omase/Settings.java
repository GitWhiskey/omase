package com.mkhabrat.omase;

import com.mkhabrat.omase.domain.original.Position;
import lombok.experimental.UtilityClass;

/**
 * Created by Maxon on 09.10.2017.
 */
@UtilityClass
public class Settings {
    public Position BASE_POSITION = new Position(0,0);
    public int PIXELS_PER_MAP_TILE = 50;
}
