package com.http_methods;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.http_methods.controller.studentcontroller;
import com.http_methods.entity.student;
import com.http_methods.repo.studentrepo;
import static org.hamcrest.Matchers.hasSize;


public class StudentControllerTest {

	// step 1: create object of mockMvc
	private MockMvc mockMvc;

	// step 2: object mapper => use to convert json to string and vise versa
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	// step 3: we will mock studentRepository
	@Mock
	private studentrepo repository;

	// step 4:Inject that class which accepts the mock
	@InjectMocks
	private studentcontroller controller;

	student dummyDate1 = new student("1", "megha", 10000);
	student dummyDate2 = new student("2", "anu", 11000);
	student dummyDate3 = new student("3", "tanu", 12000);

	@BeforeEach
	public void beforeEach() {
		// if we want to initialize mockito before each test
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	// this test is for get all method
	@Test
	@DisplayName("testing get all method")
	@Order(1)
	public void getAllMethod() throws Exception {
		List<student> stu = new ArrayList<>(Arrays.asList(dummyDate1, dummyDate2, dummyDate3));

		// we have mocked student repo and mock get method
		Mockito.when(repository.findAll()).thenReturn(stu);

		// to perform emulation of get request 
		mockMvc.perform(MockMvcRequestBuilders
				.get("/http")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3))) // to check whether we have 3 items or not
		
		//lets check data is present in list or not by providing random data
		.andExpect(jsonPath("$[2].stuname", is("tanu")));  //this means we expect nameo of index position 2 has name tanu
		
	}
	
	
	//lets test get by student id
	@Test 
	@DisplayName("testing get by student id method")
	@Order(2)
	public void getStudentByIdMethod() throws Exception
	{
		
		//we have mock get by id method
		Mockito.when(repository.findBystuid(dummyDate1.getStuid())).thenReturn(dummyDate1);

		
		//we have mocked getByid method
		mockMvc.perform(MockMvcRequestBuilders
				.get("/http/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue())) // to check whether it is null or not
		
		//lets check data is present in list or not by providing random data
		.andExpect(jsonPath("$.stuname", is("megha")));  //this means we expect nameo of index position 2 has name tanu
		

	}
	
	//test for save student
	@Test
	@DisplayName("testing save method")
	@Order(3)
	public void saveStudentDataMethod() throws Exception {
	    //step 1: create a record which we want to post
	    student dummyData4 = student.builder()
	            .stuid("4")
	            .stuname("manu")
	            .stufees(13000)
	            .build();

	    //step 2: convert java object dummyData4 into string
	    String dummyData4content =
	    		objectMapper.writeValueAsString(dummyData4);

	    //step 3: mock save method
	    Mockito.when(repository.save(Mockito.any(student.class))).thenReturn(dummyData4);

	    //step 4: build mock http servlet request build
	    MockHttpServletRequestBuilder mockRequest =
	            MockMvcRequestBuilders.post("/http")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON)
	                    .content(dummyData4content);

	    //step 5: perform post request
	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue())) // to check whether it is null or not
	            .andExpect(jsonPath("$.stuname", is("manu")));  //this means we expect name of index position 2 has name manu
	}
	
	
	//lets test update method
	@Test
	@DisplayName("testing update method")
	@Order(4)
	public void updateMethod() throws Exception {
	    // Create an updated student record
	    student updatedRecord = student.builder()
	            .stuid("1")
	            .stuname("meghahirmukhe")
	            .stufees(15000)
	            .build();
	    
	    // Mock the findById method in the repository
	    Mockito.when(repository.findById(dummyDate1.getStuid())).thenReturn(Optional.ofNullable(dummyDate1));

	    // Mock the save method in the repository
	    Mockito.when(repository.save(Mockito.any(student.class))).thenReturn(updatedRecord);

	    // Convert the updatedRecord to JSON string
	    String updatedContent = objectWriter.writeValueAsString(updatedRecord);
	    
	    // Create a PUT request to update the student with ID 1
	    MockHttpServletRequestBuilder mockRequest =
	            MockMvcRequestBuilders.put("/http/1")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON)
	                    .content(updatedContent);

	    // Perform the update request and assert the response
	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue())) 
	            .andExpect(jsonPath("$.stuname", is("meghahirmukhe")));  
	}
	
	//testing delete method
	@Test
	@DisplayName("testing delete method")
	@Order(5)
	public void deleteMethod() throws Exception
	{
		Mockito.when(repository.findById(dummyDate2.getStuid())).thenReturn(Optional.of(dummyDate2));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/http/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		
				
	}

}
