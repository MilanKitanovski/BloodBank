package org.example.service;

import org.example.config.SecurityUtils;
import org.example.model.User;
import org.example.model.dto.ChangePasswordDTO;
import org.example.model.dto.RegisterDTO;
import org.example.model.dto.UserDTO;
import org.example.model.enums.UserType;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        User user = getCurrentUser();
        if (dto.getCountry() != null) user.setCountry(dto.getCountry());
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getSurname() != null) user.setSurname(dto.getSurname());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getCity() != null) user.setCity(dto.getCity());
        if (dto.getMobile() != null) user.setMobile(dto.getMobile());
        if (dto.getUniquePersonalId() != null) user.setUniquePersonalId(dto.getUniquePersonalId());
        if (dto.getGender() != null) user.setGender(dto.getGender());
        if (dto.getInformation() != null) user.setInformation(dto.getInformation());
        if (dto.getProfession() != null) user.setProfession(dto.getProfession());

        return userRepository.save(user);
    }

    public User getCurrentUser(){
        String email = SecurityUtils.getCurrentUserLogin().get();
        return userRepository.getByEmail(email);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User register(RegisterDTO registerDTO){
        User user = new User();
        user.setCountry(registerDTO.getCountry());
        user.setName(registerDTO.getName());
        user.setSurname(registerDTO.getSurname());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setCity(registerDTO.getCity());
        user.setMobile(registerDTO.getMobile());
        user.setUniquePersonalId(registerDTO.getUniquePersonalId());
        user.setGender(registerDTO.getGender());
        user.setAddress(registerDTO.getAddress());
        //user.setCentre(dto.getCentreId()));
        user.setUserType(UserType.RegisteredUser);
        user.setInformation(registerDTO.getInformation());
        user.setProfession(registerDTO.getProfession());

        userRepository.save(user);
        return user;
    }

    public boolean updatesUerPassword(ChangePasswordDTO changePasswordDTO) {
        User user = getCurrentUser();

        if(changePasswordDTO.getOldPassword() == null || changePasswordDTO.getNewPassword() == null){
            return false;
        }

        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        if(!bcpe.matches(changePasswordDTO.getOldPassword(), user.getPassword())){
            return false;
        }

        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);

        return  true;
    }
}
