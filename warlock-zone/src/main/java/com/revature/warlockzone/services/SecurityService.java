package com.revature.warlockzone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


//provide current logged in user
public class SecurityService {
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserDetailsService userDetailsService;


	    public String findLoggedInUsername() {
	        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
	        if (userDetails instanceof UserDetails) {
	            return ((UserDetails)userDetails).getUsername();
	        }

	        return null;
	    }

}
