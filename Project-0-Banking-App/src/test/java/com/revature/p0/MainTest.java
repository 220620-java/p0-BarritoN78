package com.revature.p0;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(Lifecycle.PER_CLASS)
public class MainTest {
	private P0Main main;
	private InputStream sysInBackup = System.in;
	
	@BeforeAll
	public void BeforeAll() {
		main = new P0Main();
	}
	
	@Test
	public void getInputTest() {
		String result = "";
		InputStream input = new ByteArrayInputStream("input".getBytes());
		System.setIn(input);
		
		result = main.getInput();
		Assertions.assertEquals("input", result);
	}

	@AfterAll
	public void afterAll() {
		System.setIn(sysInBackup);
	}
}