package com.example.course_project_6.controller;

import com.example.course_project_6.entity.User;
import com.example.course_project_6.repository.UserRepository;
import com.example.course_project_6.service.UserImageService;
import com.example.course_project_6.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
@Slf4j
public class HomeController {

    private UserImageService userImageService;
    private UserService userService;

    public HomeController(UserImageService userImageService, UserService userService) {
        this.userImageService = userImageService;
        this.userService = userService;
    }

    @GetMapping()
    public String getHomePage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", userService.findUserById(user.getId()));
        model.addAttribute("userImage", userImageService.getUserImage(user.getId()));
        return "home";
    }

    @PostMapping("/image")
    public String uploadUserImage(@RequestParam("file") MultipartFile image,
                                  @AuthenticationPrincipal User user) {
        if (!image.isEmpty()) {
            userImageService.saveUserImage(image, user);
        }
        return "redirect:/home";
    }

    @GetMapping("/edit")
    public String editUser(Model model,
                           @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "edit-main-user";
    }

    @PostMapping("/edit")
    public String saveMainEdit(@Valid User editedUser, BindingResult bindingResult,
                               @AuthenticationPrincipal User user, Model model) {


        if (userService.existsUserByUsername(editedUser.getUsername())) {
            if (!editedUser.getUsername().equals(user.getUsername())) {
                model.addAttribute("error_message", "Пользователь с таким логином уже существует");
                return "edit-main-user";
            }
        }

        if (bindingResult.hasErrors()) {
            return "edit-main-user";
        } else {
            userService.saveUserChanges(user, editedUser);
            return "redirect:/home";
        }
    }
}
