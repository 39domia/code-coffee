package com.model.dto;

import com.model.Area;
import com.model.Table;
import lombok.Data;

import java.util.List;

@Data
public class AreaDTO {

    private Area area;

    private List<Table> tableList;
}
