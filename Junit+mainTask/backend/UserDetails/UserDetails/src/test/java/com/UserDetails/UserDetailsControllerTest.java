package com.UserDetails;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.UserDetails.controller.UserDetailsController;
import com.UserDetails.model.PersonalDetails;
import com.UserDetails.model.UserDetails;
import com.UserDetails.repo.UserDetailsRepo;
import com.UserDetails.service.UserDetailsServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import static org.hamcrest.Matchers.hasSize;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UserDetailsControllerTest {

	// create object of mock mvc
	private MockMvc mockmvc;

	// create object mapper to convert json into string and vise versa
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	// as user details controller depend on user details service will mock user
	// details service
	@Mock
	private UserDetailsServices userservice;

	// as user details service is depend on repo we will mock user details repo
	@Mock
	private UserDetailsRepo userrepo;

	@Mock
	private PersonalDetails personalDetails;

	// now will inject that class which accepts mock i.e. user details controller
	@InjectMocks
	private UserDetailsController userDetailsController;

	UserDetails userDetails1 = new UserDetails("1", "megha", "lastname", "megha@gmail.com", "pune", 411039L, null);
	UserDetails userDetails2 = new UserDetails("2", "anu", "shinde", "anu@gmail.com", "pune", 411039L, null);
	UserDetails userDetails3 = new UserDetails("3", "tanu", "kumar", "tanu@gmail.com", "pune", 411039L, null);

	@BeforeEach
	public void beforeEach() {
		// if we want to initialize mockito before each test
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(userDetailsController).build();

	}

	// test for get all method
	@Test
	@DisplayName("testing get all method from userdetails controller")
	public void getAllMethod() throws Exception {
		List<UserDetails> userDetailsList = new ArrayList<>(Arrays.asList(userDetails1, userDetails2, userDetails3));

		// we have mocked userdetails repo and mock get all method
//		Mockito.when(userservice.fetchUserDetailsByEmailId(userDetails1.getEmailId())).thenReturn(userDetails1);

		Mockito.when(userservice.getAll()).thenReturn((List<UserDetails>) userDetails1);

		// to perform get request
		String responseContent = mockmvc
				.perform(MockMvcRequestBuilders.get("/getall").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		System.out.println("Response Content: " + responseContent);
	}

	// test for get by email id
	@Test
	@DisplayName("testing get by email id method from userdetails controller")
	public void getByUserEmaiIdMethod() throws Exception

	{

		Mockito.when(userservice.fetchUserDetailsByEmailId(userDetails1.getEmailId())).thenReturn(userDetails1);

		// lets mock get by email id method
		mockmvc.perform(MockMvcRequestBuilders
				.get("/getbyemailid/megha@gmail.com")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.firstName", is("megha")));
				
				
	}
	
	//test for get by user id
	@Test
	@DisplayName("testing get by user id from userdetails controller")
	public void getByUserId() throws Exception
	{
		Mockito.when(userservice.getByUserId(userDetails2.getUserId())).thenReturn(userDetails2);
		
		//lets mock get by user id
		mockmvc.perform(MockMvcRequestBuilders
				.get("/getbyid/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.lastName", is("shinde")));
	}
	
	
	
	//test for update method
	@Test
	@DisplayName("testing update method from userdetails controller")
	public void updateMethod() throws Exception
	{
		//lets create updated userdetails record
		UserDetails updatedUserDetails1 = 
				UserDetails.builder()
				.userId("1")
				.firstName("megha")
				.lastName("dayanand hirmukhe")
				.emailId("megha@gmail.com")
				.city("pune")
				.pinCode(411030L)
				.build();
		
		//step 1: mock find by user id 
		Mockito
		.when(userservice.fetchUserDetailsByEmailId
				(userDetails1.getEmailId()))
		.thenReturn(userDetails1);
		
		//step 2: mock save method
		Mockito.when(userservice.saveData(userDetails1)).thenReturn(updatedUserDetails1);
		
		//we have added step 1 and step 2 as first we are getting user by email id then after making updates we are saving that data in database
		
		//convert updated userdetails 1 into string
		String updatedContent1= objectWriter.writeValueAsString(updatedUserDetails1);
		
		
		//create PUT request to update data
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
				MockMvcRequestBuilders.put("/update/megha@gmail.com")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(updatedContent1);
		
		//perform update request and assert the reponse
		mockmvc.perform(mockHttpServletRequestBuilder)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.firstName", is("megha")));
	}
	
	//test for delete by user id
	@Test
	@DisplayName("testing delete by email id method from userdetails controller")
	public void deleteMethod() throws Exception
	{

		Mockito.when(userservice.fetchUserDetailsByEmailId(userDetails3.getEmailId())).thenReturn(userDetails3);
		
		mockmvc.perform(MockMvcRequestBuilders
				.delete("/delete/tanu@gmail.com")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpectAll(status().isOk());
		

	}

}
