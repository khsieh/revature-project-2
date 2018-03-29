<<<<<<< HEAD
package com.revature.warlockzone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.revature.warlockzone.beans.PasswordToken;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.dao.PasswordTokenDAO;
import com.revature.warlockzone.services.UserService;

@RestController
@CrossOrigin
public class PasswordResetController {

	
	@Autowired
	UserService userService;
	@Autowired
	PasswordTokenDAO passTokenDao;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  
	@SuppressWarnings("rawtypes")
	//    @PostMapping
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)

    @Transactional
    public ResponseEntity  handlePasswordReset(@RequestBody String password, @RequestParam("token") String tokens,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

      

        PasswordToken token = passTokenDao.findByToken(tokens);
        if (token == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (token.isExpired()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
        	User user = token.getUser();
            //String updatedPassword = password;
    		String hashedPassword = passwordEncoder.encode(user.getPassword());

    		user.setPassword(hashedPassword);
            userService.updatePassword(user);
            passTokenDao.delete(token);
            return new ResponseEntity(HttpStatus.ACCEPTED);

        }
    }
}
=======
package com.revature.warlockzone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.revature.warlockzone.beans.PasswordToken;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.dao.PasswordTokenDAO;
import com.revature.warlockzone.services.UserService;

@RestController
@CrossOrigin
public class PasswordResetController {

	
	@Autowired
	UserService userService;
	@Autowired
	PasswordTokenDAO passTokenDao;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  
	@SuppressWarnings("rawtypes")
	//    @PostMapping
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)

    @Transactional
    public ResponseEntity  handlePasswordReset(@RequestBody String password, @RequestParam("token") String tokens,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

      

        PasswordToken token = passTokenDao.findByToken(tokens);
        if (token == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (token.isExpired()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
        	User user = token.getUser();
            //String updatedPassword = password;
    		String hashedPassword = passwordEncoder.encode(user.getPassword());

    		user.setPassword(hashedPassword);
            userService.updatePassword(user);
            passTokenDao.delete(token);
            return new ResponseEntity(HttpStatus.ACCEPTED);

        }
    }
}
>>>>>>> 6b6b04f696c2688a8f6772c45e1ad51d51f18380
