package com.fraczekkrzysztof.photogallery.controller;

import com.fraczekkrzysztof.photogallery.dao.PhotoRepository;
import com.fraczekkrzysztof.photogallery.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DemoController {


    @GetMapping("/hello")
    public String sayHello(Model theModel){
        theModel.addAttribute("theDate", LocalDateTime.now());
        return "helloworld";
    }
}
