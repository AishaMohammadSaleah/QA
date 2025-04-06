package main.najah.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import main.najah.code.UserService;

@DisplayName("User Service")
class UserServiceSimpleTest {
	UserService userService;
	@BeforeEach
	void setUp() {
		userService=new UserService();
	}
	@ParameterizedTest
	@DisplayName("Test vaild email")
	@CsvFileSource(resources = "../../../data/productTest/emailTest.csv",numLinesToSkip = 0)
	void testVaildEmail(String email,boolean expected) {
		assertEquals(expected, userService.isValidEmail(email));
	}
	
	@Test
	@DisplayName("Test Valid Authenticate")
	void testValidAuthenticate() {
		assertTrue(userService.authenticate("admin", "1234"));
	}
	
	@Test 
	@DisplayName("Test Authenticate Invalid Credentials")
	void testAuthenticateInvalidCredentials() {
		assertFalse(userService.authenticate("user", "1234"));
		assertFalse(userService.authenticate("admin", "wrongpass"));
		assertFalse(userService.authenticate("", ""));
		assertFalse(userService.authenticate(null,null));
	}
}
