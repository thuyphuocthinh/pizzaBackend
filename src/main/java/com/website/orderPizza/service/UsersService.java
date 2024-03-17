package com.website.orderPizza.service;

import com.website.orderPizza.DTO.UsersDTO;
import com.website.orderPizza.entity.Roles;
import com.website.orderPizza.entity.Users;
import com.website.orderPizza.payload.request.UpdateUserRequest;
import com.website.orderPizza.repository.UsersRepository;
import com.website.orderPizza.service.imp.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UsersService implements UsersServiceImp {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<UsersDTO> getListOfUsers() {
        List<Users> usersList = usersRepository.findAll();
        List<UsersDTO> usersDTOList = new ArrayList<>();
        for(Users users : usersList) {
            UsersDTO usersDTO = new UsersDTO();
            Roles roles = new Roles();
            roles.setId(users.getRoles().getId());
            roles.setRoleName(users.getRoles().getRoleName());
            usersDTO.setId(users.getId());
            usersDTO.setAddress(users.getAddress());
            usersDTO.setEmail(users.getEmail());
            usersDTO.setUsername(users.getUsername());
            usersDTO.setPhoneNumber(users.getPhoneNumber());
            usersDTO.setCreatedDate(users.getCreatedDate());
            usersDTO.setRoles(roles);
            usersDTOList.add(usersDTO);
        }
        return usersDTOList;
    }

    @Override
    public boolean updateProfile(UpdateUserRequest updateUserRequest) {
        Users users = new Users();
        users.setId(updateUserRequest.getUserId());
        users.setUsername(updateUserRequest.getUsername());
        users.setAddress(updateUserRequest.getAddress());
        users.setPhoneNumber(updateUserRequest.getPhoneNumber());
        users.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        try {
            usersRepository.save(users);
            return true;
        } catch (Exception e) {
            System.out.println("Error updated profile: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        Users users = usersRepository.findByEmail(email);
        if (users != null) {
            users.setResetPasswordToken(token);
            usersRepository.save(users);
        } else {
            throw new UsernameNotFoundException("Could not find any user with this email " + email);
        }
    }
    // get to know which user needs to reset password
    @Override
    public Users getUserByResetPasswordToken(String token) {
        Users users = usersRepository.findByResetPasswordToken(token);
        return users;
    }

    @Override
    public void updatePassword(Users users, String newPassword) {
        String encodeNewPassword = passwordEncoder.encode(newPassword);
        users.setPassword(encodeNewPassword);
        users.setResetPasswordToken(null);
        usersRepository.save(users);
    }
}
