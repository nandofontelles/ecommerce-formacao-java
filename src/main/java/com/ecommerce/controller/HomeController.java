package com.ecommerce.controller;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(required = false) Category category,
                       HttpSession session){

        model.addAttribute("categories",Category.values()  );
        model.addAttribute("title", "Super Loja Online");
        model.addAttribute("categoriesFilter", category != null ? category : "ALL");

        if(session.getAttribute("currentUser") != null) {
            model.addAttribute("currentUser", session.getAttribute("currentUser"));
        }

        if (session.getAttribute("shoppingCart") != null){
            model.addAttribute("shoppingCart", session.getAttribute("shoppingCart"));
        }

        if (session.getAttribute("shoppingCartView") != null){
            model.addAttribute("shoppingCartView", session.getAttribute("shoppingCartView"));
            model.addAttribute("cartTotalItens", session.getAttribute("cartTotalItens"));
            model.addAttribute("cartTotalValue", session.getAttribute("cartTotalValue"));
        }

        Iterable<Product> products = productService.findAllProducts(category);

        model.addAttribute("products", products);

        return "index";
    }

}
