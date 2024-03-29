package ru.nomokonov.employeers.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "notice")
    private String notice;

    @OneToMany(mappedBy = "profession", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Profession() {
    }

    public Profession(String name, String notice) {
        this.name = name;
        this.notice = notice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
