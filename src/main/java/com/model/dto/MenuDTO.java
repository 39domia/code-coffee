package com.model.dto;

import com.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {
    private Long idProductLine;

    private String nameProductLine;

    private List<Product> productList;
}
