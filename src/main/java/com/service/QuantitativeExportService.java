package com.service;

import com.model.QuantitativeExport;

import java.util.List;

public interface QuantitativeExportService extends BaseService<QuantitativeExport> {
    public List<QuantitativeExport> findAllByDateExport(String dateIn, String dateOut);
}
