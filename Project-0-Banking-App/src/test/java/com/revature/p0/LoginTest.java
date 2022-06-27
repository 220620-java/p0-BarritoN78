package com.revature.p0;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginTest {
	
	@InjectMocks
	private LoginScreen login;
	
	@Mock
	private BankHome bank;

	@BeforeEach
	void setUp() throws Exception {
		login = new LoginScreen();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
