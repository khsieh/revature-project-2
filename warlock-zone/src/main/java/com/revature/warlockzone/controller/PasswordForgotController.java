package com.revature.warlockzone.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.Mail;
import com.revature.warlockzone.beans.PasswordToken;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.dao.PasswordTokenDAO;
import com.revature.warlockzone.dao.UserDAO;
import com.revature.warlockzone.services.EmailService;

@RestController
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired 
    UserDAO userDao;
    @Autowired 
    PasswordTokenDAO tokenDao;
    @Autowired 
    EmailService emailService;


    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@RequestBody String email, @Valid PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return "forgot-password";
        }

        User user = userDao.findByEmail(email);
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }

        PasswordToken token = new PasswordToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenDao.save(token);
      //  passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
		//		+ "/reset?token=" + user.getResetToken());
        Mail mail = new Mail();
        mail.setFrom("matt.k.the.warlock@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");
       
        String url = "To reset your password, click the link below: \n" + "http://localhost:8080/reset-password?token=" + token.getToken();
     
        emailService.sendEmail(mail,url);

        return "redirect:/forgot-password?success";

    }

}