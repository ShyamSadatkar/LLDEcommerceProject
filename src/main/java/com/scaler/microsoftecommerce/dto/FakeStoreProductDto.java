package com.scaler.microsoftecommerce.dto;

import com.scaler.microsoftecommerce.model.Category;
import com.scaler.microsoftecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDto {
  private Long id;
  private String title;
  private String description;
  private Double price;
  private String image;
  private String category;

    public FakeStoreProductDto(Product product) {
      this.id = product.getId();
      this.title = product.getTitle();
      this.description = product.getDescription();
      this.price = product.getPrice();
      this.image = product.getImageUrl();
      this.category = product.getCategory().getTitle();


    }

    public Product toProduct(){
      Product product = new Product();
      product.setId(this.id);
      product.setTitle(this.title);
      product.setDescription(this.description);
      product.setPrice(this.price);
      product.setImageUrl(this.image);

      Category category = new Category();
      category.setTitle(this.category);
      product.setCategory(category);

      return product;
    }
}
