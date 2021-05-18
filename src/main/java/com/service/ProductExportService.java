package com.service;

import com.model.ProductExport;

import java.util.List;

public interface ProductExportService extends BaseService<ProductExport> {
    public List<ProductExport> findAllByDateExport(String dateIn, String dateOut);
}
