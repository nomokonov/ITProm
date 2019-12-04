package ru.nomokonov.employeers.model;

import javax.persistence.*;

@Entity
@Table(name="profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id")
    private Long id;
}
