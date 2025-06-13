package org.telran.ticketApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "local_users")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
public class LocalUser {

    @Id                                                 // Автогенерация значения поля: IDENTITY - нумерация только по этой таблице,
    @GeneratedValue(strategy = GenerationType.IDENTITY) //                              AUTO - сквозная нумерация по всем таблицам
    @EqualsAndHashCode.Include
    @ToString.Include
    private  Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String postAddress; // post_address// postAddress
}
