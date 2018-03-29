package com.revature.warlockzone.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired 
    UserDAO userDao;
    @Autowired 
    PasswordTokenDAO tokenDao;
    @Autowired 
    EmailService emailService;

    @SuppressWarnings("rawtypes")
	@PostMapping
    public ResponseEntity  processForgotPasswordForm(@RequestBody String email,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User user = userDao.findByEmail(email);
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        PasswordToken token = new PasswordToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(50000);
        tokenDao.save(token);
      //  passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
		//		+ "/reset?token=" + user.getResetToken());
        Mail mail = new Mail();
        mail.setFrom("matt.k.the.warlock@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");
       
        String url = "To reset your password, click the link below: \n" + "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/reset-password?token=" + token.getToken();
     
        emailService.sendEmail(mail,url);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
