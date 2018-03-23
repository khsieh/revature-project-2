package com.revature.warlockzone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.UserService;

@RestController
public class LogController {

	@Autowired
	UserService userService;
//	@GetMapping("/greeting")
//    public String greetingForm(Model model) {
//        model.addAttribute("greeting", new Greeting());
//        return "greeting";
//    }
//
//    @PostMapping("/greeting")
//    public String greetingSubmit(@ModelAttribute Greeting greeting) {
//        return "result";
//    }
	//get log in parameters
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String  loggedIn(@RequestParam("login_username") String username, @RequestParam("login_password") String password) {
		ModelMap model = new ModelMap();
		User user = userService.getUserByUsername(username);
		//if username is not found
		if(user == null) {
			return "Invalid username";
		   // return new ModelAndView("/login");
		}
		//if password is wrong
		if(!user.getPassword().equals(password)) {
			return "Invalid Password";
		 //   return new ModelAndView("/login");
		}
		//else logged in successfully

	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
		return "Logged in successful";
	   // return new ModelAndView("/main");
	}
	
	   @RequestMapping(value = "/addUser", method = RequestMethod.POST)
	   public String addUser(@ModelAttribute("SpringWeb") User user,  ModelMap model) {
		   
		   
			      model.addAttribute("name", student.getName());
			      model.addAttribute("age", student.getAge());
			      model.addAttribute("id", student.getId());
			      
			      return "result";
			   }
	
}
