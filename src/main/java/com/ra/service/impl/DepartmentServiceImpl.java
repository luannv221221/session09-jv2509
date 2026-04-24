package com.ra.service.impl;

import com.ra.config.exception.HttpConflict;
import com.ra.dto.department.request.DepartmentRequestDTO;
import com.ra.dto.department.response.DepartmentResponseDTO;
import com.ra.entity.Department;
import com.ra.mapper.DepartmentMapper;
import com.ra.repository.DepartmentRepository;
import com.ra.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponseDTO> findAll() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentMapper.toDepartmentResponseDTO(departmentList);
    }

    @Override
    public DepartmentResponseDTO findById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Not found"));
        return departmentMapper.toDepartmentResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO create(DepartmentRequestDTO departmentRequestDTO) {

        // kiem tra xem ten phong ban da ton tai hay chua
        if (departmentRepository.existsByName(departmentRequestDTO.getName())){
            throw new HttpConflict("Ten phong ban da ton tai");
        }
        // convert DTO -> Entity
        /* Dung thu cong
        Department department = Department.
                builder()
                .name(departmentRequestDTO.getName())
                .description(departmentRequestDTO.getDescription())
                .build();
        Department departmentNew = departmentRepository.save(department);

        return DepartmentResponseDTO.builder()
                .id(departmentNew.getId())
                .name(departmentNew.getName())
                .description(departmentNew.getDescription())
                .build();


         */
        // dung struct-mapper

        Department department = departmentRepository.save(departmentMapper.toDepartment(departmentRequestDTO));

        return departmentMapper.toDepartmentResponseDTO(department);
    }
}
