package com.taskmanager.tasks.services;

import com.taskmanager.tasks.domain.task.Task;
import com.taskmanager.tasks.domain.task.TaskRequestDTO;
import com.taskmanager.tasks.domain.user.User;
import com.taskmanager.tasks.repositories.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager entityManager;

    public List<Task> findTasksWithFilters(String filterTitle, String filterDescription, String filterDueDate) {
        if (filterTitle.isEmpty() && filterDescription.isEmpty() && filterDueDate.isEmpty()) {
            return this.taskRepository.findAll();
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root<Task> root = cq.from(Task.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!filterTitle.isEmpty()) {
            predicates.add(cb.equal(root.get("title"), filterTitle));
        }

        if (!filterDescription.isEmpty()) {
            predicates.add(cb.equal(root.get("description"), filterDescription));
        }

        if (!filterDueDate.isEmpty()) {
            predicates.add(cb.equal(root.get("dueDate"), filterDueDate));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

    public Task findById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()->new Exception("Task not found"));
    }

    public void saveTask(Task task){
        this.taskRepository.save(task);
    }

    public Task createTask(TaskRequestDTO requestDTO) throws Exception {
        User user = userService.findById(requestDTO.user_id());
        Task newTask = new Task(requestDTO, user);
        this.saveTask(newTask);
        return newTask;
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, TaskRequestDTO requestDTO) throws Exception {
        Task task = this.findById(id);
        String title = requestDTO.title();
        String description = requestDTO.description();
        String dueDate = requestDTO.dueDate();
        if (title != null && !title.isEmpty()){
            task.setTitle(title);
        }
        if (description != null && !description.isEmpty()){
            task.setDescription(description);
        }
        if (dueDate != null && !dueDate.isEmpty()){
            task.setDueDate(dueDate);
        }
        this.saveTask(task);
        return task;
    }

}
