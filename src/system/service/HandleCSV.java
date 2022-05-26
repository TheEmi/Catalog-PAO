package system.service;
import system.domain.Student;
import java.io.*;
import java.sql.*;
import java.nio.file.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import java.util.Date;  

public class HandleCSV {
    public ClassService[] loadCSV(){
        ClassService[] classes = new ClassService[10];
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Liceu","root","root");  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from Clase;");
            while(rs.next()){
                classes[rs.getInt(1)-1] = new ClassService(rs.getString(2).charAt(0));
            }  
            rs = stmt.executeQuery("select * from Studenti;");
            while(rs.next()){
                for (int i=0; i<classes.length; i++) {
                    if(classes[i] != null && classes[i].getId() == rs.getString(3).charAt(0) )
                    classes[i].registerStudent(rs.getString(2), rs.getString(3).charAt(0));
                }
                
            } 
            con.close();  
            }catch(Exception e){ System.out.println(e);}  
        
        return classes;
    }
    public void cuClass(char cl, int id){//create class
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Liceu","root","root");  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("INSERT INTO Clase values(" + id + ", \""+ cl + "\");");
            con.close();  
            }catch(Exception e){ System.out.println(e);} 
    }
    public void cuStudent(String Name, char cl, int id){//create class
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Liceu","root","root");  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("INSERT INTO Studenti values(" + id + ", \""+ Name + "\",\""+ cl + "\");");
            con.close();  
            }catch(Exception e){ System.out.println(e);} 
    }
    public void delStudent(int id, char cl){
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Liceu","root","root");  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("Delete from Studenti where clasa =\""+cl+"\" and id = "+id+";");
            stmt.executeUpdate("Update Studenti set id = id-1 where clasa =\""+cl+"\" and id > "+id+";");
            con.close();  
            }catch(Exception e){ System.out.println(e);} 

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
