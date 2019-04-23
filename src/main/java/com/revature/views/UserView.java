package com.revature.views;

import com.revature.services.UserService;
import com.revature.util.ScannerUtil;

public class UserView implements View {

	UserService userService = new UserService();

	@Override
	public View printOptions() {
		System.out.println("User Menu");

		System.out.println("1. Create User");
		System.out.println("2. Get User by ID");
		System.out.println("3. Get Users by last name");
		System.out.println("4. Get User by email address");
		System.out.println("5. Get Users by first name");
		System.out.println("0. Back");

		int selection = ScannerUtil.getNumericChoice(5);

		switch (selection) {
		case 1:
			this.userService.createUser();
			return this;

		case 2:
			this.userService.getUserById();
			return this;

		case 3:
			this.userService.getUsersByLastName();
			return this;
			
		case 4:
			this.userService.getUserByEmail();
			return this;
			
		case 5:
			this.userService.getUsersByFirstName();
			return this;
			
		default:
			return new MainMenu();
		}
	}

}
