package BTTH.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        public static final String URL = "jdbc:mysql://localhost:3306/MedicalAppointmentDB?createDatabaseIfNotExist=true";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin123";


    public static java.sql.Connection openConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);


            return DriverManager.getConnection(URL, USERNAME, PASSWORD);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
