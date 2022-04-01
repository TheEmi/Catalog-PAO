package system.domain;

public class Professor extends Subject{
    private String profName;

    public Professor(String subjectName, String profName) {
        super(subjectName);
        this.profName = profName;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }
    
    
    
}
