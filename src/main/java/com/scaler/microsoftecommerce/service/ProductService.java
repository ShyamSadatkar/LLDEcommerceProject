package com.scaler.microsoftecommerce.service;

import com.scaler.microsoftecommerce.model.Product;

import java.util.List;

public interface ProductService {

   Product getSingleProduct(Long productId);

   List<Product> getAllProducts();

   Product createProduct(Product product);

   Product updateProduct(Long productId, Product product);

   void deleteProduct(Long productId);



}
