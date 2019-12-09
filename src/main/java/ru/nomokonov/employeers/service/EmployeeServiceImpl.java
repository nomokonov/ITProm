package ru.nomokonov.employeers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nomokonov.employeers.model.Department;
import ru.nomokonov.employeers.model.Employee;
import ru.nomokonov.employeers.model.Profession;
import ru.nomokonov.employeers.repository.DepartmentRepository;
import ru.nomokonov.employeers.repository.EmployeeRepository;
import ru.nomokonov.employeers.repository.ProfessionRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements  EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProfessionRepository professionRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ProfessionRepository professionRepository) {
        this.employeeRepository = employeeRepository;

        this.departmentRepository = departmentRepository;
        this.professionRepository = professionRepository;
    }

    @Override
    public List<Employee> findAll(){
        return  employeeRepository.findAll();
    }

    @Override
    public void add(String employeeName, String employeeNotice, Long employeeDepartment, Long employeeProfession) {
        Department department = null;
        Profession profession = null;
        if ( employeeDepartment != null) {
            department = departmentRepository.getOne(employeeDepartment);
        }
        if ( employeeProfession != null ) {
            profession = professionRepository.getOne(employeeProfession);
        }
        Employee employee = new Employee(employeeName, employeeNotice, department, profession);
        employeeRepository.save(employee);
    }

    @Override
    public boolean delete(Long employeeId) {
        Employee employeeFromDB = employeeRepository.getOne(employeeId);
        if (employeeFromDB != null) {
            employeeRepository.delete(employeeFromDB);
            return true;
        }
        return false;
    }

    @Override
    public void update(Long employeeId, String employeeName, String employeeNotice, Long employeeDepartment, Long employeeProfession) {
        Employee employeeFromDb = employeeRepository.getOne(employeeId);
        Department departmentFromDb = null;
        Profession professionFromDb = null;
        if  ( employeeFromDb != null ){
            if (employeeDepartment != null) {
                departmentFromDb = departmentRepository.getOne(employeeDepartment);
            }
            if ( employeeProfession != null ){
                professionFromDb =professionRepository.getOne(employeeProfession);
            }
            employeeFromDb.setFio(employeeName);
            employeeFromDb.setNotice(employeeNotice);
            employeeFromDb.setDepartment(departmentFromDb);
            employeeFromDb.setProfession(professionFromDb);
            employeeRepository.save(employeeFromDb);
        }
    }
}
