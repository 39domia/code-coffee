package com.service;

import com.model.Quantitative;

public interface QuantitativeService extends BaseService<Quantitative> {
    Quantitative findByIdProductAndIdIngredient(Long idProduct, Long idIngredient);

    boolean updateByIdProductAndIdIngredient(Quantitative quantitative, Long idProduct, Long idIngredient);

    boolean deleteByIdProductAndIdIngredient(Long idProduct, Long idIngredient);
}
