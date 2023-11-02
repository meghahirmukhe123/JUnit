package com.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.UserDetails.external.services.PersonalDetailsService;
import com.UserDetails.model.PersonalDetails;
import com.UserDetails.model.UserDetails;
import com.UserDetails.repo.UserDetailsRepo;
import com.UserDetails.service.UserDetailsServices;
import com.UserDetails.serviceImpl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UserDetailsServiceImplTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private UserDetailsServices userDetailsServices;

	@Mock
	private UserDetailsRepo userDetailsRepo;

	@Mock
	private PersonalDetailsService personalDetailsService;

	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;

	UserDetails userDetails1 = new UserDetails("1", "megha", "lastname", "megha@gmail.com", "pune", 411039L, null);
	UserDetails userDetails2 = new UserDetails("2", "anu", "shinde", "anu@gmail.com", "pune", 411039L, null);
	UserDetails userDetails3 = new UserDetails("3", "tanu", "kumar", "tanu@gmail.com", "pune", 411039L, null);

	@BeforeEach
	public void setUp() {
		// if we want to initialize mockito before each test
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userDetailsServiceImpl).build();
	}

	@Test
	@DisplayName("testing save method from userDetails service impl")
	public void testSaveData() {
		// Arrange
		UserDetails userDetails = new UserDetails();
		when(userDetailsRepo.save(userDetails)).thenReturn(userDetails);

		// Act
		UserDetails savedUserDetails = userDetailsServiceImpl.saveData(userDetails);

		// Assert
		assertNotNull(savedUserDetails);
		assertEquals(userDetails, savedUserDetails);
	}

	@Test
	@DisplayName("testing get all method from userDetails service impl")
	public void testGetAll() {
		// Arrange
		List<UserDetails> userDetailsList = new ArrayList<>();
		when(userDetailsRepo.findAll()).thenReturn(userDetailsList);

		// Act
		List<UserDetails> result = userDetailsServiceImpl.getAll();

		// Assert
		assertEquals(userDetailsList, result);
	}

	@Test
	@DisplayName("testing get by user id method from userDetails service impl")
	public void testGetByUserId() {
		// Arrange
		String userId = "1";
		UserDetails userDetails = new UserDetails();
		when(userDetailsRepo.findById(userId)).thenReturn(java.util.Optional.of(userDetails));

		// Act
		UserDetails result = userDetailsServiceImpl.getByUserId(userId);

		// Assert
		assertEquals(userDetails, result);
	}

	@Test
	@DisplayName("testing deleteByEmailId method from userDetails service impl")
	public void testDeleteByEmailId() {
		// Arrange
		String emailId = "test@example.com";

		// Act
		String result = userDetailsServiceImpl.deleteByEmailId(emailId);

		// Assert
		assertEquals("User details deleted", result);
		verify(userDetailsRepo, times(1)).deleteByemailId(emailId);
	}

	@Test
	@DisplayName("testing get user details by email id method from userDetails service impl")
	public void testFetchUserDetailsByEmailId() {
		// Arrange
		String emailId = "megha@gmail.com";
		UserDetails userDetails = new UserDetails();
		List<PersonalDetails> personalDetailsList = new ArrayList<>();
		when(userDetailsRepo.findByemailId(emailId)).thenReturn(userDetails);
		when(personalDetailsService.getPersonalDetails(emailId)).thenReturn(personalDetailsList);

		// Act
		UserDetails result = userDetailsServiceImpl.fetchUserDetailsByEmailId(emailId);

		// Assert
		assertNotNull(result);
		assertEquals(userDetails, result);
	}

	@Test
	@DisplayName("testing update by email id from userDetails service impl")
	public void testUpdateByEmailId() {
		// Arrange
		String emailId = "megha@gmail.com";
		UserDetails existingUserDetails = new UserDetails();
		UserDetails updatedUserDetails = new UserDetails();
		when(userDetailsRepo.findByemailId(emailId)).thenReturn(existingUserDetails);
		when(userDetailsRepo.save(existingUserDetails)).thenReturn(updatedUserDetails);

		// Act
		UserDetails result = userDetailsServiceImpl.updateByEmailId(emailId, updatedUserDetails);

		// Assert
		assertNotNull(result);
		assertEquals(updatedUserDetails, result);
	}
}
