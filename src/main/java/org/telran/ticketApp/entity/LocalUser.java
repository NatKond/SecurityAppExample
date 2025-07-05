package org.telran.ticketApp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.telran.ticketApp.enums.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "local_users")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@AllArgsConstructor
public class LocalUser {

    @Id                                                 // Автогенерация значения поля: IDENTITY - нумерация только по этой таблице,
    @GeneratedValue(strategy = GenerationType.IDENTITY) //                              AUTO - сквозная нумерация по всем таблицам
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String surname;

    // use as login
    private String email;

    private String password;

    private String postAddress; // post_address// postAddress

    @Enumerated(EnumType.STRING) // хранит енам как строку, если ее не будет, то енам будет храниться как число
    private Role role = Role.ROLE_USER;

    @OneToMany(mappedBy = "localUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    //@JoinColumn(name = "local_user_id")
    @JsonManagedReference
    @ToString.Exclude
    private Set<Ticket> tickets = new HashSet<>();
}
