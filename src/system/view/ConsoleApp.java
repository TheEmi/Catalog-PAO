package system.view;

import java.util.Scanner;

import system.exceptions.InvalidDataException;
import system.service.ClassService;
import system.service.HandleCSV;

public class ConsoleApp {
    private Scanner s = new Scanner(System.in);
    private static ClassService[] classes = new ClassService[10];
    private static HandleCSV csvs = new HandleCSV();
    //add service path

    public static void main(String args[]) {
        ConsoleApp app = new ConsoleApp();
        classes = csvs.loadCSV();
        csvs.logCSV("logged in");
        while (true) {
            app.showMenu();
            int option = app.readOption();
            app.execute(option);
            csvs.writeCSV(classes);
        }
    }

    private void showMenu() {
        System.out.println
                ("Catalog");
        System.out.println("What do you want to do?");
        System.out.println("1. Create class");
        System.out.println("2. List classes");
        System.out.println("3. Add student to class");
        System.out.println("4. List Students in class");
        System.out.println("5. Delete student form class");
        System.out.println("6. Exit");
    }

    private int readOption() {
        try {
            int option = readInt();
            if (option >= 1 && option <= 6) {
                return option;
            }
        } catch (InvalidDataException invalid) {
            // nothing to do, as it's handled below
        }
        System.out.println("Invalid option. Try again");
        return readOption();
    }

    private void execute(int option) {
        switch (option) {
            case 1:
                addClass();
                break;
            case 2:
                listClasses();
                break;
            case 3:
                addStudent();
                break;
            case 4:
                listStudents();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                csvs.logCSV("logged out");
                System.exit(0);

        }
    }

    private int readInt() throws InvalidDataException {
        String line = s.nextLine();
        if (line.matches("^\\d+$")) {
            return Integer.parseInt(line);
        } else {
            throw new InvalidDataException("Invalid number");
        }
    }
    
    private void deleteStudent(){
        listClasses();
        System.out.println("Enter the INDEX of the class you want to delete from");
        String number = s.nextLine();
        int nr = Integer.parseInt(number);
        classes[nr-1].listStudent();
        System.out.println("Enter the INDEX of the student you want to delete");
        String numberS = s.nextLine();
        int nrS = Integer.parseInt(numberS);
        csvs.logCSV("Deleted Student "+classes[nr-1].get().get(nrS-1).getName()+" from class "+classes[nr-1].getId());
        classes[nr-1].deleteStudent(nrS-1);
        
    }

    private void listStudents(){
        listClasses();
        System.out.println("Enter the INDEX of the class you want to list");
        String number = s.nextLine();
        int nr = Integer.parseInt(number);
        classes[nr-1].listStudent();
        csvs.logCSV("listed Students in Class "+classes[nr-1].getId());
    }
    private void addStudent(){
        listClasses();
        System.out.println("Enter the INDEX of the class you want to add a student to");
        String number = s.nextLine();
        int nr = Integer.parseInt(number);
        System.out.println("Enter the name of the student");
        String name = s.nextLine();
        try{
            classes[nr-1].registerStudent(name, classes[nr-1].getId());
        }catch (Exception e){};
        csvs.logCSV("added Student "+name+" to class "+classes[nr-1].getId());
    }

    private void listClasses() {
        csvs.logCSV("listed Classes");
        for (int i=0; i<classes.length; i++) {
            if (classes[i] != null) {
                System.out.println( i+1+ ". " + classes[i]);
            }
        }
    }

    private void addClass() {
        System.out.println("Enter the ID (single character) of the class");
        String line = s.nextLine();
        if(line.length() > 1){
            System.out.println("Not a character");
            return;
        }
        char classId = line.charAt(0);
        csvs.logCSV("added Class "+classId);
        for (int i=0; i<classes.length; i++) {
            if (classes[i] == null) {
                classes[i] = new ClassService(classId);
                return;
            }
        }
    }
}
