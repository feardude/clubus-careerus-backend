package org.example.clubuscareerusbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String email;

    private String password;

    private String login;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
