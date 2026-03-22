package Gioi.Gioi2;

import BTTH.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // trong đây điều kiện do hacker sửa đổi bằng or 1 = 1 sẽ luôn true
        // nên khi truy vấn sẽ lấy ra toàn bộ thông tin trong sb ra ngoài
        Scanner scanner = new Scanner(System.in);

        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DatabaseConnection.openConnection();
            stmt = connection.createStatement();

            String patientName = scanner.nextLine();
            patientName = sanitizeInput(patientName);

            String sql = "SELECT * FROM Patients WHERE full_name = '" + patientName + "'";
            ResultSet rs = stmt.executeQuery(sql);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
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
    public static String sanitizeInput(String input) {// thêm phương thức thay toàn bộ dấu bằng khoảng trống bằn replaceAll
        return input.replaceAll("[';--]", "");
    }
}
