package com.service;

import com.model.Table;

import java.util.List;

public interface TableService extends BaseService<Table>{

    public List<Table> findByArea(Long id);

    public boolean updateStatus(Table table);


}
