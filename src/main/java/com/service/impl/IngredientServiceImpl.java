package com.service.impl;

import com.dao.IngredientDAO;
import com.model.Ingredient;
import com.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientDAO ingredientDAO;

    @Override
    public List<Ingredient> findAll() {
        return ingredientDAO.findAll();
    }

    public List<Ingredient> findAllNotQuantitativeProduct(Long idProduct) {
        return ingredientDAO.findAllNotQuantitativeProduct(idProduct);
    }

    @Override
    public Ingredient findByID(Long id) {
        return ingredientDAO.findByID(id);
    }

    @Override
    public boolean save(Ingredient ingredient) {
        return ingredientDAO.save(ingredient);
    }

    @Override
    public boolean update(Ingredient ingredient) {
        return ingredientDAO.update(ingredient);
    }

    @Override
    public boolean delete(Long id) {
        return ingredientDAO.delete(id);
    }
}
