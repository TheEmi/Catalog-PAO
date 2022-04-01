package system.persistence;

import system.domain.*;
import system.exceptions.*;

public class Class {
     private Student[] classCollection = new Student[30];
     private char classId;

    public Class(char classId){
        this.classId = classId;
    }

     public void add(Student student) throws ClassFullException{
         //if class is not full (30 students)
        for (int i=0; i<classCollection.length; i++) {
            if (classCollection[i] == null) {
                classCollection[i] = student;
                return;
            }
        }
        //if class is full throw exception
        throw new ClassFullException("Class Full");
     }

    public Student get(int index) {
        return classCollection[index];
    }
    public void delete(int index){
        if(index < classCollection.length){
            for(int i=index;i<classCollection.length-1;i++){
                classCollection[i]=classCollection[i+1];
            }
            classCollection[classCollection.length-1] = null;
        }
    } 

    public int getSize() {
        return classCollection.length;
    }

    public void listClass(){
        System.out.println("Class ID: " + classId);
        for (int i=0; i<classCollection.length; i++) {
            if(classCollection[i]!=null)
            System.out.println(i+1+ ". "+classCollection[i]);
        }
    }
    
    public char getId(){
        return classId;
    }

    @Override
    public String toString() {
        return "Class Id: " + classId;
    }
     
}
