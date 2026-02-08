package com.app.service;

import com.app.exception.DuplicateStudentIdException;
import com.app.exception.StudentNotFoundException;
import com.app.model.Student;

public interface StudentService {
	void addStudent(Student student)throws DuplicateStudentIdException;
	
	void viewAllStudents();
	
	void searchStudentById(int id)throws StudentNotFoundException;
	
	void updateStudent(int id, String name, int age, double marks)throws StudentNotFoundException;
	
	void deleteStudent(int id)throws StudentNotFoundException;
}
