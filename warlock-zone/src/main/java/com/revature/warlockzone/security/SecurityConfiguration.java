package com.revature.warlockzone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	  @Autowired
	    private UserDetailsService userDetailsService;
	  
	/*//enable security, every page requires login and redirects to login page is user hasn't login
	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests()
            .antMatchers("/home", "/register").permitAll() //allows users to see homepage and registration page
            .anyRequest().authenticated()
            .and()
        .formLogin() //authenticate using login page
            .loginPage("/login")
            .permitAll()
            .and()
        .logout()
            .permitAll();
	    }
	   //encrypts password
	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    //add db users to authorized list
	   @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	 */   }
	   
	   //disable security
	@Override
	protected void configure(HttpSecurity httpSecurity) {
		try {
			//authorize and permit every HTTPRequest
			httpSecurity
				.authorizeRequests()
				.anyRequest()
				.permitAll();
			
			httpSecurity.csrf().disable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
