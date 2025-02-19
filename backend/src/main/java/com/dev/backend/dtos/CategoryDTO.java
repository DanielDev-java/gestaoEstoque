package com.dev.backend.dtos;

import org.springframework.beans.BeanUtils;

import com.dev.backend.projections.CategoryProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;

    public CategoryDTO(CategoryProjection entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
