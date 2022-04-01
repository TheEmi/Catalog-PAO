package system.domain;

public class Student {
    private String name;
    private char classId;

    public Student(String name, char classId){
        this.name = name;
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getClassId() {
        return classId;
    }

    public void setClassId(char classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return name+"";
    }
    
}
