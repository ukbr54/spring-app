package com.example.products.service.impl;

import com.example.core.ProductCreatedEvent;
import com.example.products.model.CreateProductRestModel;
import com.example.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private Logger logger  = LoggerFactory.getLogger(ProductServiceImpl.class);
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    public ProductServiceImpl(KafkaTemplate<String,ProductCreatedEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductRestModel productRestModel) throws Exception{
        String productId = UUID.randomUUID().toString();

        //TODO: Persist product details into database table before publishing an event
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
                productRestModel.getTitle(),
                productRestModel.getPrices(),
                productRestModel.getQuantity());

        //Kafka Asynchronous send
/**        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);

        future.whenComplete((result,exception) -> {
           if(Objects.nonNull(exception)){
               logger.error("***** Failed to send message: "+exception.getMessage());
           }else{
               logger.info("***** Message sent successfully: "+result.getRecordMetadata());
           }
        });**/

       logger.info("Before publishing a ProductCreatedEvent");
       //Kafka Synchronous send
        SendResult<String, ProductCreatedEvent> result =
                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent).get();

        logger.info("Partition: "+result.getRecordMetadata().partition());
        logger.info("Topic: "+result.getRecordMetadata().topic());
        logger.info("Offset: "+result.getRecordMetadata().offset());

        logger.info("****** Returning product id");
        return productId;
    }
}
