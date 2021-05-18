package com.service;

import com.model.Ingredient;

import java.util.List;

public interface IngredientService extends BaseService<Ingredient> {
    List<Ingredient> findAllNotQuantitativeProduct(Long idProduct);

}
