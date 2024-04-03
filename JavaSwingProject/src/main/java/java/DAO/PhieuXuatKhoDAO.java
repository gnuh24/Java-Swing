package DAO;

import DTO.NghiepVuXuatKho.ChiTietPhieuXuatKhoDTO;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;
import Others.JDBCConfigure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhieuXuatKhoDAO implements DAOInterface<PhieuXuatKhoDTO>{

//      @Override
//      public List<PhieuXuatKhoDTO> getAll() {
//            List<PhieuXuatKhoDTO> xuatHangList = new ArrayList<>();
//            try {
//                  Statement statement = JDBCConfigure.getConnection().createStatement();
//                  ResultSet resultSet = statement.executeQuery("SELECT * FROM `phieuxuatkho`");
//
//                  while (resultSet.next()) {
//                        PhieuXuatKhoDTO xuatHangObject = new PhieuXuatKhoDTO();
//
//                        xuatHangObject.setMaPhieu(resultSet.getInt("MaPhieu"));
//                        xuatHangObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));
//                        xuatHangObject.setTenKhoHang(resultSet.getString("TenKhoHang"));
//                        xuatHangObject.setTongGiaTri(resultSet.getDouble("TongGiaTri"));
//                        xuatHangObject.setNgayXuatKho(resultSet.getTimestamp("NgayXuatKho"));
//
//                        xuatHangList.add(xuatHangObject);
//                  }
//
//            } catch (SQLException e) {
//                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
//            }
//            return xuatHangList;
//      }

      @Override
      public List<PhieuXuatKhoDTO> getAll(Integer maKhoHang) {
            List<PhieuXuatKhoDTO> phieuXuatHangList = new ArrayList<>();
//            try {
//                  Statement statement = JDBCConfigure.getConnection().createStatement();
//                  ResultSet resultSet = statement.executeQuery("select * from phieuxuatkho, khohang where phieuxuatkho.MaKhoHang = khohang.MaKhoHang;");
//
//                  while (resultSet.next()) {
//                        PhieuXuatKhoDTO phieuXuatHangObject = new PhieuXuatKhoDTO();
//
//                        phieuXuatHangObject.setMaPhieu(resultSet.getInt("MaPhieu"));
//                        phieuXuatHangObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));
//                        phieuXuatHangObject.setTenKhoHang(resultSet.getString("TenKhoHang"));
//                        phieuXuatHangObject.setNgayXuatKho(resultSet.getTimestamp("NgayXuatKho"));
//                        phieuXuatHangObject.setTongGiaTri(resultSet.getDouble("TongGiaTri"));
//
//                        phieuXuatHangList.add(phieuXuatHangObject);
//                  }
//
//            } catch (SQLException e) {
//                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
//            }
            return phieuXuatHangList;
      }

      @Override
      public PhieuXuatKhoDTO getById(Integer id) {
            PhieuXuatKhoDTO chiTietPhieuXuatHang = new PhieuXuatKhoDTO();
//            try {
//                  Statement statement = JDBCConfigure.getConnection().createStatement();
//                  ResultSet chiTietPhieuXuatHangRow = statement.executeQuery(
//                      "select * from phieuxuatkho, ctpxk, khohang " +
//                          "where phieuxuatkho.MaKhoHang = khohang.MaKhoHang " +
//                              "and ctpxk.MaPhieu = phieuxuatkho.MaPhieu " +
//                              "and phieuxuatkho.MaPhieu = " + id);
//                  while(chiTietPhieuXuatHangRow.next()) {
//
//                        chiTietPhieuXuatHang.setMaPhieu(chiTietPhieuXuatHangRow.getInt("MaPhieu"));
//                        chiTietPhieuXuatHang.setMaKhoHang(chiTietPhieuXuatHangRow.getInt("MaKhoHang"));
//                        chiTietPhieuXuatHang.setTenKhoHang(chiTietPhieuXuatHangRow.getString("TenKhoHang"));
//                        chiTietPhieuXuatHang.setMaKhoHang(chiTietPhieuXuatHangRow.getInt("MaKhoHang"));
//                        chiTietPhieuXuatHang.setTenKhoHang(chiTietPhieuXuatHangRow.getString("TenKhoHang"));
//                        chiTietPhieuXuatHang.setNgayXuatKho(chiTietPhieuXuatHangRow.getTimestamp("NgayXuatKho"));
//                        chiTietPhieuXuatHang.setTongGiaTri(chiTietPhieuXuatHangRow.getDouble("TongGiaTri"));
//                  }
//            } catch (SQLException e) {
//                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
//            }
            return chiTietPhieuXuatHang;
      }

      @Override
      public boolean create(Integer maKhoHang, PhieuXuatKhoDTO phieuXuatKhoDTO) {
            try{
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int checkThemPhieuXuatHang = state.executeUpdate("INSERT INTO `phieuxuatkho` (`MaPhieu`, `NgayXuatKho`, `TongGiaTri`, `MaKhoHang`) VALUES (NULL, '2024-04-01 08:09:44.000000', '2670000', '4');");
            }catch(SQLException e) {
                  System.err.println(e.getMessage());
            }
            return true;
      }

      @Override
      public boolean update(PhieuXuatKhoDTO phieuXuatKhoDTO) {
            return true;
      }

      @Override
      public boolean delete(PhieuXuatKhoDTO phieuXuatKhoDTO) {
            return true;
      }

      public List<ChiTietPhieuXuatKhoDTO> getChiTietSanPhamPhieuXuatHang(int maPhieuXuat) {
//             List<SanPhamDTO> chiTietSanPhamPhieuXuatHangList = new ArrayList<>();
//
//             try {
//                   SanPhamDTO sanPhamDTO = new SanPhamDTO();
//                   Statement statement = JDBCConfigure.getConnection().createStatement();
//                   ResultSet chiTietSanPhamPhieuXuatHangRow = statement.executeQuery("select * from phieuxuatkho, sanpham, ctpxk, khohang where phieuxuatkho.MaKhoHang = khohang.MaKhoHang and ctpxk.MaPhieu = phieuxuatkho.MaPhieu and ctpxk.MaSanPham = sanpham.MaSanPham and phieuxuatkho.MaPhieu = " + maPhieuXuat);
//                   while(chiTietSanPhamPhieuXuatHangRow.next()) {
//                         sanPhamDTO.setMaSanPham(chiTietSanPhamPhieuXuatHangRow.getInt("MaSanPham"));
//                         sanPhamDTO.setTenSanPham(chiTietSanPhamPhieuXuatHangRow.getString("TenSanPham"));
//                         sanPhamDTO.setGiaSanPham(chiTietSanPhamPhieuXuatHangRow.getInt("DonGia"));
//                         sanPhamDTO.setSoLuongConLai(chiTietSanPhamPhieuXuatHangRow.getInt("SoLuong"));
//                         sanPhamDTO.setThanhTien(chiTietSanPhamPhieuXuatHangRow.getDouble("ThanhTien"));
//                   }
//             } catch (SQLException e) {
//                   e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
//             }
//
//             return chiTietSanPhamPhieuXuatHangList;
             return null;
       }

//      public List<SanPhamDTO> getDanhSachSanPham() {
//            List<SanPhamDTO> sanPhamList = new ArrayList<>();
//            try {
//                  Statement statement = JDBCConfigure.getConnection().createStatement();
//                  ResultSet resultSet = statement.executeQuery("SELECT * FROM `sanpham`");
//
//                  while (resultSet.next()) {
//                        SanPham_DTO sanPhamObject = new SanPham_DTO();
//
//                        sanPhamObject.setMaSanPham(resultSet.getInt("MaSanPham"));
//                        sanPhamObject.setTenSanPham(resultSet.getString("TenSanPham"));
//                        sanPhamObject.setXuatXu(resultSet.getString("XuatXu"));
//                        sanPhamObject.setGiaSanPham(resultSet.getInt("Gia"));
//                        sanPhamObject.setSoLuongConLai(resultSet.getInt("SoLuongConLai"));
//                        sanPhamObject.setTrangThai(resultSet.getInt("TrangThai"));
//                        sanPhamObject.setMaLoaiSanPham(resultSet.getInt("MaLoaiSanPham"));
//                        sanPhamObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));
//
//                        sanPhamList.add(sanPhamObject);
//                  }
//
//            } catch (SQLException e) {
//                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
//            }
//            return sanPhamList;
//      }

}
