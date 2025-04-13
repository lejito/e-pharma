package com.lejito.epharma.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lejito.epharma.dto.LoginDTO;
import com.lejito.epharma.dto.ResponseDTO;
import com.lejito.epharma.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseDTO getUsers() {
        var users = userService.getUsers();
        return new ResponseDTO(true, "Users fetched successfully", users, HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseDTO getPatients() {
        var patients = userService.getPatients();
        return new ResponseDTO(true, "Patients fetched successfully", patients, HttpStatus.OK);
    }

    public ResponseDTO login(@Valid @RequestBody LoginDTO body) {
        var user = userService.login(body.getEmail(), body.getPassword());
        return new ResponseDTO(true, "Login successful", user, HttpStatus.OK);
    }
}
