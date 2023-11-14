package com.taskmanager.tasks.config;

import com.taskmanager.tasks.domain.task.Task;
import com.taskmanager.tasks.domain.user.User;
import com.taskmanager.tasks.repositories.TaskRepository;
import com.taskmanager.tasks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("postgresql")
public class PostgresqlConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception{
//        User user1 = new User(null, "Mateus", "Caccelli", "mateus@gmail.com","123456");
//        User user2 = new User(null, "Joao", "Silva", "joao@gmail.com","123456");
//
//        userRepository.saveAll(Arrays.asList(user1,user2));
//
//        Task task1 = new Task(null, "Reboot","Task for reboot the server", "09/28/2023",user1);
//        Task task2 = new Task(null, "Reboot","Task for reboot the notebook", "08/15/2022",user1);
//        Task task3 = new Task(null, "Shutdown","Task for shutdown the server","02/06/1998",user2);
//
//        taskRepository.saveAll(Arrays.asList(task1,task2,task3));
    }
}
