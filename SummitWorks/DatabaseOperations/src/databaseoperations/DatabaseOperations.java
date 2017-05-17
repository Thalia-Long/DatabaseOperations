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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Port 3306. Window Server Name MySQL57. 3310
 *
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
            statement = dbConnection.prepareStatement(insertStatement);
            statement.setLong(1, student.getStudentId());
            statement.setString(2, student.getStudentName());
            statement.setInt(3, student.getAge());

            // execute insert SQL statement
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /*
    Delete Operation
     */
    public void Delete(long id) throws SQLException {
        String deleteStatement = "DELETE FROM STUDENT WHERE ID =" + id + ";";
        try {
            dbConnection = getConnection();
            statement = dbConnection.prepareStatement(deleteStatement);

            // execute insert SQL statement
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /*
    Update Operation
     */
    public void Update(Student student) throws SQLException {
      
        String updateStatement = "UPDATE student SET name = ?, age = ? WHERE ID = " + student.getStudentId() + ";";

        try {
            dbConnection = getConnection();
            statement = dbConnection.prepareStatement(updateStatement);
            statement.setString(1, student.getStudentName());
            statement.setInt(2, student.getAge());

            // execute insert SQL statement
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /*
    DisplayAll Operation
     */
    public void DisplayAll() throws SQLException {
        String displayStatement = "SELECT * FROM student;";

        try {
            dbConnection = getConnection();
            statement = dbConnection.prepareStatement(displayStatement);
            ResultSet rs = statement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getLong("id"));
                    System.out.println(rs.getString("name"));
                    System.out.println(rs.getInt("age"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /*
    Get Operation
     */
    public void Get(long id) throws SQLException {
 String updateStatement = "SELECT name, age FROM student WHERE ID = " + id + ";";

        try {
            dbConnection = getConnection();
            statement = dbConnection.prepareStatement(updateStatement);
            ResultSet rs = statement.executeQuery();
            if(rs != null){
                while(rs.next()){
                    System.out.println(rs.getString("name"));
                    System.out.println(rs.getInt("age"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
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
