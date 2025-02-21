package com.dev.backend.dtos;

import org.springframework.beans.BeanUtils;

import com.dev.backend.projections.ProductProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String unitMeasure;
    private Double costPrice;
    private Double salePrice;
    private Integer minimumStock;
    private Integer maximumStock;
    private Integer quantity;

    public ProductDTO(ProductProjection entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
