package ru.nomokonov.employeers.service;

import ru.nomokonov.employeers.model.Profession;

import java.util.List;

public interface ProfessionService {
    List<Profession> findAll();
    void add(String name, String notice);

    boolean delete(Long professionId);

    boolean update(Long professionId, String professionName, String notice);
}


