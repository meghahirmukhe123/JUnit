package com.UserDetails.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "UserDetails")
public class UserDetails {
	
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String city;
	private long pinCode;
	
	private List<PersonalDetails> personalDetails=new ArrayList<>();


}
