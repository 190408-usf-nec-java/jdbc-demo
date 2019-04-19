package com.revature.views;

import com.revature.util.ScannerUtil;

public class MainMenu implements View {

	@Override
	public View printOptions() {
		System.out.println("1. Users");
		System.out.println("2. Posts");
		System.out.println("3. Follower");
		System.out.println("0. Quit");
	
		int selection = ScannerUtil.getNumericChoice(3);
		
		switch(selection) {
		case 1: return new UserView();
		case 2: return new PostView();
		case 3: return new FollowerView();
		default: return null;
		}
		
	}

}
