package com.bilgeadam.course04.lesson47;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bilgeadam.course04.lesson47.model.Student;
import com.bilgeadam.course04.lesson47.model.StudentClass;
import com.bilgeadam.course04.lesson47.model.Telephone;

public class MyMainClass {
	private List<Student> students = new ArrayList<>();
	private Connection connection = DatabaseConnection.getInstance().getConnection();
	
	public static void main(String... args) {
		if (args.length != 1) {
			System.err.println("Wrong number of arguments. Expected <<<1>>> actual <<<" + args.length + ">>>");
			System.exit(100);
		}

		DatabaseConnection.getInstance().setPropertiesFile(args[0]);
		MyMainClass main = new MyMainClass();
		main.retrieveStudentClasses();
		main.listStudentClasses();

	}

	private void listStudentClasses() {
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void retrieveStudentClasses() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs    = stmt.executeQuery("SELECT * FROM student_information");
			String actualFullName = "";
			long actualTelephoneNumber = Long.MAX_VALUE;
			Student stdnt = null;
			while (rs.next()) {
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String middleName=rs.getString("middle_name");
				String fullName = firstName + " " + middleName + " " + lastName;
				if (actualFullName.equals(fullName)) {
					long telephoneNumber = rs.getLong("telephone");
					if (telephoneNumber == actualTelephoneNumber) {
						
					}
					else {
						Telephone telephone = new Telephone(telephoneNumber, rs.getString("telephone_type"));
						actualTelephoneNumber = telephoneNumber;
						stdnt.addTelephone(telephone);
					}
				}
				else {
					actualFullName = fullName;
					actualTelephoneNumber = Long.MAX_VALUE;
					stdnt = new Student(firstName, middleName, lastName, rs.getLong("registration_number"), rs.getLong("national_id"));
					students.add(stdnt);
				}
				
//				StudentClass student = StudentClass.builder()
//				.city(rs.getString(4))
//				.country(rs.getString(5))
//				.countryCode(rs.getInt(6))
//				.courseName(rs.getString(7))
//				.courseId(rs.getInt(8))
//				.attandenceYear(rs.getInt(9))
//				.grade(rs.getDouble(10))
//				.street(rs.getString(11))
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}  

//	private void createStudents() {
//		StudentClass student = new StudentClass();
//		student.setAttandenceYear(2022);
//		student.setCity("İstanbul");
//		student.setCountry("Turkey");
//		student.setCountryCode(34);
//		student.setCourseId(1);
//		student.setCourseName("Java");
//		student.setFirstName("Erşan");
//		student.setGrade(8.5d);
//		student.setLastName("Kuneri");
//		student.setNationalId(123456L);
//		student.setStreet("Beyoğlu");
//		student.setTelephoneNumbers("1234567890, 234567891, 234567889");
//		System.out.println(student);
//	}
}
