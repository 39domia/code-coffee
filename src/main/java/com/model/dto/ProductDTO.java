package com.model.dto;

import com.model.ImportProduct;
import com.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    Product product;

    List<ImportProduct> importProductList;
}
