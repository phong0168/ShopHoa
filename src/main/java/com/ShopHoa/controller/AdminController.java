package com.ShopHoa.controller;

import com.ShopHoa.entity.Category;
import com.ShopHoa.entity.Flower;
import com.ShopHoa.service.CategoryService;
import com.ShopHoa.service.FlowerService;
import com.ShopHoa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final FlowerService flowerService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    @Autowired
    public AdminController(FlowerService flowerService, CategoryService categoryService, OrderService orderService) {
        this.flowerService = flowerService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @GetMapping("/products")
    public String products(Model model){
        List<Flower> flowers = flowerService.findAll();
        model.addAttribute("flowers", flowers);
        return "/admin/admin-products";
    }

    @GetMapping("/products/showFormForAdd")
    public String showFormForAdd(Model model){
        Flower flower = new Flower();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("flower", flower);
        model.addAttribute("categories", categories);
        return "/admin/showForm";
    }

    @GetMapping("/products/showFormForUpdate")
    public String showFormForUpdate(Model model, int id){
        Flower flower = flowerService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("flower", flower);
        return "/admin/showForm";
    }

    @PostMapping("/products/save")
    public String save(@ModelAttribute("flower") Flower flower){
        flower.setCategory(categoryService.findById(flower.getCategory().getId()));
        flowerService.save(flower);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete")
    public String delete(int id){
        flowerService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/tracking")
    public String tracking(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "/admin/tracking";
    }

    @GetMapping("/tracking/detail")
    public String trackingDetail(Model model, int id){
//        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("order", orderService.findOrderAndProductsByOrderId(id));
        return "/admin/tracking-detail";
    }
}
