package com.example.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.department.model.departmentModel;
import com.example.department.repo.departmentRepo;

import jakarta.persistence.EntityExistsException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class departmentService {
    
    @Autowired
    private departmentRepo departmentRepo;

    public List<departmentModel> getAllDepartments() {
        return departmentRepo.findAll();
    }

    public Optional<departmentModel> getDepartmentById(int id) {
        return departmentRepo.findById(id);
    }

    @Transactional
    public departmentModel createDepartment(departmentModel department) {
        if (departmentRepo.existsById(department.getDepartmentId())) {
            throw new RuntimeException("Department ID already exists! Choose a unique ID.");
        }

        return departmentRepo.save(department);
    }


    public departmentModel updateDepartment(int id, departmentModel updatedDepartment) {
        return departmentRepo.findById(id).map(department -> {
            department.setName(updatedDepartment.getName());
            department.setCode(updatedDepartment.getCode());
            department.setCollegeId(updatedDepartment.getCollegeId());
            department.setContactEmail(updatedDepartment.getContactEmail());
            department.setPhoneNumber(updatedDepartment.getPhoneNumber());
            department.setActive(updatedDepartment.isActive());
            department.setUpdatedAt(LocalDateTime.now());
            return departmentRepo.save(department);
        }).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public void deleteDepartment(int id) {
        departmentRepo.deleteById(id);
    }
}