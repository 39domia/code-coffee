package com.service.impl;

import com.dao.ProductDAO;
import com.model.Product;
import com.service.ProductService;
import com.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private UploadService uploadService;


    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findByID(Long id) {
        return productDAO.findByID(id);
    }

    @Override
    public List<Product> findByProductLine(Long id) {
        return productDAO.findByProductLine(id);
    }

    @Override
    public List<Product> findAllNotIngredient() {
        return productDAO.findAllNotIngredient();
    }

    @Override
    public List<Product> findAllIngredient() {
        return productDAO.findAllIngredient();
    }

    @Override
    public List<Product> findByProductLineAndProductName(Long idProductLine, String nameProduct) {
        return productDAO.findByProductLineAndProductName(idProductLine, nameProduct);
    }

    public void uploadAndSaveProductImage(Product product) throws IOException {
        Map uploadResult = uploadService.upload(product.getMultiImage());
        String url = (String) uploadResult.get("secure_url");
        product.setImage(url);
        productDAO.save(product);
    }

    @Override
    public boolean updateInventory(Product product){
        return productDAO.updateInventory(product);
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
