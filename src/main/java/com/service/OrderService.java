package com.service;

import com.model.Order;

public interface OrderService extends BaseService<Order>{

    Order findByTable(Long idTable);
}
