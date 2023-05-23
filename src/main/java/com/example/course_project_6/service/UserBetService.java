package com.example.course_project_6.service;

import com.example.course_project_6.entity.Bet;
import com.example.course_project_6.entity.User;
import com.example.course_project_6.entity.UserBet;
import com.example.course_project_6.repository.UserBetRepository;
import com.example.course_project_6.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserBetService {

    UserBetRepository userBetRepository;
    UserRepository userRepository;

    public UserBetService(UserBetRepository userBetRepository, UserRepository userRepository) {
        this.userBetRepository = userBetRepository;
        this.userRepository = userRepository;
    }


}
