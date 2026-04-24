package com.ra.dto.department.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private String description;
}
