package com.nobita.demo.service;

import com.nobita.demo.model.QuantitativeInventory;

import java.util.List;

public interface QuantitativeInventoryService extends BaseService<QuantitativeInventory>{
    public List<QuantitativeInventory> findAllByDate(String dateIn, String dateOut);
}
