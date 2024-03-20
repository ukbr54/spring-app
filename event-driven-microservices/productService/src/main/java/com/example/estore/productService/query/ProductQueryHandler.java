package com.example.estore.productService.query;

import com.example.estore.productService.core.data.ProductEntity;
import com.example.estore.productService.core.data.ProductRepository;
import com.example.estore.productService.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query){
        List<ProductRestModel> productRest = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();
        for(ProductEntity productEntity : productEntities){
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity,productRestModel);
            productRest.add(productRestModel);
        }
        return productRest;
    }
}
