package com.point.futureburger.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Data
public class Burger {
    @NotNull
    private Bacon bacon;
    @NotNull
    private Bread bread;
    @NotNull
    private Cheese cheese;
    @NotNull
    private Salad salad;
    @NotNull
    private Salad salsa;
    @NotNull
    private Steak steak;
    @NotNull
    private Tomato tomato;

    public List<Ingredient> getIngredients() {
        return Arrays.asList(bacon, bread, cheese, salad, salsa, steak, tomato);
    }
}

