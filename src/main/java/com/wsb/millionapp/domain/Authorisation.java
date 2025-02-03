package com.wsb.millionapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "authorisation")
public class Authorisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorisationId;
    @Column(name = "password")
    private String password;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name="users_id")
    private Long usersId;
}
