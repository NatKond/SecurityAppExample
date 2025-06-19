package org.telran.ticketApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String title;

    @Column
    private Double price;

    @ManyToOne//(mappedBy = "" )
    @JoinColumn(name = "local_user_id")
    @JsonBackReference
    @ToString.Exclude
    private LocalUser localUser;
}
