package com.taskmanager.tasks.repositories;

import com.taskmanager.tasks.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
