package com.example.course_project_6.service;

import com.example.course_project_6.entity.Bet;
import com.example.course_project_6.entity.User;
import com.example.course_project_6.entity.UserBet;
import com.example.course_project_6.entity.Wallet;
import com.example.course_project_6.repository.UserBetRepository;
import com.example.course_project_6.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    UserBetRepository userBetRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserBetRepository userBetRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userBetRepository = userBetRepository;
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public void registerNewUser(User newUser) {
        try {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.setWallet(new Wallet(0));
            userRepository.save(newUser);
            log.info("Registered new user" + newUser.toString());
        } catch (Exception e) {
            log.info("cannot save");
        }
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveUserChanges(User user, User changedUser) {
        user.setUsername(changedUser.getUsername());
        user.setPassword(passwordEncoder.encode(changedUser.getPassword()));
        user.setName(changedUser.getName());
        user.setLastname(changedUser.getLastname());
        user.setPhoneNumber(changedUser.getPhoneNumber());
        user.setEmail(changedUser.getEmail());

        saveUser(user);
    }

    public double getBalance(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getWallet().getBalance();
    }


    public void addBalance(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        double newBalance = user.getWallet().getBalance() + amount;
        user.getWallet().setBalance(newBalance);
        userRepository.save(user);
    }

    public void withdrawBalance(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        double newBalance = user.getWallet().getBalance() - amount;
        if (newBalance < 0) {
            throw new RuntimeException("Not enough balance");
        }
        user.getWallet().setBalance(newBalance);
        userRepository.save(user);
    }

    public void saveNewUserBet(User user, Bet bet, double bet_sum) {
        UserBet userBet = new UserBet(user, bet, bet_sum);
        List<UserBet> userBetList = user.getUserBets();
        userBetRepository.save(userBet);
        userBetList.add(userBet);
        user.setUserBets(userBetList);
        userRepository.save(user);
    }

    public List<UserBet> getActiveUserBets(Long userId) {
        return userBetRepository.findAllByUser_IdAndBet_Status(userId, true);
    }


    public List<UserBet> getInactiveUserBets(Long userId) {
        return userBetRepository.findAllByUser_IdAndBet_Status(userId, false);
    }
}
