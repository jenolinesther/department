package com.example.department.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.department.model.departmentModel;

@Repository
public interface departmentRepo extends JpaRepository<departmentModel, Integer> {
}

