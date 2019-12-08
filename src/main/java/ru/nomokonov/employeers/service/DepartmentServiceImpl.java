package ru.nomokonov.employeers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nomokonov.employeers.model.Department;
import ru.nomokonov.employeers.repository.DepartmentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class DepartmentServiceImpl implements  DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void add(String departmentName, String notice, Long departmentParent) {
        Department parent = null;
        if (departmentParent != null) {
            parent = departmentRepository.getOne(departmentParent);
        }
        Department newDepartment = new Department(departmentName, notice, parent);
        departmentRepository.save(newDepartment);
    }

    @Override
    public boolean update(Long departmentId, String departmentName, String departmentNotice, Long departmentParent) {
        Department parent = null;
        if (departmentParent != null) {
            parent = departmentRepository.getOne(departmentParent);
        }
        Department departmentFromDB = departmentRepository.getOne(departmentId);
        if ( departmentFromDB != null ){
            departmentFromDB.setName(departmentName);
            departmentFromDB.setNotice(departmentNotice);
            departmentFromDB.setParent(parent);
            departmentRepository.saveAndFlush(departmentFromDB);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long departmentId) {
        Department departmentFromDB = departmentRepository.getOne(departmentId);
        if(departmentFromDB != null) {
            Set<Department> childrens = departmentFromDB.getChildren();
            if (!childrens.isEmpty()){
                for (Department child: childrens) {
                    child.setParent(null);
                    departmentRepository.save(child);
                }
            }
            departmentRepository.delete(departmentFromDB);
            return true;
        }
        return false;
    }
}
