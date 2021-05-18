package com.service.impl;

import com.dao.ImportProductDAO;
import com.model.ImportProduct;
import com.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportProductServiceImpl implements ImportProductService {

    @Autowired
    private ImportProductDAO importProductDAO;

    @Override
    public List<ImportProduct> findAll() {
        return importProductDAO.findAll();
    }

    @Override
    public ImportProduct findByID(Long id) {
        return importProductDAO.findByID(id);
    }

    @Override
    public boolean save(ImportProduct importProduct) {
        return importProductDAO.save(importProduct);
    }

    @Override
    public boolean update(ImportProduct importProduct) {
        return importProductDAO.update(importProduct);
    }

    @Override
    public boolean delete(Long id) {
        return importProductDAO.delete(id);
    }

    @Override
    public List<ImportProduct> findByProduct(Long product) {
        return importProductDAO.findByProduct(product);
    }
}
