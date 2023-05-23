package com.example.course_project_6.service;

import com.example.course_project_6.entity.Bet;
import com.example.course_project_6.entity.User;
import com.example.course_project_6.entity.UserBet;
import com.example.course_project_6.repository.BetRepository;
import com.example.course_project_6.repository.UserBetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class BetService {
    BetRepository betRepository;
    UserBetRepository userBetRepository;
    UserService userService;

    public BetService(BetRepository betRepository, UserBetRepository userBetRepository, UserService userService) {
        this.betRepository = betRepository;
        this.userBetRepository = userBetRepository;
        this.userService = userService;
    }

    public List<Bet> findActiveBets() {
        List<Bet> activeBets = betRepository.findAllByStatus(true);
        return activeBets;
    }

    public Bet findBetById(Long id) {
        return betRepository.findById(id).orElse(null);
    }

    public void saveBet(Bet bet) {
        betRepository.save(bet);
    }

    public void deleteBet(Bet bet) {
        betRepository.delete(bet);
    }

    public void deleteInactiveBets() {
        betRepository.deleteAll(betRepository.findAllByStatus(false));
    }

    public void generateBetResults() {
        List<Bet> activeBets = findActiveBets();
        Random random = new Random();
        for (Bet bet : activeBets) {
            boolean result = random.nextBoolean();
            bet.setWinResult(result);
            bet.setStatus(false);
            betRepository.save(bet);
            getWinBetsForUsers(bet);
        }
    }

    public void getWinBetsForUsers(Bet bet) {

        for (UserBet userBet : userBetRepository.findAllByBet_id(bet.getId())) {
            User user = userBet.getUser();
            if (userBet.getBet().getWinResult()) {
                userService.addBalance(user.getId(), userBet.getBetSum() * 2);
            }
        }
    }

    public void generateNewBets() {
        for (int i = 0; i <= 3; i++) {
            Bet newBet = new Bet("Событие " + betRepository.findBetWithMaxId(), "Короткое описание события " + betRepository.findBetWithMaxId(), "Полное описание события " + betRepository.findBetWithMaxId(), true);
            betRepository.save(newBet);
        }
    }
}
