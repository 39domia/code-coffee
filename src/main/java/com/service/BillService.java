package com.service;

import com.model.Bill;

import java.util.List;

public interface BillService extends BaseService<Bill> {
    Bill findByIdOrder(Long idOrder);

    boolean deleteByIdOrder(Long idOrder);

    public List<Bill> findByDateExport(String dateExportIn, String dateExportOut);
}
