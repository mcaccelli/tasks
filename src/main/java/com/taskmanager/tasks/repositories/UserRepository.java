package com.taskmanager.tasks.repositories;

import com.taskmanager.tasks.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
