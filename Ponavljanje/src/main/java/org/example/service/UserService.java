package org.example.service;

import org.example.config.SecurityUtils;
import org.example.model.User;
import org.example.model.dto.UserDTO;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> findAll() { return userRepository.findAll(); }

    public User findById(int id) { return userRepository.findById(id);}

    public User update(UserDTO dto){
        User user = userRepository.findById(dto.getId());
        user.setCountry(dto.getCountry());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setMobile(dto.getMobile());
        user.setUniquePersonalId(dto.getUniquePersonalId());
        user.setGender(dto.getGender());
        //user.setCentre(dto.getCentreId()));
        user.setUserType(dto.getUserType());
        user.setInformation(dto.getInformation());
        user.setProfession(dto.getProfession());

        return userRepository.save(user);
    }

    public User getCurrentUser(){
        String email = SecurityUtils.getCurrentUserLogin().get();
        return userRepository.getByEmail(email);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

}
