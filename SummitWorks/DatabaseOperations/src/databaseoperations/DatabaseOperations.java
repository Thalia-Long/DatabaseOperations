/*
1. Insert
2. Delete 
3. Update 
4. Display all records
5. Get 
6. Exit. 
 */
package databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Port 3306. Window Server Name MySQL57. 3310
 * @author Hien Long
 */
public class DatabaseOperations {

    private final String DB_DRIVER;
    private final String DB_CONNECTION;
    private final String USER;
    private final String PASSWORD;
    private Connection dbConnection;
    private PreparedStatement statement;

    public DatabaseOperations(String driver, String connection, String user, String password) {
        this.DB_DRIVER = driver;
        this.DB_CONNECTION = connection;
        this.USER = user;
        this.PASSWORD = password;
        dbConnection = null;
    }

    /*
    Insert Operation
     */
    public void Insert(Student student) throws SQLException {

        String insertStatement = "INSERT INTO STUDENT" + "(ID, NAME, AGE) VALUES" + "(?,?,?)";
        try {
            dbConnection = getConnection();
            System.out.println(dbConnection);
            statement = dbConnection.prepareStatement(insertStatement);
            statement.setString(1, student.getStudentName());
            statement.setLong(2, student.getStudentId());
            statement.setInt(3, student.getAge());
           
            // execute insert SQL statement
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            if(statement != null){
                statement.close();
            }
            if(dbConnection != null){
                dbConnection.close();
            }
        }
    }

    /*
    Delete Operation
     */
    public void Delete() {

    }

    /*
    Update Operation
     */
    public void Update() {

    }

    /*
    DisplayAll Operation
     */
    public void DisplayAll() {

    }

    /*
    Get Operation
     */
    public void Get() {

    }

    /*
    Exit
     */
    public void Exit() {

    }

    /*
    Get Connection
     */
    public Connection getConnection() {
     
        try {
            //Register JDBC driver
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            //Open a connection
            dbConnection = DriverManager.getConnection(DB_CONNECTION, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

}
