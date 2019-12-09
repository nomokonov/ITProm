package ru.nomokonov.employeers.controller;

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

@Controller
@RequestMapping(path = "/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public ModelAndView getDepatment() {
        ModelAndView modelAndView = new ModelAndView("department");
        modelAndView.addObject("department_list", departmentService.findAll());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addDepartment(@RequestParam String departmentName,
                                      @RequestParam Long departmentParent,
                                      @RequestParam String departmentNotice) {
        departmentService.add(departmentName, departmentNotice, departmentParent);
        return new ModelAndView("redirect:/department/");
    }

    @PostMapping("/edit")
    public ModelAndView editDeparment(@RequestParam Long departmentId,
                                      @RequestParam String departmentName,
                                      @RequestParam Long departmentParent,
                                      @RequestParam String departmentNotice) {
        departmentService.update(departmentId, departmentName, departmentNotice, departmentParent);
        return new ModelAndView("redirect:/department/");
    }

    @PostMapping("/delete")
    public ResponseEntity<Long> deleteDepartment(@RequestParam Long departmentId){
        if (departmentService.delete(departmentId)){
            return new ResponseEntity<>(departmentId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
