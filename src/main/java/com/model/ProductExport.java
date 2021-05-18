package com.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductExport {
    private Long id;
    private LocalDateTime dateExport;
    private String nameProduct;
    private Long quantity;
    private Long priceEach;
    private Long idProduct;
}
