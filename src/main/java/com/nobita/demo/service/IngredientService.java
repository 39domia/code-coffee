package com.nobita.demo.service;

import com.nobita.demo.model.Ingredient;

import java.util.List;

public interface IngredientService extends BaseService<Ingredient> {
    List<Ingredient> findAllNotQuantitativeProduct(Long idProduct);

}
