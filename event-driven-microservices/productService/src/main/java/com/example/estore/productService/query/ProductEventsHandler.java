package com.example.estore.productService.query;

import com.example.estore.productService.core.data.ProductEntity;
import com.example.estore.productService.core.data.ProductRepository;
import com.example.estore.productService.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    private Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);

    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception{
        //Log error message
        throw new Exception();
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception){
        //Log error message
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event,productEntity);
        LOGGER.info("Event Handling Component - {}",this.getClass().getSimpleName());
        try{
            productRepository.save(productEntity);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
