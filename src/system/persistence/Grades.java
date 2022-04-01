package system.persistence;

import system.domain.*;

public class Grades {
    private Grade[] gradeCollection = new Grade[100];

     public void add(Grade grade) throws Exception{
         //if class is not full (30 students)
        for (int i=0; i<gradeCollection.length; i++) {
            if (gradeCollection[i] == null) {
                gradeCollection[i] = grade;
                return;
            }
        }
        //if class is full throw exception
        throw new Exception("Too many grades");
     }

    public Grade get(int index) {
        return gradeCollection[index];
    }
    public void delete(int index){
        if(index < gradeCollection.length){
            for(int i=index;i<gradeCollection.length-1;i++){
                gradeCollection[i]=gradeCollection[i+1];
            }
            gradeCollection[gradeCollection.length] = null;
        }
    } 

    public int getSize() {
        return gradeCollection.length;
    }
}
