package project1;

import java.util.ArrayList;

public class Student extends Person {
    private String kkuld;
    private String major;
    private ArrayList<String> courses;
    private String projectTitle;
    private String projectField;
    private TrainingCenter internship;

    public Student(String name, String kkuld, String major) {
        super(name);
        this.kkuld = kkuld;
        this.major = major;
        this.courses = new ArrayList<>();
        this.internship = null;
    }

    public void addCourse(String course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public boolean hasCourse(String course) {
        return courses.contains(course);
    }

    public void setProject(String title, String field) {
        this.projectTitle = title;
        this.projectField = field;
    }

    public boolean hasProject() {
        return projectTitle != null && !projectTitle.isEmpty();
    }

    public void setInternship(TrainingCenter center) {
        this.internship = center;
    }

    public String getKkuld() {
        return kkuld;
    }

    public String getMajor() {
        return major;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public String getProjectField() {
        return projectField;
    }
    
    public String getProjectTitle() {
    	     return projectTitle;
    	
    }

    public TrainingCenter getInternship() {
        return internship;
    }
}