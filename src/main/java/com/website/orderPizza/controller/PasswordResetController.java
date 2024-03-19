package com.website.orderPizza.controller;
import com.website.orderPizza.entity.Users;
import com.website.orderPizza.payload.request.EmailRequest;
import com.website.orderPizza.payload.request.PasswordRequest;
import com.website.orderPizza.service.imp.UsersServiceImp;
import com.website.orderPizza.utils.Utility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@CrossOrigin("*")
@Controller
@RequestMapping("/reset")
public class PasswordResetController {
    private String TOKEN;
    @Autowired
    UsersServiceImp usersServiceImp;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/forgotPassword")
    public String showForgotPasswordForm() {
        return "forgotPassword";
    }

//    @PostMapping("/forgotPassword")
//    public String processForgotPasswordForm(HttpServletRequest httpServletRequest, Model model) {
//        String email = httpServletRequest.getParameter("email");
//        String token = RandomString.make(100);
//        try {
//            usersServiceImp.updateResetPasswordToken(token, email);
//            String resetPasswordLink = Utility.getSiteURL(httpServletRequest) + "/reset/reset_password?token=" + token;
//            sendEmail(email, resetPasswordLink);
//            model.addAttribute("message", "We have sent a reset password link to your email, please check");
//        } catch (Exception e) {
//            model.addAttribute("error", e.getMessage());
//        }
//        System.out.println("Email: " + email);
//        System.out.println("Token: " + token);
//        return "forgotPassword";
//    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> processForgotPasswordForm(@RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        String token = RandomString.make(100);
        try {
            usersServiceImp.updateResetPasswordToken(token, email);
            TOKEN = token;
            String resetPasswordLink = "http://localhost:3000" + "/confirm-password?token=" + token;
            sendEmail(email, resetPasswordLink);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Email: " + email);
        System.out.println("Token: " + token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam(value = "token") String token, Model model) {
        System.out.println("token" + token);
        Users users = usersServiceImp.getUserByResetPasswordToken(token);
        if (users == null) {
            System.out.println("Invalid token");
            return "message";
        }
        model.addAttribute("token", token);
        return "resetPassword";
    }
//    @PostMapping("/reset_password")
//    public String processResetPassword(HttpServletRequest request, Model model) {
//        String token = request.getParameter("token");
//        String password = request.getParameter("password");
//        Users users = usersServiceImp.getUserByResetPasswordToken(token);
//        if (users == null) {
//            model.addAttribute("message", "Invalid token");
//        } else {
//            usersServiceImp.updatePassword(users, password);
//            model.addAttribute("message", "Successfully updated password");
//        }
//        return "message";
//    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> processResetPassword(@RequestBody PasswordRequest passwordRequest, HttpServletRequest request, Model model) {
        String password = passwordRequest.getPassword();
        Users users = usersServiceImp.getUserByResetPasswordToken(TOKEN);
        TOKEN = "";
        if (users == null) {
            model.addAttribute("message", "Invalid token");
        } else {
            usersServiceImp.updatePassword(users, password);
            model.addAttribute("message", "Successfully updated password");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        mimeMessageHelper.setFrom("contact@pizza.com", "Pizza support");
        mimeMessageHelper.setTo(email);
        String subject = "Here's the link to reset your password";
        String content = "<p>Hello, </p>" +
                "<p> Your have requested to reset your password </p>" +
                "<p> Click the link below to change the password </p>" +
                "<p> <b><a href=\""+resetPasswordLink+"\" >Change My Password</a> </b> </p>" +
                "<p> Ignore this email if you do remember your password or you have not made the request </p>";
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        javaMailSender.send(message);
    }
}
