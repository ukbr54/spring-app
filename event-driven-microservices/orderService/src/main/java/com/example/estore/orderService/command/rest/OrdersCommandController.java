package com.example.estore.orderService.command.rest;

import com.example.estore.orderService.command.commands.CreateOrderCommand;
import com.example.estore.orderService.core.model.OrderStatus;
import com.example.estore.orderService.core.model.OrderSummary;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public OrdersCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

//    @PostMapping
//    public OrderSummary createOrder(@Valid @RequestBody OrderCreateRest order){
//        String userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";
//        String orderId = UUID.randomUUID().toString();
//
//        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder().addressId(order.getAddressId())
//                .productId(order.getProductId()).userId(userId).quantity(order.getQuantity()).orderId(orderId)
//                .orderStatus(OrderStatus.CREATED).build();
//
//
//    }
}
