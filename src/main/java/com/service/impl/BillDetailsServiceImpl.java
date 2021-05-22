package com.service.impl;

import com.dao.BillDetailsDAO;
import com.model.ProductExport;
import com.model.QuantitativeExport;
import com.model.BillDetail;
import com.service.BillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailsServiceImpl implements BillDetailsService {

    @Autowired
    BillDetailsDAO billDetailsDAO;

    @Override
    public List<BillDetail> findAll() {
        return billDetailsDAO.findAll();
    }

    @Override
    public BillDetail findByID(Long id) {
        return billDetailsDAO.findByID(id);
    }

    @Override
    public List<QuantitativeExport> getQuantitativeExport(Long idOrder){
        return billDetailsDAO.getQuantitativeExport(idOrder);
    }

    @Override
    public List<ProductExport> getProductExport(Long idOrder){
        return billDetailsDAO.getProductExport(idOrder);
    }

    @Override
    public boolean save(BillDetail billDetail) {
        return billDetailsDAO.save(billDetail);
    }

    @Override
    public boolean update(BillDetail billDetail) {
        return billDetailsDAO.update(billDetail);
    }

    @Override
    public boolean delete(Long id) {
        return billDetailsDAO.delete(id);
    }

    @Override
    public List<BillDetail> findByIdOrder(Long idOrder) {
        return billDetailsDAO.findByIdOrder(idOrder);
    }

    @Override
    public List<BillDetail> topSellLimit() {
        return billDetailsDAO.topSellLimit();
    }

    @Override
    public boolean deleteByIdOrder(Long idOrder) {
        return billDetailsDAO.deleteByIdOrder(idOrder);
    }
}
