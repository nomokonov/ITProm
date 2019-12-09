package ru.nomokonov.employeers.controller;

import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.nomokonov.employeers.service.DepartmentService;
import ru.nomokonov.employeers.service.EmployeeService;
import ru.nomokonov.employeers.service.ProfessionService;

@Controller
@RequestMapping(value = {"/employee","/"})
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private ProfessionService professionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, ProfessionService professionService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.professionService = professionService;
    }

    @Autowired

    @GetMapping(value = "/")
    public ModelAndView getAllEmployeers() {
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("employee");
        modelAndView.addObject("employeers_list",employeeService.findAll());
        modelAndView.addObject("departments_list",departmentService.findAll());
        modelAndView.addObject("professions_list",professionService.findAll());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addEmployee(@RequestParam String employeeName,
                                    @RequestParam String employeeNotice,
                                    @RequestParam Long employeeDepartment,
                                    @RequestParam Long employeeProfession){
        employeeService.add(employeeName, employeeNotice, employeeDepartment, employeeProfession);
        return new ModelAndView("redirect:/employee/");
    }

    @PostMapping("/delete")
    public ResponseEntity<Long> deleteEmployee( @RequestParam Long employeeId)
    {
       if ( employeeService.delete(employeeId)){
           return new ResponseEntity<>(employeeId, HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/edit")
    public ModelAndView editEmployee(@RequestParam Long employeeId,
                                    @RequestParam String employeeName,
                                     @RequestParam String employeeNotice,
                                     @RequestParam Long employeeDepartment,
                                     @RequestParam Long employeeProfession){
        employeeService.update(employeeId, employeeName, employeeNotice, employeeDepartment, employeeProfession);
        return new ModelAndView("redirect:/employee/");
    }
}
