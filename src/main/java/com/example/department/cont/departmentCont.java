package com.example.department.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.department.service.departmentService;
import com.example.department.model.departmentModel;
import com.example.department.repo.departmentRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class departmentCont {

    @Autowired
    private departmentService departmentService;
    @Autowired
    private departmentRepo departmentRepo;

    @GetMapping("/all")
    public List<departmentModel> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<departmentModel> getDepartmentById(@PathVariable int id) {
        Optional<departmentModel> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDepartment(@RequestBody departmentModel department) {
        try {
            departmentModel savedDept = departmentService.createDepartment(department);
            return ResponseEntity.ok(savedDept);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<departmentModel> updateDepartment(@RequestBody departmentModel department) {
        return departmentRepo.findById(department.getDepartmentId()).map(existingDept -> {
            existingDept.setName(department.getName());
            existingDept.setCode(department.getCode());
            existingDept.setCollegeId(department.getCollegeId());
            existingDept.setContactEmail(department.getContactEmail());
            existingDept.setPhoneNumber(department.getPhoneNumber());
            existingDept.setActive(department.isActive());

            departmentModel updatedDept = departmentRepo.save(existingDept);
            return ResponseEntity.ok(updatedDept);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
