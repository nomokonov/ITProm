package ru.nomokonov.employeers.service;

import ru.nomokonov.employeers.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    void add(String employeeName, String employeeNotice, Long employeeDepartment, Long employeeProfession);

    boolean delete(Long employeeId);

    void update(Long employeeId, String employeeName, String employeeNotice, Long employeeDepartment, Long employeeProfession);
}
