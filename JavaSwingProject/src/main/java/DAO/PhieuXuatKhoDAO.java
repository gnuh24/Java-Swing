package DAO;

import DTO.NghiepVuXuatKho.ChiTietPhieuXuatKhoDTO;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;
import DTO.ThongTinSanPham.SanPhamDTO;
import Others.JDBCConfigure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class PhieuXuatKhoDAO implements DAOInterface<PhieuXuatKhoDTO>{
      

//      @Override
//      public List<PhieuXuatKhoDTO> getAll(Integer maKhoHang) {
//            List<PhieuXuatKhoDTO> list = new ArrayList<>();
//            try {
//                  Statement statement = JDBCConfigure.getConnection().createStatement();
//                  ResultSet resultSet = statement.executeQuery("SELECT * FROM `phieuxuatkho` where maKhoHang =" );

//                  while (resultSet.next()) {
//                        PhieuXuatKhoDTO xuatHangObject = new PhieuXuatKhoDTO();

//                        xuatHangObject.setMaPhieu(resultSet.getInt("MaPhieu"));
//                        xuatHangObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));
//                        xuatHangObject.setTenKhoHang(resultSet.getString("TenKhoHang"));
//                        xuatHangObject.setTongGiaTri(resultSet.getLong("TongGiaTri"));
//                        xuatHangObject.setNgayXuatKho(resultSet.getTimestamp("NgayXuatKho"));

//                        list.add(xuatHangObject);
//                  }

//            } catch (SQLException e) {
//                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
//            }
//            return list;
//      }

      @Override
      public ArrayList<PhieuXuatKhoDTO> getAll(Integer maKhoHang) {
            ArrayList<PhieuXuatKhoDTO> phieuXuatHangList = new ArrayList<>();
           try {
                 Statement statement = JDBCConfigure.getConnection().createStatement();
                 ResultSet resultSet = statement.executeQuery("select * from phieuxuatkho where phieuxuatkho.MaKhoHang = " + maKhoHang);

                 while (resultSet.next()) {
                       PhieuXuatKhoDTO phieuXuatHangObject = new PhieuXuatKhoDTO();

                        phieuXuatHangObject.setMaPhieu(resultSet.getInt("MaPhieu"));
                        phieuXuatHangObject.setNgayXuatKho(LocalDateTime.parse(resultSet.getString("NgayXuatKho")));
                        phieuXuatHangObject.setTongGiaTri(resultSet.getLong("TongGiaTri"));
                        phieuXuatHangObject.setMaKhoHang(resultSet.getInt("MaKhoHang"));

                       phieuXuatHangList.add(phieuXuatHangObject);
                 }

           } catch (SQLException e) {
                 e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
           }
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
                  int taoPhieuXuatHang = state.executeUpdate("INSERT INTO `phieuxuatkho` (`MaPhieu`, `NgayXuatKho`, `TongGiaTri`, `MaKhoHang`) VALUES (NULL, now(), '"+phieuXuatKhoDTO.getTongGiaTri()+"', '"+maKhoHang+"');");
                  if(taoPhieuXuatHang != 1) {
                        System.out.println("Khoi tao phieu xuat hàng that bai !");
                        return false;
                  } else return true;
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

}
