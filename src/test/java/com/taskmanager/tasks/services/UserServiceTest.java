package com.taskmanager.tasks.services;

import com.taskmanager.tasks.domain.task.Task;
import com.taskmanager.tasks.repositories.TaskRepository;
import com.taskmanager.tasks.repositories.UserRepository;
import com.taskmanager.tasks.domain.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @InjectMocks
    TaskService taskService;

    @Mock
    UserRepository userRepository;

    @Mock
    TaskRepository taskRepository;

    @Mock
    EntityManager entityManager;

    User user;

    Task task;

    @BeforeEach
    public void setUp(){
        user = new User(null, "Mateus", "Caccelli", "mateus@gmail.com","123456");
        task = new Task(null, "Reboot","Task for reboot the server", "09/28/2023", user);
    }

    @Test
    void deveBuscarUsuariosPorIdComSucesso() throws Exception {
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        user = service.findById(user.getId());

        assertEquals(user, user);
        verify(userRepository).findById(user.getId());
        verifyNoMoreInteractions(userRepository);

    }

    @Test
    void deveBuscarTarefaPorTituloComSucesso(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        when(entityManager.createQuery(cq).getResultList()).thenReturn(Collections.singletonList(task));
        List<Task> task = taskService.findTasksWithFilters("Reboot","","");

        assertEquals(Collections.singletonList(task), task);
        verify(entityManager.createQuery(cq).getResultList());
        verifyNoMoreInteractions(entityManager);
    }
}
