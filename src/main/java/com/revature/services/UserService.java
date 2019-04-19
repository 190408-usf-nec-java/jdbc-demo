package com.revature.services;

import java.util.List;

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
		
		User user = new User(0, firstName, lastName, email);
		
		userDao.safeSaveUser(user);
		System.out.println(user);
	}

	public void getUserById() {
		System.out.println("Enter the user ID:");
		int id = ScannerUtil.getNumericChoice(10000);
		User user = this.userDao.getUserById(id);
		System.out.println(user);
	}

	public void getUsersByLastName() {
		System.out.println("Enter last name: ");
		String lastName = ScannerUtil.getLine();
		List<User> users = this.userDao.getUsersByLastName(lastName);
		users.forEach(System.out::println);
	}
	
	
}
