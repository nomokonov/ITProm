package ru.nomokonov.employeers.service;

import ru.nomokonov.employeers.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    void add(String departmentName, String notice, Long departmentParent);

    boolean update(Long departmentId, String departmentName, String departmentNotice, Long departmentParent);

    boolean delete(Long departmentId);
}
