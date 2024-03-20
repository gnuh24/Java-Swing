package org.example.Configure;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConfigure {
    public static Connection connection = null;

    public static Connection getConnection() {
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\Tuan Hung\\Desktop\\Learning\\Exercise\\SGU Java Swing - Ms Loan\\Java-Swing\\JavaSwingProject\\src\\main\\java\\org\\example\\Configure\\mysql.properties"));
            String username= properties.getProperty("user");
            String password= properties.getProperty("password");
            String url = properties.getProperty("url");
//            String driver = properties.getProperty("driver");
//            Class.forName(driver);

            connection = DriverManager.getConnection(url,username,password);
            if (connection != null){
                System.out.println("Kết nối Database  Thành Công !!!");

            }
            else {
                System.out.println("Kết nối Database Không Thành Công !! Mời Kiểm Tra Lại");
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return connection;
    }
    public static void closeConnection(){
        if (connection!=null){
            try{
                connection.close();
                System.out.println("Connection has been closed!!");
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        JDBCConfigure.getConnection();
    }
}