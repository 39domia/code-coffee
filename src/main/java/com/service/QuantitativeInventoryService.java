package com.service;

import com.model.QuantitativeInventory;

import java.util.List;

public interface QuantitativeInventoryService extends BaseService<QuantitativeInventory>{
    public List<QuantitativeInventory> findAllByDate(String dateIn, String dateOut);
}
