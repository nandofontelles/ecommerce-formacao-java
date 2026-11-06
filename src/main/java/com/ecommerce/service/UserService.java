package com.ecommerce.service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createOrUpdate(User user) {

        if (user.getId() == null){
            user.setStatus(User.UserStatus.ACTIVE);
            user.setCreatedAt(LocalDateTime.now());
        }

        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> findAll(User.UserStatus status) {
        if (status != null) {
            return userRepository.findByStatus(status);
        }else {
            return userRepository.findAll();
        }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public User updateStatus(Long id, User.UserStatus status){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            user.setStatus(status);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }

    }

}
