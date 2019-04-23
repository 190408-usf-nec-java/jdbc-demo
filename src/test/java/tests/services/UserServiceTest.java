package tests.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.services.UserService;

public class UserServiceTest {

	UserService userService = new UserService();

	UserDao userDao = Mockito.mock(UserDao.class);

	@Before
	public void setup() {
		userService.setDao(userDao);
	}
	
	@After
	public void cleanUp() {
		System.setIn(System.in);
	}
	
	@Test
	public void testEmailValidator_ValidEmail() throws Exception {

		String validEmail = "xfactor@gmail.com";
		assertTrue("Valid email should return true", userService.isValidEmail(validEmail));
	}
		
	@Test
	@Ignore
	public void testLongEmailAddress() throws Exception {

		char start = 'a';
		StringBuilder str = new StringBuilder("");
		for(int i = 0; i < 260; i++) {
			str = str.append(start);
			start = (char) (start + 1);
			if(start > 'z') start = 'a';
		}
		str.append("@hotmail.com");
		
		assertFalse("Invalidly long e-mail address should return false", 
				userService.isValidEmail(str.toString()));
	}
	
	@Test
	public void testGetUsersByFirstname() throws Exception {
		// User input being streamed
		ByteArrayInputStream in = new ByteArrayInputStream("X".getBytes());
		System.setIn(in);
		
		// Mocking/Stubbing userDao method
		List<User> users = new ArrayList<>();
		User user = new User(1, "X", "Test X", "xfactor@test.com");
		users.add(user);
		Mockito.stub(userDao.getUsersByFirstName("X")).toReturn(users);
		
		// Connecting piped streams - I replace the System.out with a piped output stream
		// then read the data coming out of the connected input stream, then
		// compare that to my expected result
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream();
		pos.connect(pis);
		System.setOut(new PrintStream(pos));
		BufferedInputStream bis = new BufferedInputStream(pis);
		
		// Call method to be tested
		userService.getUsersByFirstName();
		
		// Read bytes which are being written by the test method
		int available = bis.available();
		byte[] bytes = new byte[available];
		bis.read(bytes);
		
		String expected = "Enter first name:\r\n" + user.toString();
		assertEquals("String printed to System.out should equal User.toString()",
				expected.trim(),  new String(bytes).trim());

	}
}
