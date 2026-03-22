package Kha.Kha2;

import BTTH.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // do trong if chỉ chạy một lần điều kiện nếu không thông qua vòng lặp nếu sau khi đã trả về một bản ghi thì vị trí hiện tại vẫn đang ở bản ghi đầu tiên


        // mã nguồn sau khi sửa đổi để duyệt

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();

            String sql = "SELECT * FROM student";
            java.sql.ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                System.out.println("Tên " + resultSet.getString("name"));
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
