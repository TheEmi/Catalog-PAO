package system.service;
import system.exceptions.InvalidDataException;

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
    public void writeCSV(){}
    public void deleteCSV(){

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
