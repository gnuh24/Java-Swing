package DAO.NghiepVuNhapKho;

import DAO.DAOInterface;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;
import Others.JDBCConfigure;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapKhoDAO implements DAOInterface<PhieuNhapKhoDTO> {

    @Override
    public List<PhieuNhapKhoDTO> getAll(Integer maKhoHang) {
        List<PhieuNhapKhoDTO> danhSachPhieuNhapKho = new ArrayList<>();

        try {
            JDBCConfigure.getConnection();
            Statement statement = JDBCConfigure.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `PhieuNhapKho`");

            while (resultSet.next()) {
                Integer maPhieu = resultSet.getInt("MaPhieu");
                String ngayNhapKho = resultSet.getString("NgayNhapKho");
                Long tongGiaTri = resultSet.getLong("TongGiaTri");
                Integer maNCC = resultSet.getInt("MaNCC");
                Integer maKhoHangPhieu = resultSet.getInt("MaKhoHang");

                PhieuNhapKhoDTO phieuNhapKhoDTO = new PhieuNhapKhoDTO();
                phieuNhapKhoDTO.setMaPhieu(maPhieu);
                phieuNhapKhoDTO.setNgayNhapKho(ngayNhapKho);
                phieuNhapKhoDTO.setTongGiaTri(tongGiaTri);
                phieuNhapKhoDTO.setMaNCC(maNCC);
                phieuNhapKhoDTO.setMaKhoHang(maKhoHangPhieu);

                danhSachPhieuNhapKho.add(phieuNhapKhoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi truy vấn!!");
        } finally {
            JDBCConfigure.closeConnection();
        }

        return danhSachPhieuNhapKho;
    }

    @Override
    public PhieuNhapKhoDTO getById(Integer id) {
        PhieuNhapKhoDTO phieuNhapKhoDTO = new PhieuNhapKhoDTO();

        try {
            JDBCConfigure.getConnection();
            String query = "SELECT * FROM `PhieuNhapKho` WHERE `MaPhieu` = ?";

            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer maPhieu = resultSet.getInt("MaPhieu");
                String ngayNhapKho = resultSet.getString("NgayNhapKho");
                Long tongGiaTri = resultSet.getLong("TongGiaTri");
                Integer maNCC = resultSet.getInt("MaNCC");
                Integer maKhoHangPhieu = resultSet.getInt("MaKhoHang");

                phieuNhapKhoDTO.setMaPhieu(maPhieu);
                phieuNhapKhoDTO.setNgayNhapKho(ngayNhapKho);
                phieuNhapKhoDTO.setTongGiaTri(tongGiaTri);
                phieuNhapKhoDTO.setMaNCC(maNCC);
                phieuNhapKhoDTO.setMaKhoHang(maKhoHangPhieu);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn!!");
        } finally {
            JDBCConfigure.closeConnection();
        }

        return phieuNhapKhoDTO;
    }

    @Override
    public boolean create(Integer maKhoHang, PhieuNhapKhoDTO phieuNhapKhoDTO) {
        String createPhieuNhapKhoQuery = "INSERT INTO `PhieuNhapKho` " +
            "(`NgayNhapKho`, `TongGiaTri`, `MaNCC`, `MaKhoHang`) " +
            "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(createPhieuNhapKhoQuery);

            preparedStatement.setString(1,
                (phieuNhapKhoDTO.getNgayNhapKho() != null) ?
                    phieuNhapKhoDTO.getNgayNhapKho() : LocalDate.now().toString()
            );

            preparedStatement.setLong(2, phieuNhapKhoDTO.getTongGiaTri());
            preparedStatement.setInt(3, phieuNhapKhoDTO.getMaNCC());
            preparedStatement.setInt(4, maKhoHang);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    @Override
    public boolean update(PhieuNhapKhoDTO phieuNhapKhoDTO) {
        String updatePhieuNhapKhoQuery =
            "UPDATE `PhieuNhapKho` SET " +
                "`NgayNhapKho` = ?, " +
                "`TongGiaTri` = ?, " +
                "`MaNCC` = ? " +
                "WHERE `MaPhieu` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(updatePhieuNhapKhoQuery);
            preparedStatement.setString(1, phieuNhapKhoDTO.getNgayNhapKho());
            preparedStatement.setLong(2, phieuNhapKhoDTO.getTongGiaTri());
            preparedStatement.setInt(3, phieuNhapKhoDTO.getMaNCC());
            preparedStatement.setInt(4, phieuNhapKhoDTO.getMaPhieu());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    public boolean updatePhieuNhapKhoByMaNhaCungCap(Integer maNCC) {
        String updatePhieuNhapKhoQuery =
            "UPDATE `PhieuNhapKho` SET " +
                "`MaNCC` = 1 " +
                "WHERE `MaNCC` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(updatePhieuNhapKhoQuery);
            preparedStatement.setInt(1, maNCC);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    @Override
    public boolean delete(PhieuNhapKhoDTO phieuNhapKhoDTO) {
        String deletePhieuNhapKhoQuery = "DELETE FROM `PhieuNhapKho` WHERE `MaPhieu` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(deletePhieuNhapKhoQuery);
            preparedStatement.setInt(1, phieuNhapKhoDTO.getMaPhieu());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }
}