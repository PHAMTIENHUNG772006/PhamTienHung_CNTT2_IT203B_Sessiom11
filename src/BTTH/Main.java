package BTTH;

import BTTH.dao.MedicalAppointmentDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = MedicalAppointmentDB.openConnection();
            statement = connection.createStatement();

            String sql = "SELECT * FROM student";
            boolean isStatement = statement.execute(sql);

            if (isStatement) {
                System.out.println("Kết nối thành công : Phạm Tiến Hưng");
            } else {
                System.out.println("Kết nối thất bại");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Đã đóng kết nối");
        }
    }
}