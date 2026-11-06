package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class ShopCartController {

    @Autowired
    private ProductService productService;

    @GetMapping("/add")
    public String addToCart(@RequestParam Long productId, HttpSession session) {

        if(session.getAttribute("currentUser") == null){
            System.out.println("User not logged in. Redirecting ro SingIn");
            return "redirect:/signin";
        }

        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isEmpty()){
            System.out.println("Product with ID: " + productId + " not found");
            return "redirect:/";
        }

        Product product = productOptional.get();
        List<Product> shoppingCart;
        if (session.getAttribute("shoppingCart") != null){
            shoppingCart = (List<Product>) session.getAttribute("shoppingCart");
        }else{
            shoppingCart = new ArrayList<>();
        }

        shoppingCart.add(product);
        session.setAttribute("shoppingCart", shoppingCart);

        System.out.println("Product added to the cart: " + product.getName());
        System.out.println("Total items in cart: " + shoppingCart.size());


        return"redirect:/";
    }

    @GetMapping("/view")
    public String viewCart(HttpSession session) {

        //caso usuario nao esteja logado, redirecionar para pagina de login
        if (session.getAttribute("currentUser") == null){
            System.out.println("User not logged in. Redirecting to SignIn");
            return "redirect:/signin";
        }


        //Agrupar produtos por ID
        List<Product> shoppingCart = (List<Product>) session.getAttribute("shoppingCart");

        Map<Long, CartItemView> cartItemViewMap = new HashMap<>();
        for (Product product : shoppingCart) {
            if (cartItemViewMap.containsKey(product.getId())) {
                CartItemView item = cartItemViewMap.get(product.getId());
                item.incrementQuantity();
            }else{
                cartItemViewMap.put(product.getId(), new CartItemView(product, 1));
            }
        }

        List<CartItemView> cartItens = new ArrayList<>(cartItemViewMap.values());

        //calcular totais
        int totalItens = shoppingCart.size();
        Double totalValue = cartItens.stream()
                .map(CartItemView::getSubTotal)
                .reduce(0.0, Double::sum);

        session.setAttribute("shoppingCartView", cartItens);
        session.setAttribute("cartTotalItens", totalItens);
        session.setAttribute("cartTotalValue", totalValue);

        return "redirect:/";
    }

    public static class CartItemView{
        private final Product product;
        private int quantity;

        public CartItemView(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void incrementQuantity(){
            this.quantity++;
        }

        public Double getSubTotal(){
            return product.getPrice() * quantity;
        }
    }

}
