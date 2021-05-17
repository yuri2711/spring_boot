package ru.yuri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yuri.model.People;
import ru.yuri.model.Role;
import ru.yuri.services.PeopleService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PeopleService service;

    public AdminController(PeopleService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAdminPage() {
        return "/admin";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("people", service.index());
        return "/index";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        List<Role> list = service.getAllRoles();
        model.addAttribute("person", new People());
        model.addAttribute("list", list);
        return "news";
    }
    private static final String rAdmin = "ROLE_ADMIN";
    private static final String rUser = "ROLE_USER";

    @PostMapping()
    public String create(@ModelAttribute("person") People people) {

        getSetListRole(people);
        service.save(people);
        return "redirect:/admin/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", service.get(id));
        model.addAttribute("list", service.getAllRoles());
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") People people) {
        getSetListRole(people);
        service.update(people);
        return "redirect:/admin/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("person") People people) {
        getSetListRole(people);
        service.delete((int) people.getId());
        return "redirect:/admin/index";
    }
    private Set<Role> getSetListRole(People people) {
        Set<Role> roleSet = new HashSet<>();

        if(people.getRoles().contains(rAdmin)){
            Role role = service.getSingleRole(rAdmin);
            roleSet.add(role);
        }

        if(people.getRoles().contains(rUser)) {
            Role role = service.getSingleRole(rUser);
            roleSet.add(role);
        }
        people.setRoles(roleSet);
        return roleSet;
    }
}
