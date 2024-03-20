package com.example.estore.productService.command;

import com.example.estore.productService.core.data.ProductLookupEntity;
import com.example.estore.productService.core.data.ProductLookupRepository;
import com.example.estore.productService.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    private Logger LOGGER = LoggerFactory.getLogger(ProductLookupEventsHandler.class);
    private final ProductLookupRepository productLookupRepository;
    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        LOGGER.info("Event Handling Components - {}",this.getClass().getSimpleName());
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(),event.getTitle());
        productLookupRepository.save(productLookupEntity);
    }
}
