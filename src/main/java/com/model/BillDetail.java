package com.model;

import lombok.Data;

@Data
public class BillDetail {
    private Long idOrder;
    private String nameProduct;
    private Long idProduct;
    private Long quantity;
    private Long priceEach;
}
