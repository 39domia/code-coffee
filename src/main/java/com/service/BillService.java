package com.service;

import com.model.Bill;

import java.util.List;

public interface BillService extends BaseService<Bill> {
    Bill findByIdOrder(Long idOrder);

    Bill sumTotalPriceAll();
    Bill countBills();

    boolean deleteByIdOrder(Long idOrder);

    List<Bill> findByDateExport(String dateExportIn, String dateExportOut);

}
