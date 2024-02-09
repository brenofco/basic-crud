package com.brenofco.backend.presenter.rest;

import com.brenofco.backend.domain.UserDTO;
import com.brenofco.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

// logging:
    // log4j -> logstash -> elastic search -> kibana

    @GetMapping("/{cpf}")
    public UserDTO getUser(@PathVariable String cpf) {
        // http://localhost:8080/user?cpf=38397752803
        // http://localhost:8080/user/38397752803
        return userService.getUserByCpf(cpf);
    }

    @PostMapping
    public String addUser(@RequestBody UserDTO dto) {
        return userService.postUser(dto);
    }

    @PutMapping("/{cpf}")
    public String editUser(@PathVariable String cpf, @RequestBody UserDTO dto) {
        return userService.editUser(cpf, dto);
    }

    @DeleteMapping("/{cpf}")
    public String removeUser(@PathVariable String cpf) {
        return userService.deleteUser(cpf);
    }

}
