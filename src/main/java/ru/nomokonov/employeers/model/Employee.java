package ru.nomokonov.employeers.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "epmloyee_id")
    private Long id;

    @Column(name = "fio")
    private String fio;

    @JoinColumn(name = "department_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    @JoinColumn(name = "profession_id")
    @ManyToOne( cascade = CascadeType.ALL)
    private Profession profession;

    @Column(name = "notice")
    private String notice;

    public Employee() {
    }

    public Employee(String employeeName, String employeeNotice, Department department, Profession profession) {
        this.fio =  employeeName;
        this.notice = employeeNotice;
        this.department = department;
        this.profession = profession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }


}
