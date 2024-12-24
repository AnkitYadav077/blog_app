package com.backend.blog_app.Payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotBlank
    @Size(min=4,message = "Min size of category title is 4")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10,message = "min size of category desc is 10")
    private String categoryDescription;
}
