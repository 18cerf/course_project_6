package com.example.course_project_6.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "bet")
@Data
public class Bet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String header;
    String shortDescription;
    String description;
    Boolean status;
    Boolean winResult;

    public Bet() {
    }

    public Bet(String header, String shortDescription, String description, Boolean status) {
        this.header = header;
        this.shortDescription = shortDescription;
        this.description = description;
        this.status = status;
        this.winResult = null;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", winResult=" + winResult +
                '}';
    }
}
