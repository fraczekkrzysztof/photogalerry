package com.fraczekkrzysztof.photogallery.controller;

import com.fraczekkrzysztof.photogallery.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdminPage(Model theModel){
        theModel.addAttribute("newuser", new UserDto());
        return "admin-panel";
    }
}
