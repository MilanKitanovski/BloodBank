package org.example.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.flogger.Flogger;
import org.apache.coyote.Response;
import org.example.model.User;
import org.example.model.dto.*;
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
@SecurityRequirement(name = "BearerAuth") //za dodavanje dugmeta auth u swageru
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

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO registerDTO) {
        User user = userService.register(registerDTO);

        return ResponseEntity.ok(registerDTO);
    }

    @PatchMapping(path = "/update-user")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> update(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }
    @PutMapping(path = "/change-password", consumes = "application/json")
    public ResponseEntity<ChangePasswordDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        if(!userService.updatesUerPassword(changePasswordDTO))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new ChangePasswordDTO(changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword()), HttpStatus.OK);
    }

}
