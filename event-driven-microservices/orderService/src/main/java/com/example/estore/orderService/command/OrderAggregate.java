package com.example.estore.orderService.command;

import com.example.estore.orderService.command.commands.ApproveOrderCommand;
import com.example.estore.orderService.command.commands.CreateOrderCommand;
import com.example.estore.orderService.command.commands.RejectOrderCommand;
import com.example.estore.orderService.core.events.OrderApprovedEvent;
import com.example.estore.orderService.core.events.OrderCreatedEvent;
import com.example.estore.orderService.core.events.OrderRejectedEvent;
import com.example.estore.orderService.core.model.OrderStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {

    private Logger LOGGER = LoggerFactory.getLogger(OrderAggregate.class);
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    public OrderAggregate(){
        LOGGER.info("Commands Responsibility - {} ",this.getClass().getSimpleName());
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand){
        LOGGER.info("Commands Responsibility - CreateOrderCommand - {} ",this.getClass().getSimpleName());
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand,orderCreatedEvent);

        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) throws Exception {
        LOGGER.info("Event source Handler - OrderCreatedEvent");
        this.orderId = orderCreatedEvent.getOrderId();
        this.productId = orderCreatedEvent.getProductId();
        this.userId = orderCreatedEvent.getUserId();
        this.addressId = orderCreatedEvent.getAddressId();
        this.quantity = orderCreatedEvent.getQuantity();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
    }

    @CommandHandler
    public void handle(ApproveOrderCommand approveOrderCommand) {
        // Create and publish the OrderApprovedEvent
        OrderApprovedEvent orderApprovedEvent = new OrderApprovedEvent(approveOrderCommand.getOrderId());
        AggregateLifecycle.apply(orderApprovedEvent);
    }

    @EventSourcingHandler
    public void on(OrderApprovedEvent orderApprovedEvent) {
        this.orderStatus = orderApprovedEvent.getOrderStatus();
    }

    @CommandHandler
    public void handle(RejectOrderCommand rejectOrderCommand) {
        OrderRejectedEvent orderRejectedEvent = new OrderRejectedEvent(rejectOrderCommand.getOrderId(), rejectOrderCommand.getReason());
        AggregateLifecycle.apply(orderRejectedEvent);
    }

    @EventSourcingHandler
    public void on(OrderRejectedEvent orderRejectedEvent) {
        this.orderStatus = orderRejectedEvent.getOrderStatus();
    }
}
