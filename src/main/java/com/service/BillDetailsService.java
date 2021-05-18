package com.service;

import com.model.ProductExport;
import com.model.QuantitativeExport;
import com.model.BillDetail;

import java.util.List;

public interface BillDetailsService extends BaseService<BillDetail> {
    List<BillDetail> findByIdOrder(Long idOrder);

    boolean deleteByIdOrder(Long idOrder);

    List<QuantitativeExport> getQuantitativeExport(Long idOrder);

    List<ProductExport> getProductExport(Long idOrder);
}
