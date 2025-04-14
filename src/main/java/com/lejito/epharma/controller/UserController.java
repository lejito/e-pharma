package com.lejito.epharma.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lejito.epharma.dto.LoginDTO;
import com.lejito.epharma.dto.ResponseDTO;
import com.lejito.epharma.security.AuthenticationHandler;
import com.lejito.epharma.security.SkipHandler;
import com.lejito.epharma.service.AuthService;
import com.lejito.epharma.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/")
    public ResponseDTO getUsers() {
        var users = userService.getUsers();
        return new ResponseDTO(true, "Users fetched successfully", users, HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseDTO getMethodName(@PathVariable("idUser") int idUser) {
        var user = userService.getUser(idUser);
        return new ResponseDTO(true, "User fetched successfully", user, HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseDTO getPatients() {
        var patients = userService.getPatients();
        return new ResponseDTO(true, "Patients fetched successfully", patients, HttpStatus.OK);
    }

    @PostMapping("/login")
    @SkipHandler(AuthenticationHandler.class)
    public ResponseDTO login(@Valid @RequestBody LoginDTO body) {
        var user = userService.login(body.getEmail(), body.getPassword());
        var token = authService.generateToken(String.valueOf(user.getId()));
        return new ResponseDTO(true, "Login successful and token generated", token, HttpStatus.OK);
    }
}
