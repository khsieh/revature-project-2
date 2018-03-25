package com.revature.warlockzone.beans;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="PASSWORDTOKEN")
public class PasswordToken {
	  @Id
	    @GeneratedValue
	    private int id;
	  
	    private String token;
		
		@OneToOne
		@JoinColumn(name = "USERID")
	    private User user;
	    private Date expiryDate;
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getToken() {
			return token;  
		}

		public Date getExpiryDate() {
			return expiryDate;
		}

	    public void setExpiryDate(Date expiryDate) {
		        this.expiryDate = expiryDate;
		    }

		public void setExpiryDate(int minutes){
		        Calendar now = Calendar.getInstance();
		        now.add(Calendar.MINUTE, minutes);
		        this.expiryDate = now.getTime();
		    }

		public boolean isExpired() {
		        return new Date().after(this.expiryDate);
		  }

		public void setToken(String token) {
			this.token = token;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	  
}
