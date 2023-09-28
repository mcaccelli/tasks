package com.taskmanager.tasks.services;

import com.taskmanager.tasks.domain.user.User;
import com.taskmanager.tasks.domain.user.UserDTO;
import com.taskmanager.tasks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id) throws Exception{
        return userRepository.findById(id).orElseThrow(()->new Exception("User not found"));
    }

    public void saveUser(User user){
        this.userRepository.save((user));
    }

    public User deleteUser(Long id) throws Exception {
        User deletedUser = findById(id);
        this.userRepository.delete(deletedUser);
        return deletedUser;
    }

    public User createUser(UserDTO user){
        User newUser = new User(user);
        this.saveUser(newUser);
        return  newUser;
    }
}
