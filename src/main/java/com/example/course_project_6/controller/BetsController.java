package com.example.course_project_6.controller;

import com.example.course_project_6.entity.Bet;
import com.example.course_project_6.entity.User;
import com.example.course_project_6.service.BetService;
import com.example.course_project_6.service.UserBetService;
import com.example.course_project_6.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/bets")
@Slf4j
public class BetsController {

    BetService betService;
    UserService userService;
    UserBetService userBetService;

    public BetsController(BetService betService, UserBetService userBetService, UserService userService) {
        this.betService = betService;
        this.userBetService = userBetService;
        this.userService = userService;
    }

    @GetMapping
    public String getBets(@AuthenticationPrincipal User user,
                          Model model) {
        model.addAttribute("bets", betService.findActiveBets());

        model.addAttribute("userBets", userService.getActiveUserBets(user.getId()));

        model.addAttribute("inactiveUserBets", userService.getInactiveUserBets(user.getId()));
        return "bets.html";
    }

    @PostMapping("/place_bet")
    public String placeBet(@RequestParam("sum") double sum,
                           @RequestParam("id") long id,
                           @AuthenticationPrincipal User user,
                           Model model,
                           IntegrationProperties.Error error) {

        userService.withdrawBalance(user.getId(), sum);
        userService.saveNewUserBet(userService.findUserById(user.getId()), betService.findBetById(id), sum);

        return "redirect:/bets";
    }

    @GetMapping("/result")
    public String getBetResults() {
        betService.generateBetResults();
        return "redirect:/bets";

    }

    @GetMapping("/generate")
    public String generateNewBets() {
        betService.generateNewBets();
        return "redirect:/bets";
    }
}
