package project1;
import java.util.*;

public class InternshipSystem {
	
	static Scanner input = new Scanner (System.in);
	static ArrayList<Student>students = new ArrayList<>();
	static ArrayList<TrainingCenter> centers = new ArrayList<>();

	public static void main(String[] args) {
		int choice = 0 ;
		initializeCenters();
		
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
				findMatchingCenters();
				break;
			case 5:
				assignInternship();
				break;
			case 6:
				searchStudent();
				break;
			case 7:
				displayAllStudents();
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
			System.out.println("The System has reached the maximum number of students. Cannot add a new student.");
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
				System.out.println("Invalid KKU ID. The ID must  contain 9 digits and start with 4.");
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
		Person person = newStudent;
		System.out.println("Student Name: " + person.getName());
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
			System.out.println("Invalid Number of courses. A student can register between 1 and 5 courses.");
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
	
	//Option 3:
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
	// check duplicate courses 
	
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
	// adding the five training centers
	public static void initializeCenters() {
		
		centers.add(new TrainingCenter(
				"Asir Software House",
				"Abha",
				"Software Development",
				new String[] {
						"Java Programming",
						"Data Structures",
						"Web Development",
						"Database Systems"
				},
				3
		));
		centers.add(new TrainingCenter(
				"Smart Vision AI Lab",
				"Riyadh",
				"Artificial Intelligence",
				new String[] {
						"Artificial Intelligence",
						"Machine Learning",
						"Python Programming"
				
				},
				2
		));
		centers.add(new TrainingCenter(
				"Secure Gate Cyber Center",
				"Jeddah",
				"Cybersecurity",
				new String[] {
						"Network Security",
						"Cryptography",
						"Operating Systems"
				},
				2
		));
		centers.add(new TrainingCenter(
				"DataWave Analytics",
				"Khamis Mushait",
				"Data Science",
				new String[] {
						"Database Systems",
						"Statistics",
						"Python Programming",
						"Machine Learning"
				},
				3
		));
		centers.add(new TrainingCenter(
				"NetLink Telecom",
				"Dammam",
				"Networking",
				new String[] {
						"Computer Networks",
						"Operating Systems",
						"Network Security"
				},
				2
		));
				
	}
	public static TrainingCenter getBestMatch(Student student) {
		TrainingCenter bestCenter = null;
		int bestScore = -1;
		
		for (TrainingCenter center : centers) {
			if (center.hasSeats()) {
				int score = center.calculateScore(student);
				
				if (score > bestScore) {
					bestScore = score;
					bestCenter = center;
				}
			}
		}
		return bestCenter;
	}
	
	// Option 4:
	public static void findMatchingCenters() {
		System.out.println("\nFind Matching Training Centers");
		System.out.println("Enter KKU ID:");
		
		String id = input.nextLine().trim();
		
		if (!id.matches("4\\d{8}")) {
			System.out.println("Invalid KKU ID. The ID must contain 9 digits and start with 4.");
			return;
		}
		
		Student student = findStudent(id);
		
		if (student == null) {
			System.out.println("Student not found.");
			return;
		}
		if (student.getCourses().isEmpty()) {
			System.out.println("No courses have been registered for this student. Please register courses first.");
			return;
		}
		
		if (!student.hasProject()) {
			System.out.println("No graduation project has been registered for this student, Please register the graduation project first.");
			return;
		}
	
	int bestScore = -1;
	TrainingCenter bestCenter = null;
	
	System.out.println("\nTraining Centers Matching Results:");
	System.out.printf("%-28s %-20s %-25s %-10s%n", "Center Name", "City", "Field", "Score");
	
	for (TrainingCenter center : centers) {
		int score = center.calculateScore(student);
		
		System.out.printf("%-28s %-20s %-25s %d%%%n",
				center.getName(), center.getCity(), center.getField(), score);
		
		if (score > bestScore) {
			bestScore = score;
			bestCenter = center;
		}
	}
	
	if (bestCenter != null) {
		System.out.println("\nBest Match: " + bestCenter.getName());
		System.out.println("Matching Score: " + bestScore + "%");
	}
	
	else {
		System.out.println("No matching training center found.");
	}
	}
	//Option 5: 
	public static void assignInternship() {
		System.out.println("\nAssign Internship");
		System.out.println("Enter KKU ID:");
		
		String id = input.nextLine().trim();
		
		if (!id.matches("4\\d{8}")) {
			System.out.println("Invalid KKU ID. The ID must contain 9 digits and start with 4.");
			return;
		}
		
		Student student = findStudent(id);
		
		if (student == null) {
			System.out.println("Student not found");
			return;
		}
		if (student.getCourses().isEmpty()) {
			System.out.println("No courses have been registered for this student. Please register courses first.");
			return;
		}
		
		if (!student.hasProject()) {
			System.out.println("No graduation project has been registered for this student. Please register the graduation project first.");
			return;
		}
		
		if (student.getInternship() != null) {
			System.out.println("This student is already assigned to " + student.getInternship().getName() + ".");
			return;
		}
		TrainingCenter bestCenter = getBestMatch(student);
		int bestScore = bestCenter != null ? bestCenter.calculateScore(student) : -1;
		
	if (bestCenter != null) {
		student.setInternship(bestCenter);
		bestCenter.takeSeat();
		
		System.out.println("Internship assigned successfully.");
		System.out.println("Center: " + bestCenter.getName());
		System.out.println("City: " + bestCenter.getCity());
		System.out.println("Score: " + bestScore + "%");
		}
	else {
		System.out.println("No training centers have available seats.");
	}
  }
	// Option 6:
	public static void searchStudent () {
		System.out.println("\nSearch Student");
		System.out.println("Enter KKU ID:");
		
		String id = input.nextLine().trim();
		
		if (!id.matches("4\\d{8}")) {
			System.out.println("Invalid KKU ID. The ID must contain 9 digits and start with 4.");
			return;
		}
		
		Student student = findStudent(id);
		
		if (student == null) {
			System.out.println("Student not found.");
			return;
		}
		System.out.println("\nStudent Information");
		System.out.println("Name: " + student.getName());
		System.out.println("KKU ID: " + student.getKkuld());
		System.out.println("Major: " + student.getMajor());
		
		System.out.println("Courses:");
		if (student.getCourses().isEmpty()) {
			System.out.println("No courses registered.");
		}
		else {
			for (String course : student.getCourses()) {
				System.out.println("-" + course);
			}
		}
		
		if (student.hasProject()) {
			System.out.println("Graduation Project Title: " + student.getProjectTitle());
			System.out.println("Graduation Project Field: " + student.getProjectField());
		}
		else {
			System.out.println("No graduation project registered.");
		}
		
		if (student.getInternship() != null) {
			System.out.println("Internship Center: " + student.getInternship().getName());
			System.out.println("City: " + student.getInternship().getCity());
		}
		else {
			System.out.println("No internship assigned.");
		}
	}
	//Option 7:
	public static void displayAllStudents() {
		System.out.println("\nDisplay All Students");
		
		if (students.isEmpty()) {
			System.out.println("No students registered.");
			return;	
		}
		
		System.out.printf("%-25s %-15s %-25s%n", "Name", "KKU ID", "Major");
		System.out.println("-------------------------------------------------------------------------------");
		
		for (Student student : students) {
			System.out.printf("%-25s %-15s %-25s%n", student.getName(), student.getKkuld(), student.getMajor());
		}
		int assigned = 0;
		int notAssigned = 0;
		
		for (Student student : students) {
			if (student.getInternship() != null) {
				assigned++;
			}
			else {
				notAssigned++;
			}
		}
		
		System.out.println("\nStudent Statistics:");
		System.out.println("Total Students: " + students.size());
		System.out.println("Assigned Students: " + assigned);
		System.out.println("Not Assigned Students: " + notAssigned);
	}
}



	



	
	
	
	
	
	
	
	
	
	
	


		
	

	
