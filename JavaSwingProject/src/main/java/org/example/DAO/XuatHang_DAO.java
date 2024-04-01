package org.example.DAO;

import org.example.Configure.JDBCConfigure;
import org.example.DTO.*;
// import org.example.DAO.*;
// import org.example.BUS.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class XuatHang_DAO {
      public List<XuatHang_DTO> getDanhSachXuatHang() {
            List<XuatHang_DTO> xuatHangList = new ArrayList<>();
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet resultSet = statement.executeQuery("SELECT * FROM `phieuxuatkho`");
      
                  while (resultSet.next()) {
                        XuatHang_DTO xuatHangObject = new XuatHang_DTO();

                        xuatHangObject.setMaPhieu(resultSet.getInt("MaPhieu"));
                        xuatHangObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));
                        xuatHangObject.setTenKhoHang(resultSet.getString("TenKhoHang"));
                        xuatHangObject.setTongGiaTri(resultSet.getDouble("TongGiaTri"));
                        xuatHangObject.setNgayXuatKho(resultSet.getTimestamp("NgayXuatKho"));

                        xuatHangList.add(xuatHangObject);
                  }
      
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
            }
            return xuatHangList;
      }
      public List<XuatHang_DTO> getDanhSachPhieuXuatHang() {
            List<XuatHang_DTO> phieuXuatHangList = new ArrayList<>();
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet resultSet = statement.executeQuery("select * from phieuxuatkho, khohang where phieuxuatkho.MaKhoHang = khohang.MaKhoHang;");
      
                  while (resultSet.next()) {
                        XuatHang_DTO phieuXuatHangObject = new XuatHang_DTO();

                        phieuXuatHangObject.setMaPhieu(resultSet.getInt("MaPhieu"));
                        phieuXuatHangObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));
                        phieuXuatHangObject.setTenKhoHang(resultSet.getString("TenKhoHang"));
                        phieuXuatHangObject.setNgayXuatKho(resultSet.getTimestamp("NgayXuatKho"));
                        phieuXuatHangObject.setTongGiaTri(resultSet.getDouble("TongGiaTri"));

                        phieuXuatHangList.add(phieuXuatHangObject);
                  }
      
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
            }
            return phieuXuatHangList;
      }
      // public List<SanPham_DTO> getChiTietSanPhamPhieuXuatHang(int maPhieuXuat) {
      //       List<SanPham_DTO> chiTietSanPhamPhieuXuatHangList = new ArrayList<>();

      //       try {
      //             SanPham_DTO chiTietSanPhamPhieuXuatHang = new SanPham_DTO();
      //             Statement statement = JDBCConfigure.getConnection().createStatement();
      //             ResultSet chiTietSanPhamPhieuXuatHangRow = statement.executeQuery("select * from phieuxuatkho, sanpham, ctpxk, khohang where phieuxuatkho.MaKhoHang = khohang.MaKhoHang and ctpxk.MaPhieu = phieuxuatkho.MaPhieu and ctpxk.MaSanPham = sanpham.MaSanPham and phieuxuatkho.MaPhieu = " + maPhieuXuat);
      //             while(chiTietSanPhamPhieuXuatHangRow.next()) {
      //                   chiTietSanPhamPhieuXuatHang.setMaSanPham(chiTietSanPhamPhieuXuatHangRow.getInt("MaSanPham"));
      //                   chiTietSanPhamPhieuXuatHang.setTenSanPham(chiTietSanPhamPhieuXuatHangRow.getString("TenSanPham"));
      //                   chiTietSanPhamPhieuXuatHang.setGiaSanPham(chiTietSanPhamPhieuXuatHangRow.getInt("DonGia"));
      //                   chiTietSanPhamPhieuXuatHang.setSoLuong(chiTietSanPhamPhieuXuatHangRow.getInt("SoLuong"));
      //                   chiTietSanPhamPhieuXuatHang.setThanhTien(chiTietSanPhamPhieuXuatHangRow.getDouble("ThanhTien"));
      //             }
      //       } catch (SQLException e) {
      //             e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
      //       }

      //       return chiTietSanPhamPhieuXuatHangList;
      // }
      public XuatHang_DTO getChiTietPhieuXuatHang(int maPhieuXuat ) {
            XuatHang_DTO chiTietPhieuXuatHang = new XuatHang_DTO();
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet chiTietPhieuXuatHangRow = statement.executeQuery("select * from phieuxuatkho, ctpxk, khohang where phieuxuatkho.MaKhoHang = khohang.MaKhoHang and ctpxk.MaPhieu = phieuxuatkho.MaPhieu and phieuxuatkho.MaPhieu = " + maPhieuXuat);
                  while(chiTietPhieuXuatHangRow.next()) {

                        chiTietPhieuXuatHang.setMaPhieu(chiTietPhieuXuatHangRow.getInt("MaPhieu"));
                        chiTietPhieuXuatHang.setMaKhoHang(chiTietPhieuXuatHangRow.getInt("MaKhoHang"));
                        chiTietPhieuXuatHang.setTenKhoHang(chiTietPhieuXuatHangRow.getString("TenKhoHang"));
                        chiTietPhieuXuatHang.setMaKhoHang(chiTietPhieuXuatHangRow.getInt("MaKhoHang"));
                        chiTietPhieuXuatHang.setTenKhoHang(chiTietPhieuXuatHangRow.getString("TenKhoHang"));
                        chiTietPhieuXuatHang.setNgayXuatKho(chiTietPhieuXuatHangRow.getTimestamp("NgayXuatKho"));
                        chiTietPhieuXuatHang.setTongGiaTri(chiTietPhieuXuatHangRow.getDouble("TongGiaTri"));
                  }
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
            }
            return chiTietPhieuXuatHang;
      }
      public List<SanPham_DTO> getDanhSachSanPham() {
            List<SanPham_DTO> sanPhamList = new ArrayList<>();
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet resultSet = statement.executeQuery("SELECT * FROM `sanpham`");
      
                  while (resultSet.next()) {
                        SanPham_DTO sanPhamObject = new SanPham_DTO();

                        sanPhamObject.setMaSanPham(resultSet.getInt("MaSanPham"));
                        sanPhamObject.setTenSanPham(resultSet.getString("TenSanPham"));
                        sanPhamObject.setXuatXu(resultSet.getString("XuatXu"));
                        sanPhamObject.setGiaSanPham(resultSet.getInt("Gia"));
                        sanPhamObject.setSoLuongConLai(resultSet.getInt("SoLuongConLai"));
                        sanPhamObject.setTrangThai(resultSet.getInt("TrangThai"));
                        sanPhamObject.setMaLoaiSanPham(resultSet.getInt("MaLoaiSanPham"));
                        sanPhamObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));

                        sanPhamList.add(sanPhamObject);
                  }
      
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
            }
            return sanPhamList;
      }
      public void themPhieuXuatHang() {
            try{
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int checkThemPhieuXuatHang = state.executeUpdate("INSERT INTO `phieuxuatkho` (`MaPhieu`, `NgayXuatKho`, `TongGiaTri`, `MaKhoHang`) VALUES (NULL, '2024-04-01 08:09:44.000000', '2670000', '4');");
            }catch(SQLException e) {
                  System.err.println(e.getMessage());
            }
      }     
}
