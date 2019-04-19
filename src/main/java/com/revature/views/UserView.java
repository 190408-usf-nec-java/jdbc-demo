package com.revature.views;

import com.revature.services.UserService;
import com.revature.util.ScannerUtil;

public class UserView implements View {

	UserService userService = new UserService();

	@Override
	public View printOptions() {
		System.out.println("User Menu");

		System.out.println("1. Create User");
		System.out.println("0. Back");

		int selection = ScannerUtil.getNumericChoice(1);

		switch (selection) {
		case 1:
			this.userService.createUser();
			return this;
		default:
			return new MainMenu();
		}
	}

}
