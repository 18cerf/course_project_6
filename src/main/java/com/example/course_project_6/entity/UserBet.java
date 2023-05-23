package com.example.course_project_6.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "userbet")
@Data
public class UserBet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "bet_id")
    private Bet bet;

    @Column(name = "bet_sum")
    private double betSum;

    public UserBet() {
    }

    public UserBet(User user, Bet bet, double betSum) {
        this.user = user;
        this.bet = bet;
        this.betSum = betSum;
    }

    @Override
    public String toString() {
        return "UserBet{" +
                "id=" + id +
                ", user=" + user +
                ", bet=" + bet +
                ", betSum=" + betSum +
                '}';
    }
}
