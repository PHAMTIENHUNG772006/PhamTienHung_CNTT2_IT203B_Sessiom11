package BTTH.service;


import BTTH.dao.DatabaseConnection;
import BTTH.modal.Appointment;

import java.sql.*;
import java.util.*;

public class AppointmentRepository {

    public void addAppointment(Appointment a) {
        String sql = "INSERT INTO appointments(patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getPatientName());
            ps.setDate(2, a.getAppointmentDate());
            ps.setString(3, a.getDoctorName());
            ps.setString(4, a.getStatus());

            ps.executeUpdate();
            System.out.println("Thêm thành công");

        } catch (Exception e) {
            System.out.println("lỗi thêm: " + e.getMessage());
        }
    }

    public void updateAppointment(Appointment a) {
        String sql = "UPDATE appointments SET patient_name=?, appointment_date=?, doctor_name=?, status=? WHERE id=?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getPatientName());
            ps.setDate(2, a.getAppointmentDate());
            ps.setString(3, a.getDoctorName());
            ps.setString(4, a.getStatus());
            ps.setInt(5, a.getId());

            ps.executeUpdate();
            System.out.println("Cập nhật thành công");

        } catch (Exception e) {
            System.out.println("Lỗi update: " + e.getMessage());
        }
    }

    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id=?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Xóa thành công");

        } catch (Exception e) {
            System.out.println("lỗi xóa: " + e.getMessage());
        }
    }

    public Appointment getAppointmentById(int id) {
        String sql = "SELECT * FROM appointments WHERE id=?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            System.out.println("lỗi tìm: " + e.getMessage());
        }
        return null;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                ));
            }

        } catch (Exception e) {
            System.out.println("lỗi lấy danh sách: " + e.getMessage());
        }
        return list;
    }
}
