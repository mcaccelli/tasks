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

    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }

    public User createUser(UserDTO user){
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public User updateUser(Long id, UserDTO userDTO) throws Exception {
        User user = this.findById(id);

        String newFirstName = userDTO.firstName();
        if (newFirstName != null && !newFirstName.isEmpty()) {
            user.setFirstName(newFirstName);
        }

        String newLastName = userDTO.lastName();
        if (newLastName != null && !newLastName.isEmpty()) {
            user.setLastName(newLastName);
        }

        String newEmail = userDTO.email();
        if (newEmail != null && !newEmail.isEmpty()) {
            user.setEmail(newEmail);
        }

        String newPassword = userDTO.password();
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }
        this.saveUser(user);
        return user;
    }
}
