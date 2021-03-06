package com.service.impl;

import com.dao.QuantitativeInventoryDAO;
import com.model.QuantitativeInventory;
import com.service.QuantitativeInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantitativeInventoryServiceImpl implements QuantitativeInventoryService {
    @Autowired
    QuantitativeInventoryDAO quantitativeInventoryDAO;

    @Override
    public List<QuantitativeInventory> findAll() {
        return quantitativeInventoryDAO.findAll();
    }

    @Override
    public QuantitativeInventory findByID(Long id) {
        return quantitativeInventoryDAO.findByID(id);
    }

    @Override
    public boolean save(QuantitativeInventory quantitativeInventory) {
        return quantitativeInventoryDAO.save(quantitativeInventory);
    }

    @Override
    public boolean update(QuantitativeInventory quantitativeInventory) {
        return quantitativeInventoryDAO.update(quantitativeInventory);
    }

    @Override
    public boolean delete(Long id) {
        return quantitativeInventoryDAO.delete(id);
    }

    @Override
    public List<QuantitativeInventory> findAllByDate(String dateIn, String dateOut) {
        return quantitativeInventoryDAO.findAllByDate(dateIn,dateOut);
    }
}
