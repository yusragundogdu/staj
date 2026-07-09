package com.armelsan.signalapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task_table")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- SENİN EKLEDİĞİN ALANLAR ---
    @Column(name = "tanim", length = 500)
    private String tanim;

    @ManyToMany
    @JoinTable(
            name = "task_observers",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> gozlemciler;

    private LocalDateTime startDate; // İşin başlangıcı
    private LocalDateTime dueDate; // Bitiş tarihi
    private LocalDateTime createdAt = LocalDateTime.now(); // Oluşturma tarihi

    @ManyToOne()
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;


    public Task() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTanim() { return tanim; }
    public void setTanim(String tanim) { this.tanim = tanim; }

    public List<User> getGozlemciler() {
        return gozlemciler;
    }

    public void setGozlemciler(List<User> gozlemciler) {
        this.gozlemciler = gozlemciler;
    }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User getAssignee() { return assignee; }
    public void setAssignee(User assignee) { this.assignee = assignee; }

    public User getCreator() { return creator; }
    public void setCreator(User creator) { this.creator = creator; }
}