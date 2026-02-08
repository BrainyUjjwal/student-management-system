package com.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.app.exception.DuplicateStudentIdException;
import com.app.exception.StudentNotFoundException;
import com.app.model.Student;

public class StudentServiceImplementation implements StudentService{
	
	private List<Student> students = new ArrayList<>();
	@Override
	public void addStudent(Student student) throws DuplicateStudentIdException{
		for (Student s : students) {
	        if (s.getId() == student.getId()) {
	            throw new DuplicateStudentIdException("Student with Id " + student.getId() + " already exists");
	        }
	    }
		students.add(student);
		System.out.println("Student data added successfully");
	}

	@Override
	public void viewAllStudents() {
		if (students.isEmpty()) {
	        System.out.println("No students found.");
	        return;
	    }
		Iterator<Student> iterator = students.iterator();
		while(iterator.hasNext()) {
			Student student = iterator.next();
			System.out.println(student);
		}
	}

	@Override
	public void searchStudentById(int id) throws StudentNotFoundException {
		if (students.isEmpty()) {
	        System.out.println("No students found.");
	        return;
	    }
		for(Student student : students) {
			if(student.getId()==id) {
				System.out.println(student);
				return;
			}
		}
	    throw new StudentNotFoundException("Student with Id " + id + " not found");		
	}

	@Override
	public void updateStudent(int id, String name, int age, double marks) throws StudentNotFoundException {
		if (students.isEmpty()) {
	        System.out.println("No students found.");
	        return;
	    }
		for(Student student : students) {
			if(student.getId() == id) {
				student.setName(name);
				student.setAge(age);
				student.setMarks(marks);
				return;
			}
		}
	    throw new StudentNotFoundException("Student with Id " + id + " not found");		
	}

	@Override
	public void deleteStudent(int id) throws StudentNotFoundException{
		if (students.isEmpty()) {
	        System.out.println("No students found.");
	        return;
	    }
		Iterator<Student> iterator = students.iterator();
		while(iterator.hasNext()) {
			Student student = iterator.next();
			if(student.getId()==id) {
				iterator.remove();
				return;
			}
		}
	    throw new StudentNotFoundException("Student with Id " + id + " not found");
	}

}
