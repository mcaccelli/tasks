package com.taskmanager.tasks.domain.task;

import com.taskmanager.tasks.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_tasks")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title required.")
    private String title;
    @NotBlank(message = "Description required.")
    private String description;
    @NotBlank(message = "DueDate required.")
    private String dueDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Task(){
    }

    public Task(Long id, String title, String description, String dueDate, User user) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.user = user;
    }

    public Task(TaskRequestDTO requestDTO, User user){
        this.title = requestDTO.title();
        this.description = requestDTO.description();
        this.dueDate = requestDTO.dueDate();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
