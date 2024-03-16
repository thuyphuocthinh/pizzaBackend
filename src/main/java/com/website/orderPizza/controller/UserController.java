package com.website.orderPizza.controller;

import com.website.orderPizza.DTO.UsersDTO;
import com.website.orderPizza.payload.ResponseData;
import com.website.orderPizza.payload.request.UpdateUserRequest;
import com.website.orderPizza.service.imp.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UsersServiceImp usersServiceImp;
    @GetMapping("/get")
    public ResponseEntity<?> getListOfUsers() {
        ResponseData responseData = new ResponseData();
        List<UsersDTO> usersDTOList = usersServiceImp.getListOfUsers();
        if (!usersDTOList.isEmpty()) {
            responseData.setData(usersDTOList);
            responseData.setStatusCode(200);
            responseData.setDescription("Get successfully");
        } else {
            responseData.setStatusCode(204);
            responseData.setDescription("No content");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateUserRequest updateUserRequest) {
        ResponseData responseData = new ResponseData();
        if (usersServiceImp.updateProfile(updateUserRequest)) {
            responseData.setStatusCode(200);
            responseData.setDescription("Updated profile successfully");
        } else {
            responseData.setStatusCode(200);
            responseData.setDescription("Updated profile failed");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

//    @PostMapping("/user/resetPassword")
//    public GenericResponse resetPassword(HttpServletRequest request,
//                                         @RequestParam("email") String userEmail) {
//        User user = userService.findUserByEmail(userEmail);
//        if (user == null) {
//            throw new UserNotFoundException();
//        }
//        String token = UUID.randomUUID().toString();
//        userService.createPasswordResetTokenForUser(user, token);
//        mailSender.send(constructResetTokenEmail(getAppUrl(request),
//                request.getLocale(), token, user));
//        return new GenericResponse(
//                messages.getMessage("message.resetPasswordEmail", null,
//                        request.getLocale()));
//    }
}
