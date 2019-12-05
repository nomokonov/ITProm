package ru.nomokonov.employeers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.nomokonov.employeers.repository.EmployeeRepository;

@Controller
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeRepository  employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired

    @GetMapping(value = "/")
    public ModelAndView getAllEmployeers() {
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("employee");
        modelAndView.addObject("employeers_list",employeeRepository.findAll());
        return modelAndView;
    }
}
