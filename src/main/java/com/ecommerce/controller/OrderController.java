package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.User;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String dashboard(Model model, @RequestParam(required = false) Order.OrderStatus status, HttpSession session){

        if (session.getAttribute("currentUser") == null){
            System.out.println("User not looged in. Redirecting to SignIn");
            return "redirect:/signin";
        }else{
            System.out.println("User is logged in as: " + session.getAttribute("currentUser"));
            model.addAttribute("currentUser", session.getAttribute("currentUser"));
        }

        model.addAttribute("adminSection", "order");

        User currentUser = (User) session.getAttribute("currentUser");
        boolean isAdmin = "admin@admin.com".equalsIgnoreCase(currentUser.getEmail());

        Iterable<Order> orders = orderService.findAllByStatus(status, isAdmin, currentUser);

       if (isAdmin){
           model.addAttribute("statusFilter", Objects.requireNonNullElse(status, "ALL"));
        } else {
           model.addAttribute("statusFilter", Objects.requireNonNullElse(status, "ALL"));
        }

        model.addAttribute("orders", orders);
        model.addAttribute("statusList", Order.OrderStatus.values());
        model.addAttribute("isAdmin", isAdmin);

        return "admin";
    }

    @GetMapping("/order/checkout")
    public String checkout(Model model, HttpSession session){

        if (session.getAttribute("currentUser") == null){
            System.out.println("User not logged in. Redirecting to SignIn");
            return "redirect:/signin";
        }

        return "redirect:/order/checkout-page";
    }

    @GetMapping("/order/checkout-page")
    public String checkoutPage(Model model, HttpSession session){
        if (session.getAttribute("currentUser") == null){
            System.out.println("User not logged in. Redirecting to SignIn");
            return "redirect:/signin";
        }

        User currentUser = (User) session.getAttribute("currentUser");
        List<ShopCartController.CartItemView> shoppingCartItemView =(List<ShopCartController.CartItemView>) session.getAttribute("shoppingCartView");

        if (shoppingCartItemView == null || shoppingCartItemView.isEmpty()){
            System.out.println("Cart is empety. Redirecting to home");
            return "redirect:/";
        }

        model.addAttribute("shoppingCartView", shoppingCartItemView);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("cartTotalItens", session.getAttribute("cartTotalItens"));
        model.addAttribute("cartTotalValue", session.getAttribute("cartTotalValue"));

        return "checkout";
    }

    @PostMapping("/order/finalize")
    public String finalizeOrder(HttpSession session){
        if (session.getAttribute("currentUser") == null){
            System.out.println("User not logged in. Redirecting to SignIn");
            return "redirect:/signin";
        }

        User currentUser = (User) session.getAttribute("currentUser");

        List<ShopCartController.CartItemView> shoppingCartItemView =(List<ShopCartController.CartItemView>) session.getAttribute("shoppingCartView");

        if (shoppingCartItemView == null || shoppingCartItemView.isEmpty()){
            System.out.println("Cart is empety. Redirecting to home");
            return "redirect:/";
        }

        Integer cartTotalItens = (Integer) session.getAttribute("cartTotalItens");
        Double cartTotalValue = (Double) session.getAttribute("cartTotalValue");

        Order  order = new Order(currentUser, cartTotalValue, cartTotalItens);

        for (ShopCartController.CartItemView itemView : shoppingCartItemView){
            OrderItem item = new OrderItem(itemView.getProduct(),
                    itemView.getProduct().getPrice(),
                    itemView.getQuantity(),
                    itemView.getSubTotal());

            order.addItem(item);
        }

        orderService.createOrUpdate(order);

        session.setAttribute("orderSucess", true);
        session.setAttribute("orderId", order.getId());

        return "redirect:/order/sucess";
    }

    @GetMapping("/order/sucess")
    public String orderSucess(Model model, HttpSession session){
        return "order-sucess";
    }

}
