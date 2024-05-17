package com.scaler.microsoftecommerce.service;


import com.scaler.microsoftecommerce.dto.FakeStoreProductDto;
import com.scaler.microsoftecommerce.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+ productId, FakeStoreProductDto.class
        );
        assert fakeStoreProductDto != null;
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class
        );
        assert fakeStoreProducts != null;
        return Arrays.stream(fakeStoreProducts).map(FakeStoreProductDto::toProduct).collect(Collectors.toList());
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fs = new FakeStoreProductDto(product);
        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fs,
                FakeStoreProductDto.class
        );
        assert response != null;
        return response.toProduct();
    }



    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fs = new FakeStoreProductDto(product);
        restTemplate.put("https://fakestoreapi.com/products/"+ productId, fs);
        return getSingleProduct(productId);
    }

    @Override
    public void deleteProduct(Long productId) {
    restTemplate.delete("https://fakestoreapi.com/products/"+ productId);
    }
}
