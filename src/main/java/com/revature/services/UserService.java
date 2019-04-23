package com.revature.services;

import java.util.List;
import java.util.regex.Pattern;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.ScannerUtil;

public class UserService {

	UserDao userDao;
	
	public void setDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * Handles creation workflow for User bean
	 */
	public void createUser() {
		System.out.println("Please enter user's first name: ");
		String firstName = ScannerUtil.getLine();

		System.out.println("Please enter user's last name: ");
		String lastName = ScannerUtil.getLine();

		System.out.println("Please enter user's email address: ");
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

	public void getUserByEmail() {
		
		System.out.println("Enter email address: ");
		String email = ScannerUtil.getLine();
		
		while(!this.isValidEmail(email)) {
			System.out.println("Invalid e-mail address format. Please try again");
			email = ScannerUtil.getLine();
		}
		
		User user = this.userDao.getUserByEmail(email);
		System.out.println(user);
	}
	
	public boolean isValidEmail(String email) {
		String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		Pattern pattern = Pattern.compile(regex);
		return pattern.asPredicate().test(email);
	}

	/**
	 * alt+shift+r - update all references in scope 
	 * ctrl+shift+o - Auto-imports
	 * ctrl+shift+f - auto-format
	 * ctrl+m - maximizes a window
	 * ctrl+shift-s - Bring up options window (with generate options)
	 */
	public void getUsersByFirstName() {
		System.out.println("Enter first name:");
		String firstName = ScannerUtil.getLine();
		List<User> users = this.userDao.getUsersByFirstName(firstName);
		users.forEach(System.out::println);
	}
	

}
