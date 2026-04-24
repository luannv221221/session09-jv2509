package com.ra.dto.department.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentRequestDTO {
    @NotBlank(message = "Tên phong ban không được để rỗng")
    private String name;
    private String description;

}
