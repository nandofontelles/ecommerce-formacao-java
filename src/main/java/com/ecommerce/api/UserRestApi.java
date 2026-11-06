package com.ecommerce.api;

import com.ecommerce.api.dto.UserResponseDTO;
import com.ecommerce.api.dto.UserUpdateStatusRequestDTO;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserRestApi {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        Iterable<User> users =  userService.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(UserResponseDTO::fromUser).toList();
    }

    @PutMapping("/{id}/status")
    public UserResponseDTO updateStatus(@PathVariable Long id, @RequestBody UserUpdateStatusRequestDTO request){
        System.out.println(id);
        System.out.println(request);
        User user = userService.updateStatus(id, request.status());
        return UserResponseDTO.fromUser(user);
    }
}
