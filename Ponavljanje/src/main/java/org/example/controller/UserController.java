package org.example.controller;
import lombok.extern.flogger.Flogger;
import org.apache.coyote.Response;
import org.example.model.User;
import org.example.model.dto.LoginDTO;
import org.example.model.dto.UserDTO;
import org.example.security.TokenUtil;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "User Controller", description = "CRUD operacije za korisnike") //dodaje ime grupe i dodaje opis u swaggeru
@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable int id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/update-user/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List <User> users = userService.findAll();

        //convert users to DTOs
        List<UserDTO> dto = new ArrayList<>();
        for (User user : users){
            dto.add(new UserDTO().convert(user)); //nad UserDTO pozivamo njegovu metodu
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.findUserByEmail(loginDTO.getEmail());

        if(user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = tokenUtil.generateToken(user.getEmail(), user.getUserType().toString());

        LoginDTO responseDTO = new LoginDTO();
        responseDTO.setToken(token);

        return ResponseEntity.ok(responseDTO);
    }

}
