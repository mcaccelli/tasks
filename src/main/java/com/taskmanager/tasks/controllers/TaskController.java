package com.taskmanager.tasks.controllers;

import com.taskmanager.tasks.domain.task.Task;
import com.taskmanager.tasks.domain.task.TaskRequestDTO;
import com.taskmanager.tasks.dto.ResponseDTO;
import com.taskmanager.tasks.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam (required = false) String title,
            @RequestParam (required = false) String description,
            @RequestParam (required = false) String dueDate
    ){
        if(title == null){
            title = "";
        }
        if(description == null){
            description = "";
        }
        if(dueDate == null){
            dueDate = "";
        }
        List<Task> tasks = taskService.findTasksWithFilters(title,description,dueDate);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) throws Exception {
        Task task = taskService.findById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskRequestDTO requestDTO) throws Exception{
        Task newTask = taskService.createTask(requestDTO);
        return new ResponseEntity<>(newTask,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable Long id) throws Exception{
        taskService.deleteTask(id);
        ResponseDTO responseDTO = new ResponseDTO("Task with ID: " + id + " deleted successfully.");
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequestDTO requestDTO) throws Exception{
        Task updatedTask = taskService.updateTask(id, requestDTO);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

}
