package org.example.DAO;

import org.example.DTO.NghiepVuNhapKho.NhaCungCapDTO;
import org.example.Others.JDBCConfigure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {

    public List<NhaCungCapDTO> getAllNhaCungCap() {
        List<NhaCungCapDTO> danhSachNhaCungCap = new ArrayList<>();

        try{
            JDBCConfigure.getConnection();
            Statement statement = JDBCConfigure.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `NhaCungCap`");

            while (resultSet.next()){
                Integer maNCC = resultSet.getInt("MaNCC");
                String tenNCC = resultSet.getString("TenNCC");
                String email = resultSet.getString("Email");
                String sdt = resultSet.getString("SoDienThoai");

                NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO();
                nhaCungCapDTO.setMaNCC(maNCC);
                nhaCungCapDTO.setTenNCC(tenNCC);
                nhaCungCapDTO.setEmail(email);
                nhaCungCapDTO.setSoDienThoai(sdt);

                danhSachNhaCungCap.add(nhaCungCapDTO);

            }

        }
        catch (SQLException e){
            System.err.println("Lỗi truy vấn !!");
        }
        finally {
            JDBCConfigure.closeConnection();
        }

        return danhSachNhaCungCap;
    }

    public static void main(String[] args) {
        NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
        for (NhaCungCapDTO dto: nhaCungCapDAO.getAllNhaCungCap()) {
            System.err.println(dto.getMaNCC());
            System.err.println(dto.getTenNCC());
            System.err.println(dto.getEmail());
            System.err.println(dto.getSoDienThoai());



        }

    }

}
