package com.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.app.exception.DuplicateStudentIdException;
import com.app.exception.StudentNotFoundException;
import com.app.model.Student;
import com.app.service.StudentService;
import com.app.service.StudentServiceImplementation;

public class StudentUtil {
    private Scanner scanner = new Scanner(System.in);
    private StudentService service = new StudentServiceImplementation();
    
    public void start() {
		boolean flag = true;
		while(flag) {
			showMenu();
			int input = scanner.nextInt();
			switch(input) {
			case 0 : System.out.println("Thank for Using this application."); System.exit(0); break;
			case 1 : add(); break;
			case 2 : 
				System.out.print("Enter which Id data your want to update : "); int updateId = scanner.nextInt();
				update(updateId); break;
			case 3 : 
				System.out.print("Enter which Id data your want to search : "); int serachId = scanner.nextInt();
				search(serachId); break;
			case 4 : view(); break;
			case 5 : 
				System.out.print("Enter which Id data your want to delete : "); int deleteId = scanner.nextInt();
				delete(deleteId); break;
			}
		}
    }
    
    private void showMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
        System.out.println("0. Exit");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Search Student");
        System.out.println("4. View All Students");
        System.out.println("5. Delete Student");
        System.out.print("Enter your choice: ");
    }
    
    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidAge(int age) {
        return age > 0 && age <= 100;
    }

    private boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }

    private boolean isValidCourse(String course) {
        return course != null && !course.trim().isEmpty();
    }

    
    private void add() {

        System.out.print("Enter Student Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (!isValidName(name)) {
            System.out.println("❌ Name cannot be empty");
            return;
        }

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        if (!isValidAge(age)) {
            System.out.println("❌ Invalid age");
            return;
        }

        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();
        if (!isValidMarks(marks)) {
            System.out.println("❌ Invalid marks");
            return;
        }

        System.out.print("Enter number of subjects: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        if (num <= 0) {
            System.out.println("❌ Subject count must be greater than 0");
            return;
        }

        List<String> courses = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            System.out.print("Enter Course " + (i + 1) + ": ");
            String course = scanner.nextLine();

            if (!isValidCourse(course)) {
                System.out.println("❌ Course name cannot be empty");
                return;
            }
            courses.add(course);
        }

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setMarks(marks);
        student.setCourse(courses);

        try {
			service.addStudent(student);
		} catch (DuplicateStudentIdException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("✅ Student added successfully.");
    }

    private void update(int id) {

        scanner.nextLine();

        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        if (!isValidName(name)) {
            System.out.println("❌ Name cannot be empty");
            return;
        }

        System.out.print("Enter new Age: ");
        int age = scanner.nextInt();
        if (!isValidAge(age)) {
            System.out.println("❌ Invalid age");
            return;
        }

        System.out.print("Enter new Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();
        if (!isValidMarks(marks)) {
            System.out.println("❌ Invalid marks");
            return;
        }

        try {
            service.updateStudent(id, name, age, marks);
            System.out.println("✅ Student updated successfully.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
	
	private void view() {
		service.viewAllStudents();
	}
	
	private void delete(int id) {
		try {
			service.deleteStudent(id);
		} catch (StudentNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void search(int id) {
		try {
			service.searchStudentById(id);
		}catch(StudentNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
