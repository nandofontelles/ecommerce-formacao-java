package com.ecommerce.controller;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/signin")
    public String signIn(){

        return "signin";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        System.out.println("User logout id" );
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes,
                        HttpSession session){

        System.out.println("Logando o usuario: " + email);

        Optional<User> userOptional = userService.findByEmail(email);

        if (userOptional.isEmpty()){
            System.out.println("Usuario nao encontrado " + email);
            redirectAttributes.addFlashAttribute("error", "E-mail e/ou senha inválidos");
            return "redirect:/sigin";
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(password)){
            System.out.println("Invalid password for email :" + email);
            redirectAttributes.addFlashAttribute("error", "E-mail e/ou senha inválidos");
            return "redirect:/signin";
        }

        System.out.println("User logged in succesfully: " + user);
        session.setAttribute("currentUser", user);
        redirectAttributes.addFlashAttribute("message", "Login realizado com sucesso");

        return "redirect:/";
    }

}
