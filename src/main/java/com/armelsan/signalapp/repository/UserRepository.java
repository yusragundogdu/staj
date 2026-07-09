package com.armelsan.signalapp.repository;

import com.armelsan.signalapp.model.Department;
import com.armelsan.signalapp.model.Role;
import com.armelsan.signalapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findUsersByDepartment(Department department);

    List<User> findUsersByRole(Role role);
}