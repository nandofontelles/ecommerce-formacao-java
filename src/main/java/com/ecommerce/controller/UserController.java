package com.ecommerce.controller;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String dashboard(Model model, @RequestParam(required = false) User.UserStatus status){

        if (model.containsAttribute("adminSection")){
            model.addAttribute("adminSection", model.getAttribute("adminSection"));
        }else{
            model.addAttribute("adminSection", "user");
        }

        if (model.containsAttribute("user")){
            model.addAttribute("user", model.getAttribute("user"));
        }else{
            model.addAttribute("user", new User());
        }

        Iterable<User> users = userService.findAll(status);


        model.addAttribute("users", users);
        model.addAttribute("statusList", User.UserStatus.values());



        return "admin";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute User user){

        userService.createOrUpdate(user);

        System.out.println("User salvo: " + user);

        return "redirect:/user";
    }

    @GetMapping("/user/edit/{id}")
    public String edit(@PathVariable Long id, RedirectAttributes redirectAttributes){
        System.out.println("Editing : " + id);

        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            System.out.println("User found: "+id);
            redirectAttributes.addFlashAttribute("user", user.get());
        }else{
            System.out.println("User not found with ID: "+id);
        }
        return "redirect:/user";
    }

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        System.out.println("Deleting : " + id);

        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            System.out.println("User found: "+id);
            redirectAttributes.addFlashAttribute("user", user.get());
            redirectAttributes.addFlashAttribute("adminSection", "userDelete");
        }else{
            System.out.println("User not found with ID: "+id);
        }
        return "redirect:/user";
    }

    @GetMapping("/user/delete/confirmation/{id}")
    public String confirmDeleteUser(@PathVariable Long id){
        System.out.println("Confirming Deleting of user with ID: " + id);

        userService.deleteById(id);

        return "redirect:/user";
    }
}
