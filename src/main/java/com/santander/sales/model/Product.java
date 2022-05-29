package com.santander.sales.model;


import com.santander.sales.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long _id;
    private String name;
    private Double price;
    private Long amount;


    public Product(ProductDTO productDTO) {
        this._id = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.amount = productDTO.getAmount();
    }
}
