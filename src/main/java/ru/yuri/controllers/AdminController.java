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

    @GetMapping()
    public String getAdminPage(Model model) {
        return "/admin";
    }
}
