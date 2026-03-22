package XuatSac.XuatSac1.presentation;
import XuatSac.XuatSac1.business.DoctorService;
import XuatSac.XuatSac1.model.Doctor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoctorService service = new DoctorService();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    List<Doctor> list = service.getAll();
                    list.forEach(d ->
                            System.out.println(d.getId() + " - " + d.getName() + " - " + d.getSpecialty()));
                    break;

                case 2:
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();

                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();

                    System.out.print("Nhập chuyên khoa: ");
                    String specialty = sc.nextLine();

                    service.add(new Doctor(id, name, specialty));
                    break;

                case 3:
                    service.statistic();
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }
}