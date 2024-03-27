package org.example;

import org.example.Others.CloundinaryServices;

import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
//        Statement statement = JDBCConfigure.getConnection().createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM `TaiKhoan`");
//
//
//        while (resultSet.next()) {
//            int maTK = resultSet.getInt("MaTK");
//            String tenDangNhap = resultSet.getString("TenDangNhap");
//            String matKhau = resultSet.getString("MatKhau");
//            boolean trangThai = resultSet.getBoolean("TrangThai");
//            // Lấy giá trị của các cột khác tương tự ở đây
//
//            System.out.println("MaTK: " + maTK + ", TenDangNhap: " + tenDangNhap + ", MatKhau: " + matKhau + ", TrangThai: " + trangThai);
//        }
//
//
//        JDBCConfigure.closeConnection();


//        CloundinaryServices.deleteImage("https://res.cloudinary.com/djhoea2bo/image/upload/v1711510440/JavaSwingProject/mpdswdudevrgxw4wdbjf.png");
        System.err.println(CloundinaryServices.createImage("C:\\Users\\Tuan Hung\\Desktop\\File\\Meme\\22c12220f8e7d892668c7d434f19c3ac.jpg"));



    }
}