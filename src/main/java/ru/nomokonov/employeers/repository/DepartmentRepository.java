package ru.nomokonov.employeers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nomokonov.employeers.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
