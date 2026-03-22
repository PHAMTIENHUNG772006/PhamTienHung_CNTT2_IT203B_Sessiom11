package Kha.Kha1;

import BTTH.dao.DatabaseConnection;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       /*
       trong một hệ thống nếu không đong kết nối sẽ gây hao tổn tài nguyên ,
       kết nối rơi vào trạng thái treo mà treen các thiết bị khác không thể kết nối đến db
       dễ bị tấn công do các hacker có thể tạo các đoạn mã kết nối giả
       rò rỉ dữ liệu các thông tin nhạy cảm thông qua bộ nhớ hoặc buffer
        */
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();

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