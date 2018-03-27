package com.revature.warlockzone.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.revature.warlockzone.beans.PasswordToken;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.dao.PasswordTokenDAO;
import com.revature.warlockzone.services.UserService;

@RestController
public class PasswordController {
	
	@Autowired
	UserService userService;
	@Autowired
	PasswordTokenDAO passTokenDao;

  
	
    @GetMapping("/forgot")
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        PasswordToken resetToken = passTokenDao.findByToken(token);
        if (resetToken == null){
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        return "reset-password";
    }
    //http://localhost:8080/reset-password?token=2bb51d8a-3324-4f26-a13a-f0ff25b1413c

//    @PostMapping
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)

    @Transactional
    public String handlePasswordReset(@RequestBody String password, @RequestParam("token") String tokens,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

      

        PasswordToken token = passTokenDao.findByToken(tokens);
        User user = token.getUser();
        String updatedPassword = password;
        user.setPassword(updatedPassword);
        userService.updatePassword(user);
        passTokenDao.delete(token);

        return "redirect:/login?resetSuccess";
    }
}
