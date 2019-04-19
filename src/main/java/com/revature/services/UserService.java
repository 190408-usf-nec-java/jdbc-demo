package com.revature.services;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.ScannerUtil;

public class UserService {
	
	UserDao userDao = new UserDao();
	
	/**
	 * Handles creation workflow for User
	 * bean
	 */
	public void createUser() {
		System.out.println("Please enter user's first name: ");
		String firstName = ScannerUtil.getLine();
		
		System.out.println("Please enter user's last name: ");
		String lastName = ScannerUtil.getLine();
		
		System.out.println("Please enter user's email address: " );
		String email = ScannerUtil.getLine();
		
		// Validate all this data
		
		User user = new User(firstName, lastName, email);
		
		user = userDao.saveUser(user);
	}
}
