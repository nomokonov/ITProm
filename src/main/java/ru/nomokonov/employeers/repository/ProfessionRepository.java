package ru.nomokonov.employeers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nomokonov.employeers.model.Profession;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
