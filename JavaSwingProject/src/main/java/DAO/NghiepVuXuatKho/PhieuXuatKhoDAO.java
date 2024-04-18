package DAO.NghiepVuXuatKho;

import DAO.DAOInterface;
import DTO.NghiepVuXuatKho.ChiTietPhieuXuatKhoDTO;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;
import Others.JDBCConfigure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime parsedDateTime = LocalDateTime.parse(dateString, formatter);
                        phieuXuatKhoObject.setNgayXuatKho(parsedDateTime);
                        phieuXuatKhoObject.setTongGiaTri(resultSet.getLong("TongGiaTri"));
                        phieuXuatKhoObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));

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
                 ResultSet phieuXuatKhoRow = statement.executeQuery(
                     "select * from phieuxuatkho where phieuxuatkho.MaPhieu = " + id);
                 while(phieuXuatKhoRow.next()) {
                       phieuXuatKho.setMaPhieu(phieuXuatKhoRow.getInt("MaPhieu"));
                       phieuXuatKho.setMaKhoHang(phieuXuatKhoRow.getInt("MaKhoHang"));
                       String dateString = phieuXuatKhoRow.getString("NgayXuatKho");
                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                       LocalDateTime parsedDateTime = LocalDateTime.parse(dateString, formatter);
                       phieuXuatKho.setNgayXuatKho(parsedDateTime);
                       phieuXuatKho.setTongGiaTri(phieuXuatKhoRow.getInt("TongGiaTri"));
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
                  int taoPhieuXuatHang = state.executeUpdate("INSERT INTO `phieuxuatkho` (`MaPhieu`, `NgayXuatKho`, `TongGiaTri`, `MaKhoHang`) VALUES (NULL, now(), '"+phieuXuatKhoDTO.getTongGiaTri()+"', '"+maKhoHang+"');");
                  if(taoPhieuXuatHang != 1) {
                        System.out.println("Khoi tao phieu xuat hàng that bai !");
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
      // public void taoPhieuXuatHang() {
      //            //! Cần thêm giá trị kho hàng
      //            try {
      //                  Statement state = JDBCConfigure.getConnection().createStatement();
      //                  //? UPDATE phieuxuathang
      //                  int maPhieuVuaKhoiTao = 0;
      //                  if(state.executeUpdate("INSERT INTO `phieuxuatkho` (`MaPhieu`, `NgayXuatKho`, `TongGiaTri`, `MaKhoHang`) VALUES (NULL, now(), '"+currencyBack(thanh_tien_total.getText())+"', '4')") != 1) {
      //                        JOptionPane.showMessageDialog(null, "Lỗi khi tạo phiếu xuất hàng","Cảnh báo", JOptionPane.ERROR_MESSAGE);
      //                  } else {
      //                        ResultSet resultSet = state.executeQuery("SELECT LAST_INSERT_ID()");
      //                        if(resultSet.next()) {
      //                              maPhieuVuaKhoiTao = resultSet.getInt(1);
      //                        }
      //                  }
      
      //                  //? UPDATE CTPXK
      //                  for (SanPhamDTO sanPham : getDanhSachSanPhamTaoPhieuXuatHang()) {
      //                        if(state.executeUpdate("INSERT INTO `ctpxk` (`SoLuong`, `ThanhTien`, `DonGia`, `MaPhieu`, `MaSanPham`) VALUES ('"+sanPham.getSoLuongXuatHang()+"', '"+(sanPham.getGiaSanPham() * sanPham.getSoLuongXuatHang())+"', '"+sanPham.getGiaSanPham()+"', '"+maPhieuVuaKhoiTao+"', '"+sanPham.getMaSanPham()+"')") != 1) {
      //                              JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm vào Chi Tiet Phieu Xuat Hang","Cảnh báo", JOptionPane.ERROR_MESSAGE);
      //                        }
      //                  }
      //                  //? UPDATE số lượng SanPham
      //                  for (SanPhamDTO sanPham : getDanhSachSanPhamTaoPhieuXuatHang()) {
      //                        ResultSet resultSet = state.executeQuery("SELECT * FROM `sanpham` WHERE `sanpham`.`MaSanPham` = "+sanPham.getMaSanPham()+";");
      //                        int setSoLuongConLai = 0;
      //                        if(resultSet.next()) {
      //                              resultSet.getInt(5);
      //                              setSoLuongConLai = resultSet.getInt(5) - sanPham.getSoLuongXuatHang();
      //                        }
      //                        if(state.executeUpdate("UPDATE `sanpham` SET `SoLuongConLai` = '"+setSoLuongConLai+"' WHERE `sanpham`.`MaSanPham` = "+sanPham.getMaSanPham()+";") != 1) {
      //                              JOptionPane.showMessageDialog(null, "Lỗi khi set So Luong Con Lai","Cảnh báo", JOptionPane.ERROR_MESSAGE);
      //                        }
      //                  }
      //                  //?Reset
      
      //                        //? Xóa bảng danh sách sản phẩm xuất hàng
      //                        for (int i = model_ds_xuat_hang.getRowCount() - 1; i >= 0; i--) {
      //                              model_ds_xuat_hang.removeRow(i);
      //                        }
      //                        //? cập nhật danh sách sản phẩm
      //                        for (int i = model_ds_san_pham.getRowCount() - 1; i >= 0; i--) {
      //                              model_ds_san_pham.removeRow(i);
      //                        }
      //                        showDanhSachSanPham();
      //                        //?cập nhật thành tiền
      //                        thanh_tien_total.setText("0 đ");
      //                  } catch (SQLException e) {
      //                  System.err.println(e.getMessage());
      //            }
      // }
      @Override
      public boolean update(PhieuXuatKhoDTO phieuXuatKhoDTO) {
            
            System.out.println("UPDATE `phieuxuatkho` SET `TongGiaTri` = '"+phieuXuatKhoDTO.getTongGiaTri()+"', `NgayXuatKho` = '"+String.valueOf(phieuXuatKhoDTO.getNgayXuatKho())+"'  WHERE `phieuxuatkho`.`MaPhieu` = " + phieuXuatKhoDTO.getMaPhieu());
            try{
                  //? UPDATE `phieuxuatkho` SET `NgayXuatKho` = now(), `TongGiaTri` = '15000000' WHERE `phieuxuatkho`.`MaPhieu` = 33;
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int capNhatPhieuXuatHang = state.executeUpdate("UPDATE `phieuxuatkho` SET `TongGiaTri` = '"+phieuXuatKhoDTO.getTongGiaTri()+"', `NgayXuatKho` = '"+String.valueOf(phieuXuatKhoDTO.getNgayXuatKho())+"'  WHERE `phieuxuatkho`.`MaPhieu` = " + phieuXuatKhoDTO.getMaPhieu());
                  if(capNhatPhieuXuatHang != 1) {
                        System.out.println("Cap nhat phieu xuat hàng that bai !");
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
