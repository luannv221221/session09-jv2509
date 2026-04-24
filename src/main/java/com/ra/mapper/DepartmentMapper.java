package com.ra.mapper;

import com.ra.dto.department.request.DepartmentRequestDTO;
import com.ra.dto.department.response.DepartmentResponseDTO;
import com.ra.entity.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    // chuyen tu entity => DTO
    DepartmentResponseDTO toDepartmentResponseDTO(Department department);
    List<DepartmentResponseDTO> toDepartmentResponseDTO(List<Department> departmentList);
    // chuyen tu DTO => Entity
    Department toDepartment(DepartmentRequestDTO departmentRequestDTO);
}
