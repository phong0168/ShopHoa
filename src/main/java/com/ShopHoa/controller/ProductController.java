package com.ShopHoa.controller;

import com.ShopHoa.dao.FavoriteRepository;
import com.ShopHoa.entity.Category;
import com.ShopHoa.entity.Favorite;
import com.ShopHoa.entity.Flower;
import com.ShopHoa.entity.User;
import com.ShopHoa.service.CategoryServiceImpl;
import com.ShopHoa.service.FavoriteService;
import com.ShopHoa.service.FlowerServiceImpl;
import com.ShopHoa.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ProductController {
    private final FlowerServiceImpl flowerService;
    private final FavoriteService favoriteService;
    private final UserService userService;

    @Autowired
    public ProductController(FlowerServiceImpl flowerService, FavoriteService favoriteService, UserService userService) {
        this.flowerService = flowerService;
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @GetMapping("/products")
    public String findByName(Model model, @RequestParam(value = "sort", defaultValue = "") String sort, @RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "1") int page){

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

    @GetMapping("/favorite/add")
    public String addToFavorite(int id, Principal principal){
        final String currentUser = principal.getName();
        Favorite favorite = new Favorite();
        favorite.setFlower(flowerService.findById(id));
        favorite.setUser(userService.findByUserName(currentUser));
        favoriteService.save(favorite);
        return "redirect:/products/detail?id=" + id;
    }

    @GetMapping("/favorite/delete")
    public String deleteFavorite(int id){
        favoriteService.deleteById(id);
        return "redirect:/favorite/show-favorites";
    }

    @GetMapping("/favorite/show-favorites")
    public String showFavorite(Model model, Principal principal){
        List<Favorite> favoriteList = favoriteService.findByUserId(userService.findByUserName(principal.getName()).getId());
        model.addAttribute("favoriteList", favoriteList);
        return "show-favorites";
    }

}
