package com.service;

import com.model.ImportProduct;

import java.util.List;

public interface ImportProductService extends BaseService<ImportProduct> {

    public List<ImportProduct> findByProduct(Long product);
}
