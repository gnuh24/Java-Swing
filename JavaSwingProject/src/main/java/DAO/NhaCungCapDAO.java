package DAO;

import DTO.NghiepVuNhapKho.NhaCungCapDTO;
import Others.JDBCConfigure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO implements DAOInterface<NhaCungCapDTO>{

    @Override
    public List<NhaCungCapDTO> getAll(Integer maKhoHang) {
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

    @Override
    public NhaCungCapDTO getById(Integer id) {
        NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO();
        try{
            JDBCConfigure.getConnection();
            String query = "SELECT * FROM `NhaCungCap` " +
                            "WHERE `MaNCC` = ?";

            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Integer maNCC = resultSet.getInt("MaNCC");
                String tenNCC = resultSet.getString("TenNCC");
                String email = resultSet.getString("Email");
                String sdt = resultSet.getString("SoDienThoai");

                nhaCungCapDTO.setMaNCC(maNCC);
                nhaCungCapDTO.setTenNCC(tenNCC);
                nhaCungCapDTO.setEmail(email);
                nhaCungCapDTO.setSoDienThoai(sdt);

            }

        }
        catch (SQLException e){
            System.err.println("Lỗi truy vấn !!");
        }
        finally {
            JDBCConfigure.closeConnection();
        }

        return nhaCungCapDTO;
    }

    @Override
    public boolean create(Integer maKhoHang, NhaCungCapDTO nhaCungCapDTO) {

        String createNhaCungCapQuery =
            "INSERT INTO `NhaCungCap` (`TenNCC`, `SoDienThoai`, `Email`, `MaKhoHang`) VALUES" +
                                     "(?       ,    ?          ,    ?  ,        ?   )";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(createNhaCungCapQuery);
            preparedStatement.setString(1, nhaCungCapDTO.getTenNCC());
            preparedStatement.setString(2, nhaCungCapDTO.getSoDienThoai());
            preparedStatement.setString(3, nhaCungCapDTO.getEmail());
            preparedStatement.setInt   (4, maKhoHang);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    @Override
    public boolean update(NhaCungCapDTO nhaCungCapDTO) {
        return true;
    }

    @Override
    public boolean delete(NhaCungCapDTO nhaCungCapDTO) {
        return true;
    }

    public static void main(String[] args) {
        NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
        System.err.println(nhaCungCapDAO.getById(1).getTenNCC());
    }


}
