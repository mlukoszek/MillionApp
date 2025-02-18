package com.wsb.millionapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "result")
    private int result;
    @Column(name = "users_id")
    private Long userId;

    public Result(LocalDate now, int difficulty, Long userId) {
        this.date = now;
        this.result = difficulty;
        this.userId = userId;
    }
}
