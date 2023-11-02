package com.UserDetails;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import com.UserDetails.model.UserDetails;
import com.UserDetails.repo.UserDetailsRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UserDetailRepoTest {

	@Autowired
	private UserDetailsRepo userDetailsRepo;

	@Test
	@DisplayName("testing save method")
	public void saveUserDetails() {
		UserDetails dummyDetails = UserDetails.builder().userId("1").firstName("megha").lastName("hirmukhe")
				.emailId("megha@gmail.com").city("pune").pinCode(411039L).personalDetails(null) // Pass null for
																								// personalDetails
				.build();
		UserDetails savedUserDetails = userDetailsRepo.save(dummyDetails);

		assertThat(savedUserDetails.getUserId()).isNotNull();
		assertThat(savedUserDetails.getPersonalDetails()).isNull(); // Assert personalDetails is null in saved
																	// UserDetails
	}
	
	//test for get by id
	@Test
	@DisplayName("test for get by id method of repo")
	public void getMethod()
	{
		UserDetails dummUserDetails = userDetailsRepo.findById("1").get();
		assertThat(dummUserDetails.getUserId()).isEqualTo("1");
		
	}
	
	//test get all method
	@Test
	@DisplayName("testing get all method of repo")
	public void getAllMethod()
	{
		List<UserDetails> getAlList = userDetailsRepo.findAll();
		
		
		//if size of list is greater than 0. It means their are list of data in repo 
		assertThat(getAlList.size()).isGreaterThan(0);
	}
	
	//test for updates
	@Test
	@DisplayName("test for update by email id method of repo")
	public void updateMethod()
	{
		UserDetails userDetail = userDetailsRepo.findById("1").get();
		
		userDetail.setEmailId("megha1@gmail.com");
		UserDetails updatedUserDetails = userDetailsRepo.save(userDetail);
		
		assertThat(updatedUserDetails.getEmailId()).isEqualTo("megha1@gmail.com");
	}
	
	//test for delete
	@Test
	@DisplayName("testing delete method of userdetails repo")
	public void deleteMethod()
	{
		//step 1: get that usetdetail which we want to delete
		UserDetails uDetails = userDetailsRepo.findById("1").get();
		
		UserDetails u = null;
		//delete the userdetail
		userDetailsRepo.deleteByemailId(uDetails.getEmailId());  //we have deleted empployee
		
		 // Attempt to retrieve the deleted user
        Optional<UserDetails> deletedUserDetails = userDetailsRepo.findById("1");

        // Assert that the user is not present in the database anymore
        assertThat(deletedUserDetails).isEmpty();
		
		
		
	}
}
