package com.armelsan.signalapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @NotBlank(message = "Kullanıcı adı boş bırakılamaz")
    private String username;

    @NotBlank(message = "E-posta adresi boş bırakılamaz")
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@hotmail\\.com$", message = "Sadece @hotmail.com uzantılı adresler geçerlidir")
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne()
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;


    @OneToMany(mappedBy = "assignee", cascade = CascadeType.REMOVE)
    private List<Task> assignedTasks;


    private boolean requiresPasswordChange = true;

    public User() {}

    public User(String username, String password, String email, Department department, Role role) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.department = department;
        this.role = role != null ? role : Role.USER;
    }


    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public boolean isRequiresPasswordChange() {
        return requiresPasswordChange;
    }

    public void setRequiresPasswordChange(boolean requiresPasswordChange) {
        this.requiresPasswordChange = requiresPasswordChange;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}