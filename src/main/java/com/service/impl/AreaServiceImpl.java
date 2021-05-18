package com.service.impl;

import com.dao.AreaDAO;
import com.model.Area;
import com.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDAO areaDAO;

    @Override
    public List<Area> findAll() {
        return areaDAO.findAll();
    }

    @Override
    public Area findByID(Long id) {
        return areaDAO.findByID(id);
    }

    @Override
    public boolean save(Area area) {
        return areaDAO.save(area);
    }

    @Override
    public boolean update(Area area) {
        return areaDAO.update(area);
    }

    @Override
    public boolean delete(Long id) {
        return areaDAO.delete(id);
    }
}
