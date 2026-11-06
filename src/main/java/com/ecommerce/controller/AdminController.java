package com.ecommerce.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Model model, HttpSession session){
        model.addAttribute("adminSection","dashboard");

        if(session.getAttribute("currentUser") != null) {
            model.addAttribute("currentUser", session.getAttribute("currentUser"));
        }

        return "admin";
    }

}
