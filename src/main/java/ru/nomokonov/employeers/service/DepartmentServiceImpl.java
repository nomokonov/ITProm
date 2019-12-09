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
        Long level = 0L;
        Iterator<Department> iterator = all.iterator();
        while (iterator.hasNext()) {
            Department department = iterator.next();
            if (department.getParent() == null) {
                root_department.add(department);
                iterator.remove();
            }
        }
        iterator = all.iterator();
        while (iterator.hasNext()) {
            Department department = iterator.next();
             int count = 0;
            for (Department depart: root_department) {
                count++;
//            findChilds(all, root_department, department);
                if ( count == root_department.size() ){
                    root_department.add(department);
                    all.remove(department);
                    break;
                }
                if ( department.getId() == depart.getParent().getId() ){
                    root_department.add(count -1,depart);
                    all.remove(depart);
                }
            }
        }

        return root_department;
    }

//    private void findChilds(List<Department> all, List<Department> root_department, Department department) {
//        for (Department depart : all) {
//            if (department.getId() == depart.getParent().getId()) {
//                root_department.add(root_department.indexOf(department), depart);
//                all.remove(depart);
//                findChilds(all, root_department, depart);
//            }
//        }
//    }

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
