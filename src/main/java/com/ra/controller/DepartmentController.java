package com.ra.controller;

import com.ra.dto.department.ResponseWrapper;
import com.ra.dto.department.request.DepartmentRequestDTO;
import com.ra.dto.department.response.DepartmentResponseDTO;
import com.ra.entity.Department;
import com.ra.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<DepartmentResponseDTO> departmentResponseDTOList = departmentService.findAll();
        return ResponseEntity.status(200).body(ResponseWrapper.success(departmentResponseDTOList,"Danh sach phong ban",HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id){
        DepartmentResponseDTO department = departmentService.findById(id);
        return ResponseEntity.status(200).body(ResponseWrapper.success(department,"lay ve ban ghi thanh cong",HttpStatus.OK.value()));
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO){
        DepartmentResponseDTO departmentResponseDTO = departmentService.create(departmentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(departmentResponseDTO,"Them moi thanh cong",HttpStatus.CREATED.value()));
    }
}
