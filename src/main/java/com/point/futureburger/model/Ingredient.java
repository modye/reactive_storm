package com.point.futureburger.model;

import lombok.Data;

import java.util.UUID;

/**
 *
 */
@Data
public class Ingredient {
    private UUID id;

    /**
     * Initializes an ingredient with a random auto-generated UUID.
     */
    public Ingredient() {
        this.id = UUID.randomUUID();
    }
}
