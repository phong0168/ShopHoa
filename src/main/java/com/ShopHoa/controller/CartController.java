package com.ShopHoa.controller;

import com.ShopHoa.entity.Cart;
import com.ShopHoa.entity.CartItem;
import com.ShopHoa.entity.Flower;
import com.ShopHoa.service.FlowerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final FlowerService flowerService;

    @Autowired
    public CartController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/show-shopping-cart")
    public String cart(Model model, HttpSession session){
        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", new Cart());
        }
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("totalQuantity", cart.getTotalQuantity());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        model.addAttribute("cart", cart);
        return "shopping-cart";
    }

    @GetMapping("/add-to-cart")
    public String addToCart(Model model, HttpSession session, int id) {
        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", new Cart());
        }
        Flower flower = flowerService.findById(id);
        Cart cart = (Cart) session.getAttribute("cart");
        cart.addCartItem(new CartItem(flower, 1));
        model.addAttribute("cart", cart);
        return "redirect:show-shopping-cart";
    }

    @GetMapping("/remove-from-cart")
    public String removeFromCart(Model model, HttpSession session, int id) {

        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeCartItem(id);
        return "redirect:show-shopping-cart";
    }

    @PostMapping("/update-cart")
    public String updateCart(@RequestParam("id") int id, @RequestParam("quantity") int quantity, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        cart.updateCartItem(id, quantity);
        return "redirect:show-shopping-cart";
    }



}
