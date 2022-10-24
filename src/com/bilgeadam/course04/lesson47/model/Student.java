package com.bilgeadam.course04.lesson47.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@ToString

public class Student {
	private String firstName;
	private String middleName;
	private String lastName;
	private long nationalId;
	private long registratonId;
	@Setter
	private List<Telephone> telephones;
	
	public void addTelephone(Telephone telephone) {
		if (telephones == null) {
			telephones = new ArrayList<>();
		}
		telephones.add(telephone);
	}

	public Student(String firstName, String middleName, String lastName, long nationalId, long registratonId) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.nationalId = nationalId;
		this.registratonId = registratonId;
	}
}
