package com.bilgeadam.course04.lesson47.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class StudentClass {
	private long oid;
	private long nationalId;
	private String firstName;
	private String lastName;
	private String city;
	private String street;
	private String country;
	private int countryCode;
	@Setter
	private List<Telephone> numbers;
	private int courseId;
	private String courseName;
	private int attandenceYear;
	private double grade;
}
