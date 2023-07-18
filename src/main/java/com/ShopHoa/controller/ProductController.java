package com.ShopHoa.controller;

import com.ShopHoa.entity.Category;
import com.ShopHoa.entity.Flower;
import com.ShopHoa.service.CategoryServiceImpl;
import com.ShopHoa.service.FlowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ProductController {
    private final FlowerServiceImpl flowerService;
    private final CategoryServiceImpl categoryService;

    @Autowired
    public ProductController(FlowerServiceImpl flowerService, CategoryServiceImpl categoryService) {
        this.flowerService = flowerService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String findAll(Model model, @RequestParam(value = "sort", defaultValue = "") String sort, @RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "1") int page){

        List<Flower> flowers = flowerService.findByName(search);
        switch (sort)
        {
            case "price-asc":
                flowers = flowers.stream().sorted(Comparator.comparing(Flower::getPrice)).collect(Collectors.toList());
                break;
            case "price-desc":
                flowers = flowers.stream().sorted(Comparator.comparing(Flower::getPrice).reversed()).collect(Collectors.toList());
                break;
        }
        int noOfRecordPerPage = 4;
        int noOfPage = (int) Math.ceil((double) flowers.size() / noOfRecordPerPage);
        int noOfRecordToSkip = (page - 1) * noOfRecordPerPage;
        flowers = flowers.subList(noOfRecordToSkip, Math.min(noOfRecordToSkip + noOfRecordPerPage, flowers.size()));
        model.addAttribute("noOfPage", noOfPage);
        model.addAttribute("search", search);
        model.addAttribute("flowers", flowers);
        model.addAttribute("sort", sort);
        return "products";
    }
    @GetMapping("/products/detail")
    public String detail(Model model, int id){
        Flower flower = flowerService.findById(id);
        model.addAttribute("flower", flower);
        return "product-detail";
    }
    @GetMapping("/category")
    public String findByCategory(Model model, int id){
        List<Flower> flowers = flowerService.findByCategory(id);
        model.addAttribute("flowers", flowers);
        return "products";
    }




}
