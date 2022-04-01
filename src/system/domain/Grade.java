package system.domain;

public class Grade extends Subject{
    
    private int grade;
    private String studentName;

    public Grade(String subjectName, int grade, String studentName) {
        super(subjectName);
        this.grade = grade;
        this.studentName = studentName; 
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
}
