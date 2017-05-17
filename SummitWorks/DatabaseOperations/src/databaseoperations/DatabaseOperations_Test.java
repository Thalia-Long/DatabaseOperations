/*
 Implement Following operations as a single application 
1. Insert
2. Delete 
3. Update 
4. Display all records
5. Get 
6. Exit. 
You have to display a menu to the customer, let customer choose the option whatever he wanted to do, 
depending on the customer option, you have to run the corresponding methods.
[Insert a new record in data base table, update the existing record, delete etc on student object]

 */
package databaseoperations;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Hien Long
 */
public class DatabaseOperations_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        final String DB_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_CONNECTION = "jdbc:mysql://localhost:3306/STUDENT";
        final String USER = "root";
        final String PASSWORD = "1490";
        DatabaseOperations operations = new DatabaseOperations(DB_DRIVER, DB_CONNECTION, USER, PASSWORD);
        long id;
        int age;
        String name;
        Student student;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please choose one of these option: "
                + "\n1.Insert"
                + "\n2.Delete"
                + "\n3.Update"
                + "\n4.Display all records"
                + "\n5.Get"
                + "\n6.Exit");
        String input = keyboard.next();
        switch (input) {
            // INSERT
            case "1":
                System.out.println("Student ID: ");
                id = keyboard.nextLong();
                System.out.println("Student name: ");
                name = keyboard.next();
                System.out.println("Student age: ");
                age = keyboard.nextInt();
                student = new Student(id, name, age);
                operations.Insert(student);
                break;
            // DELETE
            case "2":
                System.out.println("Please enter student ID who will be deleted: ");
                id = keyboard.nextLong();
                operations.Delete(id);
                break;

            // UPDATE, assume user wants to update both name, and age. 
            case "3":
                System.out.println("Please enter student id to be updated: ");
                id = keyboard.nextLong();
                System.out.println("Enter the updated name: ");
                name = keyboard.next();
                System.out.println("Enter the updated age: ");
                age = keyboard.nextInt();
                student = new Student(id, name, age);
                operations.Update(student);
                break;

            // DISPLAY ALL RECORDS
            case "4":
                operations.DisplayAll();
                break;
            case "5":
                System.out.println("Please enter the student id: ");
                id = keyboard.nextLong();
                operations.Get(id);
                break;
            case "6":
                System.exit(0);
                break;
        }

    }

}
