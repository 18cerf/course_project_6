package com.example.course_project_6.repository;

import com.example.course_project_6.entity.Bet;
import com.example.course_project_6.entity.UserBet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserBetRepository extends CrudRepository<UserBet, Long> {
    public List<UserBet> findAllByUser_IdAndBet_Status(Long id, Boolean status);

    public List<UserBet> findAllByBet_id(Long id);
}
