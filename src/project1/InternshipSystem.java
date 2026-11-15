package project1;
import java.util.*;

public class InternshipSystem {
	
	static Scanner input = new Scanner (System.in);
	static ArrayList<Student>students = new ArrayList<>();

	public static void main(String[] args) {
		int choice = 0 ;
		
		do {
			System.out.println("\n=====BSC Internship Matching System ====");
			System.out.println("1- Add New Student ");
			System.out.println("2- Register Courses ");
			System.out.println("3- Register Graduation Project ");
			System.out.println("4- Find Matching Training Centers  ");
			System.out.println("5- Assing Internship ");
			System.out.println("6- Search Student  ");
			System.out.println("7- Display All Students  ");
			System.out.println("8- Exit ");
			
			System.out.println("Enter Your Choice ");
			
			
			
			String menuInput = input.nextLine().trim();
			
			try {
				choice = Integer.parseInt(menuInput);
			}catch (NumberFormatException e) {
				System.out.println("Invalid Choise. Please Enter a Number between 1 and 8");
				continue;
			}
			
			switch (choice) {
			case 1:
				addNewStudent();
				break;
			case 2:
				RegisterCourses ();
				break;
			case 3:
				registerGraduationProject();
				break;
			case 4:
				System.out.println("Find Matching Training Centers - To be integrated");
				break;
			case 5:
				System.out.println("AssingInternship - To be integrated ");
				break;
			case 6:
				System.out.println("Search Student - To be integrated ");
				break;
			case 7:
				System.out.println("Display All Students - To be integrated ");
				break;
			case 8:
				System.out.println("Thank you for using the BSC internship Matching System. ");
				System.out.println("Goodbye! ");
				break;
				
			default:
				System.out.println("Invalid Choice.Please Enter a Number between 1 and 8");
			}
			
			
		}while (choice != 8);
		
		input.close();

	}
// Option 1:
	
	public static void addNewStudent() {
		if (students.size()>=10) {
			System.out.println("The System has reached the Maximum Number of student cannot add a new student");
			return;
		}
		System.out.println("\n    Add New Student     ");
		
		//entar student name
		
		String name;
		while (true) {
			System.out.println("Enter student name:");
			name = toTitelCase(input.nextLine());
			
			if (name.replace("","").length()>=2) {
				break;
			}
			System.out.println("Name must contain at least 2 characters");
		}
		//enter and validate KKU Id
		String kkuId;
		while (true) {
			System.out.println("Enter KKU ID:");
			kkuId= input.nextLine().trim();
			if (!kkuId.matches("4\\d{8}")) {
				System.out.println("Invalid KKU ID The ID must  contain 9 digits and start with 4.");
				continue;
			}
			if (findStudent(kkuId) != null) {
				System.out.println("This KKU ID is already registered.");
				continue;
			}
			break;
		}
		//enter student major
		String major;
		while (true) {
			System.out.println("Enter Major:");
			major = toTitelCase(input.nextLine());
			
			if (!major.isEmpty()) {
				break;
			}
		System.out.println("Major cannot be Empty . ");
		
				
			}
		//create new student 
		Student newStudent = new Student(name, kkuId, major);
		students.add(newStudent); 
		System.out.println("Student Added Successfully");
	}
	
	//Option 2 :
	public static void RegisterCourses() {
		System.out.println("\n   Register Courses    ");
		System.out.println("Enter KKU ID:");
		String id = input.nextLine().trim();
		
		//validate id format
		
		if (! id.matches("4\\d{8}")) {
			System.out.println("Invalid KKU ID . The ID must  contain 9 digits and start with 4 ");
			return;
		}
		// find student 
		Student student = findStudent(id);
		if (student == null) {
			System.out.println("Student not found.");
			return;
		}
		//Enter Number of courses
		System.out.println("Enter Number Of Courses");
		int NumberOfCourses;
		
		try {
			NumberOfCourses =
					Integer.parseInt(input.nextLine().trim());
		} catch (NumberFormatException e ) {
			System.out.println("Invalid Number of courses A student can register between 1 and 5 courses.");
			return;
		}
		//Validate courses Number 
		if(NumberOfCourses < 1|| NumberOfCourses > 5 || student.getCourses().size() + NumberOfCourses > 5 ) {
			System.out.println("Invalid Number of courses A student can register between 1 and 5 courses.");
			return;	
		}
		int registered = 0;
		
		// Enter courses 
		
		while(registered < NumberOfCourses ) {
			System.out.println(" Enter Course Name: ");
			String course = input.nextLine().trim();
			
			if (course.isEmpty()) {
				System.out.println(" course name cannot be empty! ");
				continue;
				
			}
			if (hasCourseIgnoreCase(student, course)) {
				System.out.println(" Course already registered");
				continue;	
			}
			student.addCourse(course);
			registered++;
		}
		
		System.out.println("Course registered Successfully! ");
		
	}
	
	//Option:3
	public static void registerGraduationProject() {
		System.out.println("\n   Register Graduation Project    ");
		System.out.println("Enter KKU ID:");
		String id = input.nextLine().trim();
		
		//validate ID 
		if (!id.matches("4\\d{8}")) {
			System.out.println("Invalid KKU ID . The ID must  contain 9 digits and start with 4 ");
			return;
		}
		// find student 
		Student student = findStudent(id);
		if (student == null) {
			System.out.println("Student not found.");
			return;
		}
		//Enter project title 
		String title;
		while (true) {
			System.out.println("Enter Project title");
			title = input.nextLine().trim()	;
			if (! title.isEmpty()) {
				break;
			}
			System.out.println("Project Title cannot be Empty ");
		}
		// available Project fields 
		String[] fields = {"Software Development","ArtiFicial Intelligence","Cybersecurity","Data Science","NetWorking"
				};
		System.out.println("\nChoose project field:");
		for (int i = 0; i < fields.length; i++) {
			System.out.println((i + 1)+ "." + fields[i]);
		}
		// select project field
		int fieldChoice;
		
		while (true) {
			System.out.println("Enter field number (1-5):");
			
			try {fieldChoice = 
					Integer.parseInt(input.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println(" Invalid field choice . please choose from 1 to 5.");
				continue;
				
			}
			if (fieldChoice >= 1 &&  fieldChoice <= 5 ) {
				break;
			}
			System.out.println("Invalid field choice . please choose from 1 to 5.");
		}
		String field = fields[fieldChoice - 1 ];
		
		student.setProject(title, field);
		System.out.println("Graduation project registered Successfully!");
		
		}
	//find student using KKU ID
	
	public static Student findStudent(String id) {
		for (Student student : students) {
			if (student.getKkuld().equals(id)) {
				return student;
			}
		}
		return null;	
	}
	// cheek duplicate courses 
	
	public static boolean hasCourseIgnoreCase(Student student, String course) {
		for (String existingCourse :student.getCourses() ) {
			if (existingCourse.equalsIgnoreCase(course)) {
				return true;
			}
		}
		return false;
	}
	// convert Text to titel Case 
	public static String toTitelCase (String text ) {
		text = text.trim().replaceAll("\\s+"," ");
		if (text.isEmpty()) {
			return "";
		}
		String [] words = text.toLowerCase().split(" ");
		String result = " ";
		for(String word : words) {
			result += word.substring(0,1).toUpperCase()
					+ word.substring(1)+ " ";
		}
		return result.trim();
	}
	
	}
	
	
	
	
	
	
	
	
	
	
	


		
	

	
