package com.ra.repository;

import com.ra.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    boolean existsByName(String name);
}
