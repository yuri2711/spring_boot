package ru.yuri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yuri.DAO.PeopleDAO;
import ru.yuri.model.People;
import ru.yuri.services.PeopleService;

import java.security.Principal;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService service;
    private final PeopleDAO peopleDAO;

    public PeopleController(PeopleService service, PeopleDAO peopleDAO) {
        this.service = service;
        this.peopleDAO = peopleDAO;
    }


    @GetMapping
    public String get2(Model model, Principal principal) {
        model.addAttribute("person", peopleDAO.getUserByName(principal.getName()));
        return "/lk";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int id, Model model, Principal principal) {

        model.addAttribute("person", peopleDAO.getUserByName(principal.getName()));
        return "/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/people";
    }
}
