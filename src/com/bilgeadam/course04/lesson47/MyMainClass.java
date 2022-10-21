package com.bilgeadam.course04.lesson47;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bilgeadam.course04.lesson47.model.StudentClass;
import com.bilgeadam.course04.lesson47.model.Telephone;

public class MyMainClass {
	private List<StudentClass> studentClasses = new ArrayList<>();
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
		for (StudentClass studentClass : studentClasses) {
			System.out.println(studentClass);
		}
	}

	private void retrieveStudentClasses() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs    = stmt.executeQuery("SELECT * FROM student_classes");
			
			while (rs.next()) {
				StudentClass student = StudentClass.builder()
				.nationalId(rs.getLong(1))
				.firstName(rs.getString(2))
				.lastName(rs.getString(3))
				.city(rs.getString(4))
				.country(rs.getString(5))
				.countryCode(rs.getInt(6))
				.courseName(rs.getString(7))
				.courseId(rs.getInt(8))
				.attandenceYear(rs.getInt(9))
				.grade(rs.getDouble(10))
				.street(rs.getString(11)).build();
				studentClasses.add(student);
			}
			
			for (StudentClass studentClass : studentClasses) {
				rs    = stmt.executeQuery("SELECT \"number\" AS telNo, type FROM telephone_numbers WHERE national_id=" + studentClass.getNationalId());
				while (rs.next()) {
					Telephone tel = new Telephone(rs.getLong("telNo"), rs.getString("type"));  // <=======
					System.out.println(tel);
				}
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
