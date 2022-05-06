package system.service;
import system.exceptions.InvalidDataException;
import system.domain.Student;
import java.io.*;
import java.nio.file.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;  

public class HandleCSV {
    public ClassService[] loadCSV(){
        ClassService[] classes = new ClassService[10];
        String[] reader;
        try{
            Scanner sc = new Scanner(new File("src/system/CSVs/classes.csv")).useDelimiter("\n");
            while (sc.hasNext()){  //read classes
                reader = sc.next().toString().split(",");
                classes[Integer.parseInt(reader[0])-1] = new ClassService(reader[1].charAt(0));
            }
            sc = new Scanner(new File("src/system/CSVs/students.csv")).useDelimiter("\n");
            while (sc.hasNext()){  //read students
                reader = sc.next().toString().split(",");                
                for (int i=0; i<classes.length; i++) {
                    if (classes[i].getId() == reader[2].charAt(0)) {
                        try{
                        classes[i].registerStudent(reader[1], reader[2].charAt(0));
                        break;
                        }
                        catch (InvalidDataException e){
                            System.out.println(reader[2]);
                        };
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        };
        return classes;
    }
    public void writeCSV(ClassService[] classes){
        try{
        FileWriter cla = new FileWriter("src/system/CSVs/classes.csv");
        FileWriter stu = new FileWriter("src/system/CSVs/students.csv");
        
        for (int i=0; i<classes.length; i++) {
            if(classes[i]!=null){
                cla.append(Integer.toString(i+1));
                cla.append(',');
                cla.append(classes[i].getId());
                cla.append("\n");
                for (int j=0; j<classes[i].get().getSize(); j++) {
                    
                    if(classes[i].get().get(j) != null){
                        
                    Student s = classes[i].get().get(j);
                    stu.append(Integer.toString(j+1));
                    stu.append(',');
                    stu.append(s.getName());
                    stu.append(',');
                    stu.append(s.getClassId());
                    stu.append('\n');
                    }
                }
            }
        }
        
        cla.close();
        stu.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void logCSV(String action){//much easier with Log4j....
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date); 
        try{
            StringBuilder sb = new StringBuilder();
            sb.append(action);
            sb.append(", ");
            sb.append(strDate);
            sb.append("\n");
            Files.write(Paths.get("src/system/CSVs/log.csv"), sb.toString().getBytes(), StandardOpenOption.APPEND);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
