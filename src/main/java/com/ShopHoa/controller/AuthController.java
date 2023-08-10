package com.ShopHoa.controller;
import com.ShopHoa.entity.User;
import com.ShopHoa.service.UserService;
import com.ShopHoa.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;


@Controller
public class AuthController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register/showRegistrationForm")
    public String showRegistrationForm(Model model){

        model.addAttribute("webUser", new WebUser());
        return "registration-form";


    }

    @GetMapping("/login/showLoginForm")
    public String showLoginForm(){
        return "login-form";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied.html";
    }

    @PostMapping("/register/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") WebUser theWebUser,
            BindingResult theBindingResult,
            Model theModel) {

        String userName = theWebUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()){

            return "registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
            theModel.addAttribute("registrationError", "user name đã tồn tại.");
            return "registration-form";

        }

        // create user account and store in the database
        userService.save(theWebUser);

        logger.info("Successfully created user: " + userName);

        return "registration-confirmation";
    }
}
