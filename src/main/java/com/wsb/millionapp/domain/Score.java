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
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "result")
    private int result;
    @Column(name = "users_id")
    private Long userId;

    public Score(LocalDate date, int result, Long userId) {
        this.date = date;
        this.result = result;
        this.userId = userId;
    }
}
