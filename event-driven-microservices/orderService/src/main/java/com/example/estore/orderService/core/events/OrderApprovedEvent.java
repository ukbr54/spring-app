package com.example.estore.orderService.core.events;

import com.example.estore.orderService.core.model.OrderStatus;
import lombok.Value;

@Value
public class OrderApprovedEvent {
    private final String orderId;
    private final OrderStatus orderStatus = OrderStatus.APPROVED;
}
