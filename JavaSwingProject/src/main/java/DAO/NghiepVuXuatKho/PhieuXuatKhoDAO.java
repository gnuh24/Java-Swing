package DAO.NghiepVuXuatKho;

import DAO.DAOInterface;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;
import Others.JDBCConfigure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PhieuXuatKhoDAO implements DAOInterface<PhieuXuatKhoDTO> {
      @Override
      public ArrayList<PhieuXuatKhoDTO> getAll(Integer maKhoHang) {
            ArrayList<PhieuXuatKhoDTO> phieuXuatKhoList = new ArrayList<>();
           try {
                 Statement statement = JDBCConfigure.getConnection().createStatement();
                 ResultSet resultSet = statement.executeQuery("select * from phieuxuatkho where phieuxuatkho.MaKhoHang = " + maKhoHang);

                 while (resultSet.next()) {
                       PhieuXuatKhoDTO phieuXuatKhoObject = new PhieuXuatKhoDTO();

                        phieuXuatKhoObject.setMaPhieu(resultSet.getInt("MaPhieu"));
                        
                        //? Định dạng Localdatetime
                        String dateString = resultSet.getString("NgayXuatKho");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate parsedDateTime = LocalDate.parse(dateString, formatter);
                        phieuXuatKhoObject.setNgayXuatKho(parsedDateTime);
                        phieuXuatKhoObject.setTongGiaTri(resultSet.getLong("TongGiaTri"));
                        phieuXuatKhoObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));
                        phieuXuatKhoObject.setTrangThai(resultSet.getString("TrangThai"));

                       phieuXuatKhoList.add(phieuXuatKhoObject);
                 }

           } catch (SQLException e) {
                 e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
           }
            return phieuXuatKhoList;
      }

      @Override
      public PhieuXuatKhoDTO getById(Integer id) {
            PhieuXuatKhoDTO phieuXuatKho = new PhieuXuatKhoDTO();
           try {
                 Statement statement = JDBCConfigure.getConnection().createStatement();
                 ResultSet phieuXuatKhoRow = statement.executeQuery( "select * from phieuxuatkho where phieuxuatkho.MaPhieu = " + id);
                 while(phieuXuatKhoRow.next()) {
                       phieuXuatKho.setMaPhieu(phieuXuatKhoRow.getInt("MaPhieu"));
                       phieuXuatKho.setMaKhoHang(phieuXuatKhoRow.getInt("MaKhoHang"));
                       String dateString = phieuXuatKhoRow.getString("NgayXuatKho");
                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                       LocalDate parsedDateTime = LocalDate.parse(dateString, formatter);
                       phieuXuatKho.setNgayXuatKho(parsedDateTime);
                       phieuXuatKho.setTongGiaTri(phieuXuatKhoRow.getInt("TongGiaTri"));
                       phieuXuatKho.setTrangThai(phieuXuatKhoRow.getString("TrangThai"));
                 }
                 JDBCConfigure.closeConnection();
           } catch (SQLException e) {
                 e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
           }
            return phieuXuatKho;
      }
      @Override
      public boolean create(Integer maKhoHang, PhieuXuatKhoDTO phieuXuatKhoDTO) {
            try{
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int taoPhieuXuatHang = state.executeUpdate("INSERT INTO `phieuxuatkho` (`NgayXuatKho`, `TongGiaTri`, `MaKhoHang`,`TrangThai`) VALUES (now(), '"+phieuXuatKhoDTO.getTongGiaTri()+"', '"+maKhoHang+"','ChoDuyet');");
                  if(taoPhieuXuatHang != 1) {
                        System.out.println("Khoi tao phieu xuat hàng that bai !");
                        return false;
                  } else {
                        JDBCConfigure.closeConnection();
                        return true;
                  }
            }catch(SQLException e) {
                  System.out.println("Khu a");
                  System.err.println(e.getMessage());
                  return false;
            }
      }
      @Override
      public boolean update(PhieuXuatKhoDTO phieuXuatKhoDTO) {
            
            System.out.println("UPDATE `phieuxuatkho` SET `TongGiaTri` = '"+phieuXuatKhoDTO.getTongGiaTri()+"', `NgayXuatKho` = '"+String.valueOf(phieuXuatKhoDTO.getNgayXuatKho())+"',`TrangThai` = '"+phieuXuatKhoDTO.getTrangThai()+"'  WHERE `phieuxuatkho`.`MaPhieu` = " + phieuXuatKhoDTO.getMaPhieu());
            try{
                  //? UPDATE `phieuxuatkho` SET `NgayXuatKho` = now(), `TongGiaTri` = '15000000' WHERE `phieuxuatkho`.`MaPhieu` = 33;
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int capNhatPhieuXuatHang = state.executeUpdate("UPDATE `phieuxuatkho` SET `TongGiaTri` = '"+phieuXuatKhoDTO.getTongGiaTri()+"', `NgayXuatKho` = '"+String.valueOf(phieuXuatKhoDTO.getNgayXuatKho())+"',`TrangThai` = '"+phieuXuatKhoDTO.getTrangThai()+"'  WHERE `phieuxuatkho`.`MaPhieu` = " + phieuXuatKhoDTO.getMaPhieu());
                  if(capNhatPhieuXuatHang != 1) {
                        System.out.println("Cap nhat phieu xuat hang that bai !");
                        return false;
                  } else {
                        JDBCConfigure.closeConnection();
                        return true;
                  }
            }catch(SQLException e) {
                  System.err.println(e.getMessage());
                  return false;
            }
      }

      @Override
      public boolean delete(PhieuXuatKhoDTO phieuXuatKhoDTO) {
            try{
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int taoPhieuXuatHang = state.executeUpdate("DELETE FROM phieuxuatkho WHERE `phieuxuatkho`.`MaPhieu` = " + phieuXuatKhoDTO.getMaPhieu());
                  if(taoPhieuXuatHang != 1) {
                        System.out.println("Xoa phieu xuat hàng that bai !");
                        return false;
                  } else {
                        JDBCConfigure.closeConnection();
                        return true;
                  }
            }catch(SQLException e) {
                  System.err.println(e.getMessage());
                  return false;
            }
      }
      public String getTenKhoHang(int maPhieuXuat) {
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet phieuXuatKho = statement.executeQuery("SELECT * FROM `phieuxuatkho`,`khohang` WHERE phieuxuatkho.MaKhoHang = khohang.MaKhoHang and phieuxuatkho.MaPhieu = " + maPhieuXuat);
                  while(phieuXuatKho.next()) {
                        return phieuXuatKho.getString("TenKhoHang");
                  }
                  return null;
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                  return null;
            }
      }
      public String getHoTen(int maKhoHang) {
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet phieuXuatKho = statement.executeQuery("SELECT * FROM `khohang`,`taikhoan` WHERE khohang.maKhoHang = taikhoan.maKhoHang and  khohang.maKhoHang = " + maKhoHang);
                  while(phieuXuatKho.next()) {
                        return phieuXuatKho.getString("HoTen");
                  }
                  return null;
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                  return null;
            }
      }
      public String getHoTenByMaPhieuXuat(int maPhieuXuat) {
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet phieuXuatKho = statement.executeQuery("SELECT * FROM `phieuxuatkho`, `taikhoan` WHERE phieuxuatkho.MaKhoHang = taikhoan.MaKhoHang and phieuxuatkho.MaPhieu =" + maPhieuXuat);
                  while(phieuXuatKho.next()) {
                        return phieuXuatKho.getString("HoTen");
                  }
                  return null;
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                  return null;
            }
      }
      public int maPhieuXuatKhoTiepTheo() {
            try {
                    Statement statement = JDBCConfigure.getConnection().createStatement();
                    ResultSet maPhieuXuatTiepTheo = statement.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'javaswing_database'AND TABLE_NAME = 'phieuxuatkho';");
                    while(maPhieuXuatTiepTheo.next()) {
                        return maPhieuXuatTiepTheo.getInt("AUTO_INCREMENT");
                    }
                    return -1;
            } catch (SQLException e) {
                    e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                    return -1;
            }
        }
      //?     Lấy chi tiết phiếu xuất kho
      //?select * from ctpxk, sanpham, phieuxuatkho, khohang where ctpxk.MaPhieu = phieuxuatkho.MaPhieu and ctpxk.MaSanPham = sanpham.MaSanPham and phieuxuatkho.MaKhoHang = khohang.MaKhoHang and phieuxuatkho.MaPhieu = " + maPhieuXuat
}
