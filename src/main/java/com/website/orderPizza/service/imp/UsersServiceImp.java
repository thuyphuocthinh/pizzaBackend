package com.website.orderPizza.service.imp;

import com.website.orderPizza.DTO.UsersDTO;
import com.website.orderPizza.payload.request.UpdateUserRequest;

import java.util.List;

public interface UsersServiceImp {
    List<UsersDTO> getListOfUsers();
    boolean updateProfile(UpdateUserRequest updateUserRequest);
}
