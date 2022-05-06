package system.service;

import system.exceptions.ClassFullException;
import system.exceptions.InvalidDataException;
import system.persistence.Class;
import system.domain.*;

public class ClassService {
    private Class classRepo;
    public ClassService(char classId){
        
        classRepo = new Class(classId);
    }
    public void registerStudent(String name, char classId) throws InvalidDataException{
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Invalid name");
        }
        Student student = new Student(name, classId);
        try{
            classRepo.add(student);
        }catch (ClassFullException e){
            //
        };
    }

    public void deleteStudent(int index){
        System.out.println("Student " + classRepo.get(index) + " will be deleted");
        classRepo.delete(index);
    }

    public char getId(){
        return classRepo.getId();
    }
    public void listStudent(){
        classRepo.listClass();
    }
    @Override
    public String toString() {
        return "" + classRepo;
    }

    
}
