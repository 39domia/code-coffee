package com.nobita.demo.model;

import lombok.Data;

@Data
public class QuantitativeInventory {
    private String nameIngredient;
    private Double importIngredient;
    private Double exportIngredient;
    private Double inventoryIngredient;
}
