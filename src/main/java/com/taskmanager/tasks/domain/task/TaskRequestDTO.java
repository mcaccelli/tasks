package com.taskmanager.tasks.domain.task;


public record TaskRequestDTO(String title, String description, String dueDate, Long user_id) {
}
