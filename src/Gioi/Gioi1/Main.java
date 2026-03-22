package Gioi.Gioi1;

import BTTH.dao.MedicalAppointmentDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Statement sẽ trả về số dòng dã bị thay đổi nếu lớn hơn không thì có nghĩa tìm thấy một phần từ và cập nhật thành công


        Connection connection = null;
        Statement statement = null;

        try {
            connection = MedicalAppointmentDB.openConnection();
            statement = connection.createStatement();

            System.out.println("Nhập id cần cập nhật");
            String id = scanner.nextLine();

            String sql = "update Beds set bed_status = 'Occupied' where bed_id = " + id;

            int i = statement.executeUpdate(sql);

            if (i > 0){
                System.out.println("Cập nhật thành công");
            }else {
                System.out.println("không tìm thấy");
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
