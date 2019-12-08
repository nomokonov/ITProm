package ru.nomokonov.employeers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nomokonov.employeers.model.Profession;
import ru.nomokonov.employeers.repository.ProfessionRepository;

import java.util.List;

@Service
public class ProfessionServiceImpl  implements  ProfessionService{

    private ProfessionRepository professionRepository;

    @Autowired
    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }


    @Override
    public List<Profession> findAll() {
        return professionRepository.findAll();
    }

    @Override
    public void add(String name, String notice) {
        Profession profession = new Profession(name, notice);
        professionRepository.saveAndFlush(profession);

    }

    @Override
    public boolean delete(Long professionId) {
        Profession professionFromDB = professionRepository.getOne(professionId);
        if (professionFromDB != null){
            professionRepository.delete(professionFromDB);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean update(Long professionId, String professionName, String notice) {
        Profession professionDromDB = professionRepository.getOne(professionId);
        if (professionDromDB != null){
            professionDromDB.setName(professionName);
            professionDromDB.setNotice(notice);
            professionRepository.saveAndFlush(professionDromDB);
            return true;
        }
            return false;
    }
}
