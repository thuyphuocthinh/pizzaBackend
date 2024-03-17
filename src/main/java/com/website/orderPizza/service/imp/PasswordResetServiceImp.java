package com.website.orderPizza.service.imp;

import com.website.orderPizza.entity.Users;

public interface PasswordResetServiceImp {
    void createPasswordResetTokenForUser(Users users, String token);
}
