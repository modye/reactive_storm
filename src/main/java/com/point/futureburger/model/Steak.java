package com.point.futureburger.model;

import lombok.Data;

/**
 *
 */
@Data
public class Steak extends Ingredient{
    private SteakState state;

    public Steak() {
        this.state = SteakState.RAW;
    }
}