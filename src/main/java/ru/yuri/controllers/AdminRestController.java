package ru.yuri.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yuri.model.People;
import ru.yuri.model.Role;
import ru.yuri.services.PeopleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admins")
public class AdminRestController {

    private final PeopleService service;

    public AdminRestController(PeopleService service) {
        this.service = service;
    }
    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody People user) {
        service.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<People>> readAll() {
        List<People> list = service.index();
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<People> read(@PathVariable int id) {
        People user = service.get(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> list = service.getAllRoles();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<People> update(@PathVariable int id, @RequestBody People user) {
         service.update(user);
        return true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<People> delete(@PathVariable int id) {
        boolean bool = service.delete(id);
        return bool
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
