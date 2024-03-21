package com.example.estore.productService.command.rest;

import com.example.estore.productService.command.CreateProductCommand;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private final CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel){
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .title(createProductRestModel.getTitle())
                .quantity(createProductRestModel.getQuantity())
                .productId(UUID.randomUUID().toString()).build();

        String returnValue = commandGateway.sendAndWait(createProductCommand);
//        try{
//             returnValue = commandGateway.sendAndWait(createProductCommand);
//        }catch (Exception e){
//            returnValue = e.getLocalizedMessage();
//        }

        return returnValue;
    }
}
