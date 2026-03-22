package BTTH.presentation;

import BTTH.modal.Appointment;
import BTTH.service.AppointmentRepository;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AppointmentRepository repo = new AppointmentRepository();
        int choice = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm lịch khám");
            System.out.println("2. Cập nhật lịch khám");
            System.out.println("3. Xóa lịch khám");
            System.out.println("4. Tìm theo ID");
            System.out.println("5. Xem tất cả");
            System.out.println("6. Thoát");

            try {
                System.out.print("Chọn: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Tên bệnh nhân: ");
                        String name = sc.nextLine();

                        System.out.print("Ngày khám (yyyy-mm-dd): ");
                        Date date = Date.valueOf(sc.nextLine());

                        System.out.print("Tên bác sĩ: ");
                        String doctor = sc.nextLine();

                        System.out.print("Trạng thái: ");
                        String status = sc.nextLine();

                        repo.addAppointment(new Appointment(name, date, doctor, status));
                    } catch (Exception e) {
                        System.out.println("Dữ liệu không hợp lệ");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Nhập ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Tên bệnh nhân: ");
                        String name = sc.nextLine();

                        System.out.print("Ngày khám : ");
                        Date date = Date.valueOf(sc.nextLine());

                        System.out.print("Tên bác sĩ: ");
                        String doctor = sc.nextLine();

                        System.out.print("Trạng thái: ");
                        String status = sc.nextLine();

                        repo.updateAppointment(new Appointment(id, name, date, doctor, status));
                    } catch (Exception e) {
                        System.out.println("Dữ liệu không hợp lệ");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Nhập ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        repo.deleteAppointment(id);
                    } catch (Exception e) {
                        System.out.println("ID không hợp lệ");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Nhập ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Appointment a = repo.getAppointmentById(id);

                        if (a == null) {
                            System.out.println("Không tìm thấy");
                        } else {
                            System.out.println(
                                    a.getId() + " | " +
                                            a.getPatientName() + " | " +
                                            a.getAppointmentDate() + " | " +
                                            a.getDoctorName() + " | " +
                                            a.getStatus()
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Dữ liệu không hợp lệ");
                    }
                    break;

                case 5:
                    List<Appointment> list = repo.getAllAppointments();

                    if (list.isEmpty()) {
                        System.out.println("Danh sách rỗng");
                    } else {
                        list.forEach(a -> System.out.println(
                                a.getId() + " | " +
                                        a.getPatientName() + " | " +
                                        a.getAppointmentDate() + " | " +
                                        a.getDoctorName() + " | " +
                                        a.getStatus()
                        ));
                    }
                    break;

                case 6:
                    System.out.println("Kết thúc");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 6);
    }
}