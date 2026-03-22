package XuatSac.XuatSac1.business;


import XuatSac.XuatSac1.dao.DoctorDAO;
import XuatSac.XuatSac1.model.Doctor;

import java.util.List;

public class DoctorService {
    private DoctorDAO dao = new DoctorDAO();

    public List<Doctor> getAll() {
        if (dao.findAll().isEmpty()){
            System.out.println("Danh sách rỗng");
            return null;
        }
        return dao.findAll();
    }

    public void add(Doctor doctor) {
        dao.insert(doctor);
    }
    public void statistic() {
        dao.statisticSpecialty();
    }
}