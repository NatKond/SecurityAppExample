package org.telran.ticketApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column
    private String title;

    @Column
    private Double price;
}
