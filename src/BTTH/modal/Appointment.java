package BTTH.modal;

import java.sql.Date;

public class Appointment {
    private int id;
    private String patientName;
    private Date appointmentDate;
    private String doctorName;
    private String status;

    public Appointment() {}

    public Appointment(int id, String patientName, Date appointmentDate, String doctorName, String status) {
        this.id = id;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
        this.status = status;
    }

    public Appointment(String patientName, Date appointmentDate, String doctorName, String status) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
        this.status = status;
    }

    public int getId() { return id; }
    public String getPatientName() { return patientName; }
    public Date getAppointmentDate() { return appointmentDate; }
    public String getDoctorName() { return doctorName; }
    public String getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public void setStatus(String status) { this.status = status; }
}