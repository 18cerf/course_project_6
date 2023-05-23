package com.example.course_project_6.controller;

import com.example.course_project_6.entity.User;
import com.example.course_project_6.repository.UserRepository;
import com.example.course_project_6.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * Контроллер, предназначенный для регистрации новых пользователей
 */
@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {


    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    /*
     * Внедряем бины userRepository и passwordEncoder
     */
    public RegistrationController(

            PasswordEncoder passwordEncoder,
            UserService userService) {
        this.passwordEncoder = passwordEncoder;

        this.userService = userService;
    }

    /*
     * ModelAttribute "registerForm" предназначен для заполнения его данными о User
     * + валидации входящей информации о новом User.
     *
     */
    @ModelAttribute(name = "registerForm")
    public User registrationForm() {
        return new User();
    }

    /*
     * Возвращает представление для регистрации.
     */
    @GetMapping
    public String registerForm() {
        return "registration";
    }


    /*
     *
     * В случае успешной валидации данных происходит редирект на страницу входа
     *
     * Если данные валидацию не прошли, возвращается на страницу регистрации.
     *
     */
    @PostMapping
    public String processRegistration(@ModelAttribute("registerForm") @Valid User newUser,
                                      Errors errors,
                                      Model model,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Неудачная попытка валидации с данными: {}", newUser.toString());
            model.addAttribute("message", "Ошибка валидации данных, проверьте правильность вводимых полей");
            return "registration";
        }

        if (!userService.existsUserByUsername(newUser.getUsername())) {
           userService.registerNewUser(newUser);
        } else {
            model.addAttribute("error_message", "Пользователь с таким логином уже существует");
            return "registration";
        }


        return "redirect:/login";

    }
}
