import java.sql.*;

public class DBConnection {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/jdbc?createDatabaseIfNotExist=true";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin123";



    public static java.sql.Connection openConnection(){
        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL,USERNAME,PASSWORD);


        } catch (ClassNotFoundException e) {
            System.out.println("không biết");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi SQL : kêết nối thất bại");
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        try (
                Connection connection = openConnection(); // Bỏ (Connection) vì không cần thiết
                Statement stm = connection.createStatement();
        ) {
            // Bước 1: Xóa bảng cũ (nếu muốn làm mới mỗi lần chạy)
            stm.execute("DROP TABLE IF EXISTS student");

            // Bước 2: Khai báo câu lệnh tạo bảng
            String sql = """
                    CREATE TABLE student (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    age INT
                )
                """;

            // Bước 3: THỰC THI lệnh tạo bảng (Quan trọng nhất - phải chạy dòng này trước)
           boolean isStatement =  stm.execute(sql);
            System.out.println("Đã tạo bảng student thành công.");

            System.out.println(isStatement);
            // Bước 4: Sau khi bảng đã tồn tại, mới gọi hàm thêm dữ liệu
            insertStudent("Phan Hung", 20);
            insertStudent("Nguyen Van A", 22);
            insertStudent("Mai Thi B", 19);

            displayStudents();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertStudent(String name, int age) {
        // 1. Lấy kết nối
        Connection conn = openConnection();
        if (conn == null) return;

        // 2. Câu lệnh SQL (Bỏ id vì nó tự tăng)
        String sql = "INSERT INTO student (name, age) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 3. Truyền tham số vào dấu ?
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // 4. Thực thi
            int row = pstmt.executeUpdate();
            if (row > 0) {
                System.out.println("Thêm sinh viên " + name + " thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close(); // Luôn đóng kết nối sau khi dùng xong
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void displayStudents() {
        String sql = "SELECT * FROM student";

        // Sử dụng try-with-resources để tự động đóng kết nối
        try (Connection conn = openConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {

            System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
            System.out.printf("%-5s | %-20s | %-5s\n", "ID", "Tên", "Tuổi");
            System.out.println("-----------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.printf("%-5d | %-20s | %-5d\n", id, name, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
