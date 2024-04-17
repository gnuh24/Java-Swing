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
                Integer maKhoHang = resultSet.getInt("MaKhoHang");


                nhaCungCapDTO.setMaNCC(maNCC);
                nhaCungCapDTO.setTenNCC(tenNCC);
                nhaCungCapDTO.setEmail(email);
                nhaCungCapDTO.setSoDienThoai(sdt);
                nhaCungCapDTO.setMaKhoHang(maKhoHang);

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

    public boolean isTenNCCExists(String tenNCC){
        String createNhaCungCapQuery =
            "SELECT * FROM `NhaCungCap`" +
                "WHERE `TenNCC` = ? ;";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(createNhaCungCapQuery);
            preparedStatement.setString(1, tenNCC);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JDBCConfigure.closeConnection();
        }
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

            preparedStatement.executeUpdate();
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
        String updateNhaCungCapQuery = "UPDATE `NhaCungCap` SET " +
            "`TenNCC` = ?, `SoDienThoai` = ?, `Email` = ?, `MaKhoHang` = ? WHERE `MaNCC` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(updateNhaCungCapQuery);
            preparedStatement.setString(1, nhaCungCapDTO.getTenNCC());
            preparedStatement.setString(2, nhaCungCapDTO.getSoDienThoai());
            preparedStatement.setString(3, nhaCungCapDTO.getEmail());
            preparedStatement.setInt(4, nhaCungCapDTO.getMaKhoHang());
            preparedStatement.setInt(5, nhaCungCapDTO.getMaNCC());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }
    }

    @Override
    public boolean delete(NhaCungCapDTO nhaCungCapDTO) {
        String deleteNhaCungCapQuery = "DELETE FROM `NhaCungCap` WHERE `MaNCC` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(deleteNhaCungCapQuery);
            preparedStatement.setInt(1, nhaCungCapDTO.getMaNCC());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }
    }

    public static void main(String[] args) {
        NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapDAO.getById(3);

//        nhaCungCapDTO.setTenNCC("Công ty TNHH 1 thành THug88");
//        System.err.println(nhaCungCapDTO.getMaKhoHang());
//        nhaCungCapDAO.update(nhaCungCapDTO);


        System.err.println(nhaCungCapDAO.isTenNCCExists("4 chàng lính ngự lâm !!"));

        System.err.println(nhaCungCapDAO.isTenNCCExists("Công ty TNHH 1 thành THug88"));


        nhaCungCapDAO.delete(nhaCungCapDTO);
    }


}
