package com.ShopHoa.controller;

import com.ShopHoa.entity.Cart;
import com.ShopHoa.entity.CartItem;
import com.ShopHoa.entity.Flower;
import com.ShopHoa.entity.Order;
import com.ShopHoa.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private final OrderService orderService;
    @GetMapping("/checkout")
    public String showCheckoutForm(Model model, HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getCartItemList().isEmpty()) {
            // Xử lý khi không có giỏ hàng
            return "redirect:/show-shopping-cart";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        model.addAttribute("order", new Order()); // Đối tượng Order để nhập thông tin giao hàng

        return "checkout-form";

    }

    @PostMapping("/place-order")
    public String placeOrder(@ModelAttribute("order") Order order, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        Map<Flower, Integer> flowerQuantities = new HashMap<>();
        if (cart == null || cart.getCartItemList().isEmpty()) {
            // Xử lý khi không có giỏ hàng
            return "redirect:/show-shopping-cart";
        }

        for(CartItem cartItem: cart.getCartItemList())
        {
            Flower flower = cartItem.getFlower();
            int quantity = cartItem.getQuantity();
            flowerQuantities.put(flower, quantity);


        }
        order.setFlowerQuantities(flowerQuantities);
        orderService.save(order);
        session.removeAttribute("cart");
        return "order-success";
    }
}





