package project1;

public class TrainingCenter implements Matchable {
    private String name;
    private String city;
    private String field;
    private String[] relatedCourses;
    private int seats;

    public TrainingCenter(String name, String city, String field, String[] relatedCourses, int seats) {
        this.name = name;
        this.city = city;
        this.field = field;
        this.relatedCourses = relatedCourses;
        this.seats = seats;
    }

    @Override
    public int calculateScore(Student student) {
        int score = 0;
   
        if (student.getProjectField() != null && student.getProjectField().equalsIgnoreCase(this.field)) {
            score += 60;
        }

        int courseMatches = 0;
        for (String rc : relatedCourses) {
            if (student.hasCourse(rc)) {
                courseMatches++;
            }
        }
        
        int courseScore = courseMatches * 20;
        if (courseScore > 40) {
            courseScore = 40;
        }
        
        score += courseScore;
        return score;
    }

   
    public boolean hasSeats() {
        return seats > 0;
    }

    public void takeSeat() {
        if (seats > 0) {
            seats--;
        }
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getField() {
        return field;
    }

    public String[] getRelatedCourses() {
        return relatedCourses;
    }

    public int getSeats() {
        return seats;
    }
}