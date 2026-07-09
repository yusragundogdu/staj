package com.armelsan.signalapp.controller;

import com.armelsan.signalapp.model.Department;
import com.armelsan.signalapp.model.User;
import com.armelsan.signalapp.repository.DepartmentRepository;
import com.armelsan.signalapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentController(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        Department savedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(savedDepartment);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        if (!departmentRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Hata: Departman bulunamadı!");
        }

        Department department = departmentRepository.getReferenceById(id);

        List<User> users = userRepository.findUsersByDepartment(department);

        for (User user: users) {
            user.setDepartment(null);
        }

        for (int i = 0; i < 10; i++) {

        }

        userRepository.saveAll(users);

        departmentRepository.deleteById(id);
        return ResponseEntity.ok("Departman başarıyla silindi.");
    }
}