package com.ra.service;

import com.ra.dto.department.request.DepartmentRequestDTO;
import com.ra.dto.department.response.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponseDTO> findAll();
    DepartmentResponseDTO findById(Long id);
    DepartmentResponseDTO create(DepartmentRequestDTO departmentRequestDTO);
}
