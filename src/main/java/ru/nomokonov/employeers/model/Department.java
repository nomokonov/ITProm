package ru.nomokonov.employeers.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;


    @ManyToOne()
    private Department parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<Department> children = new HashSet<Department>();

    @Column(name = "name")
    private String name;

    @Column(name = "notice")
    private String notice;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Department() {
    }

    public Department(String name, String notice, Department parent) {
        this.name = name;
        this.notice = notice;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
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

    public Set<Department> getChildren() {
        return children;
    }

    public void setChildren(Set<Department> children) {
        this.children = children;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
