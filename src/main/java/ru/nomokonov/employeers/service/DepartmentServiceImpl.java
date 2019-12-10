package ru.nomokonov.employeers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nomokonov.employeers.model.Department;
import ru.nomokonov.employeers.repository.DepartmentRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return getTree(departmentRepository.findAll());
    }

    private List<Department> getTree(List<Department> all) {
        List<Department> root_department = new LinkedList<>();
        Iterator<Department> iterator = all.iterator();
        while (iterator.hasNext()) {
            Department department = iterator.next();
            if (department.getParent() == null) {
                root_department.add(department);
                iterator.remove();
            }
        }

        while ( all.size() > 0 ){
            Iterator<Department> all_iter = all.iterator();
            while ( all_iter.hasNext()){
                Department curr_from_all = all_iter.next();
                Iterator<Department> root_iter = root_department.iterator();
                while( root_iter.hasNext() ){
                    Department curr_from_root = root_iter.next();
                    if ( curr_from_all.getParent().getId() == curr_from_root.getId() ){
                        root_department.add(root_department.indexOf(curr_from_root)+1,curr_from_all);
                        all_iter.remove();
                        break;
                    }
                }
            }
        }
        return root_department;
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
        if (departmentFromDB != null) {
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
        if (departmentFromDB != null) {
            Set<Department> childrens = departmentFromDB.getChildren();
            if (!childrens.isEmpty()) {
                for (Department child : childrens) {
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
