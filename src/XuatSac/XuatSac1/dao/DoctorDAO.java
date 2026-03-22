package XuatSac.XuatSac1.dao;

import XuatSac.XuatSac1.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


public class DoctorDAO {

    public List<Doctor> findAll() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";

        try (Connection conn = DBConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Doctor(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("specialty")
                ));
            }

        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách: " + e.getMessage());
        }
        return list;
    }

    public void insert(Doctor doctor) {
        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, doctor.getId());
            ps.setString(2, doctor.getName());
            ps.setString(3, doctor.getSpecialty());

            ps.executeUpdate();
            System.out.println("Thêm thành công!");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Trùng mã bác sĩ!");
        } catch (Exception e) {
            System.out.println("Lỗi thêm: " + e.getMessage());
        }
    }

    public void statisticSpecialty() {
        String sql = "SELECT specialty, COUNT(*) as total FROM Doctors GROUP BY specialty";

        try (Connection conn = DBConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("=== Thống kê chuyên khoa ===");
            while (rs.next()) {
                System.out.println(rs.getString("specialty") + " - " + rs.getInt("total"));
            }

        } catch (Exception e) {
            System.out.println("Lỗi thống kê: " + e.getMessage());
        }
    }
}