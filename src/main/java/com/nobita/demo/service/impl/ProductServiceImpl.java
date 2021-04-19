package com.nobita.demo.service.impl;

import com.nobita.demo.dao.ProductDAO;
import com.nobita.demo.model.Product;
import com.nobita.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findByID(Long id) {
        return productDAO.findByID(id);
    }

    @Override
    public List<Product> findByProductLine(Long id){
        return productDAO.findByProductLine(id);
    }

    @Override
    public boolean save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public boolean update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDAO.delete(id);
    }
}
