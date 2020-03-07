package com.fraczekkrzysztof.photogallery.controller;

import com.fraczekkrzysztof.photogallery.dao.PhotoRepository;
import com.fraczekkrzysztof.photogallery.entity.Role;
import com.fraczekkrzysztof.photogallery.entity.User;
import com.fraczekkrzysztof.photogallery.dao.UserRepository;
import com.fraczekkrzysztof.photogallery.dto.UserDto;
import com.fraczekkrzysztof.photogallery.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    private PhotoRepository photoRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private boolean afterSuccessAdd = false;

    @GetMapping("/password")
    public String retrivePassword(Model TheModel){
        System.out.println(passwordEncoder.encode("fun123"));
        return "test";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    public PhotoController (PhotoRepository photoRepository, UserRepository userRepository, PasswordEncoder passwordEncoder ){
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/list")
    public String GetPhotos(Model theModel, Authentication authentication){
        String username = authentication.getName();
        List<Photo> listOfPhotos = photoRepository.findPhotosByUserName(username);
        theModel.addAttribute("photos",listOfPhotos);
        theModel.addAttribute("newuser", new UserDto());
        checkToAddSuccessAfterAdd(theModel);
        return "list-photos";

    }

    @PostMapping("/newuser")
    public String addUser(@ModelAttribute("newuser") UserDto user){
        User userToSave = new User();
        userToSave.setUserName(user.getUserName());
        userToSave.setEnabled(1);
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role  = new Role();
        role.setUsername(user.getUserName());
        role.setAuthority("ROLE_USER");
        userToSave.setRoleList(Arrays.asList(role));
        userRepository.save(userToSave);
        afterSuccessAdd = true;
        return "redirect:/photos/list";
    }

    private void checkToAddSuccessAfterAdd(Model theModel){
        if (afterSuccessAdd){
            afterSuccessAdd = false;
            theModel.addAttribute("SuccessMessage","Succeddfully create user!");
        }
    }

}
