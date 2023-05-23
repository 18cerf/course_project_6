package com.example.course_project_6.repository;

import com.example.course_project_6.entity.Bet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetRepository extends CrudRepository<Bet, Long> {
    public List<Bet> findAllByStatus(Boolean status);
    @Query("SELECT e.id FROM bet e WHERE e.id = (SELECT MAX(e2.id) FROM bet e2)")
    public String findBetWithMaxId();
}
