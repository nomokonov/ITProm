package ru.nomokonov.employeers.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.nomokonov.employeers.service.ProfessionService;

@Controller
@RequestMapping(path = "/profession")
public class ProfessionController {

    private ProfessionService professionService;

    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping("/")
    public ModelAndView getAllProfession() {
        ModelAndView modelAndview = new ModelAndView("profession");
        modelAndview.addObject("profession_list", professionService.findAll());
        return modelAndview;
    }

    @PostMapping("/add")
    public ModelAndView addProfession(@RequestParam String professionName,
                                      @RequestParam String notice) {
        professionService.add(professionName, notice);
        return new ModelAndView("redirect:/profession/");
    }

    @PostMapping("/delete")
    public ResponseEntity<Long> deleteProfession(@RequestParam Long professionId) {
        if (professionService.delete(professionId)) {
            return new ResponseEntity<>(professionId, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/edit")
    public ModelAndView deleteProfession(@RequestParam Long professionId,
                                                 @RequestParam String professionName,
                                                 @RequestParam String notice
    ) {
        if (professionService.update(professionId,professionName,notice)) {
            return new ModelAndView("redirect:/profession/");
        } else {
            return new ModelAndView("redirect:/profession/");
        }

    }
}
